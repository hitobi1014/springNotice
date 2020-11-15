package kr.or.ddit.notice.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.fileupload.FileUploadUtil;
import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class NoticeModify
 */
@WebServlet("/noticeModify")
@MultipartConfig
public class NoticeModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeServiceI noticeService;
	private static final Logger logger = LoggerFactory.getLogger(NoticeModify.class);
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String user_id = request.getParameter("userid");
		int nt_num = Integer.parseInt(request.getParameter("ntnum")); //게시글 번호 가져오기
		
		Map<String, Object> getNotice = noticeService.getNotice(nt_num); //게시글 정보 가져오기
		NoticeVo nvo =  (NoticeVo) getNotice.get("nvo"); // Map객체에서 noticeVo객체만 가져오기
		
		String ntgu_code = nvo.getNtgu_code(); //게시판 분류코드 얻기
		Map<String, Object> getNtgu = noticeService.getAllNotice(ntgu_code); //게시판 분류코드를 통해 noticeGubunVo를 담은 map객체 생성
		NoticeGubunVo ngvo = (NoticeGubunVo) getNtgu.get("ngvo"); //
//		logger.debug("게시글 정보 : {}",nvo);
//		logger.debug("게시판분류 정보 : {}", ngvo);
		
		request.setAttribute("nvo", nvo);
		request.setAttribute("ngvo", ngvo);
		
		// 파일가져오기
		List<NoticeFileVo> nfvoList = noticeService.getAllFile(nt_num);
		request.setAttribute("nfvoList", nfvoList);
		
		request.getRequestDispatcher("/notice/noticeModify.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 필요데이터
//		NTGU_CODE 	USER_ID 	NT_DT 	NT_CONT 	NT_TITLE 	NT_PANUM
//		구분코드 		아이디 	  	날짜		내용			제목			부모게시글번호
		String ntgu_code = request.getParameter("noticeGubun");
		String nt_title =request.getParameter("title");
		String nt_cont = request.getParameter("editordata");
		String np= request.getParameter("nt_panum");
		int nt_num = Integer.parseInt(request.getParameter("nt_num"));
		
//		logger.debug("코드 : {}, 제목 : {}, 내용 :{}, 글번호 : {}",ntgu_code, nt_title, nt_cont, nt_num);
		
		NoticeVo nvo = new NoticeVo();
		
		if(np !=null) {
			int nt_panum = Integer.parseInt(np);
			nvo.setNt_panum(nt_panum);
		}
		
		nvo.setNtgu_code(ntgu_code);
		nvo.setNt_title(nt_title);
		nvo.setNt_cont(nt_cont);
		nvo.setNt_num(nt_num);
		int updateCnt = noticeService.updateNotice(nvo);
		if(updateCnt>0) {
			logger.debug("등록성공");
		}else {
			logger.debug("등록실패");
		}
		
		String[] getfilenums = request.getParameterValues("fileDel");
		logger.debug("확인값 : {}",getfilenums);
		if(getfilenums != null) {
			int filenum=0;
			for(int i=0; i<getfilenums.length; i++) {
				filenum = Integer.parseInt(getfilenums[i]);
				int deleteCnt = noticeService.deleteFile(filenum);
				if(deleteCnt >0) {
					logger.debug("파일삭제 성공");
				}else {
					logger.debug("파일삭제 실패");
				}
			}
		}
		

		for(int i=1; i<6; i++) {
			logger.debug("i값 : {}",i);
			Part profile = request.getPart("nt_file" +i);
			if(profile !=null) {
				logger.debug("profile : {} ",profile.getHeader("Content-Disposition"));
				
				String realFilename = FileUploadUtil.getFileName(profile.getHeader("Content-Disposition")); // 파일이름
				String fileName = UUID.randomUUID().toString();
				String extension = FileUploadUtil.getExtension(realFilename);
				String filePath = ""; //파일경로
				if(profile.getSize() > 0) { 
					filePath = "D:\\upload\\" + fileName + "."+extension;
					profile.write(filePath);
					NoticeFileVo nfvo = new NoticeFileVo();
					nfvo.setFilename(realFilename);
					nfvo.setFilepath(filePath);
					nfvo.setNt_num(nt_num);
					int inserFiletCnt = noticeService.insertFile(nfvo);
					if(inserFiletCnt > 0) {
						logger.debug("{}번째 파일 등록성공",i);
					}else {
						logger.debug("파일 등록실패");
					}
				}
			}else {
				continue;
			}
		}
		
		response.sendRedirect("/notice?ntgu_code="+ntgu_code);
	}
}
