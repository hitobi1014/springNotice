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
import kr.or.ddit.notice.model.ReplyVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class NoticeWrite
 */
@WebServlet("/noticeAnswer")
@MultipartConfig
public class NoticeAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(NoticeAnswer.class);   
	private NoticeServiceI noticeService;
	private int nt_panum;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		nt_panum=Integer.parseInt(request.getParameter("ntnum"));
//		logger.debug("게시글번호 : {}",nt_panum);
		Map<String, Object> map = noticeService.getNotice(nt_panum);
		NoticeVo nvo = (NoticeVo) map.get("nvo");
		String ntgu_code = nvo.getNtgu_code();
		Map<String, Object> ngMap = noticeService.getAllNotice(ntgu_code);
		NoticeGubunVo ngvo = (NoticeGubunVo) ngMap.get("ngvo");
		
		request.setAttribute("nvo", nvo);
		request.setAttribute("ngvo", ngvo);
		
		request.setAttribute("nt_panum", nt_panum);
		request.getRequestDispatcher("/notice/noticeAnswer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 필요데이터
//		NT_NUM 	NTGU_CODE 	USER_ID 	NT_STAT 		NTCONT_STAT 	NT_DT 	NT_CONT 	NT_TITLE 	NT_PANUM
//		시퀀스	구분코드 	아이디  	게시판상태코드  글 상태코드		날짜	내용		제목		부모게시글번호
		String ntgu_code = request.getParameter("noticeGubun");
		String user_id = request.getParameter("user_id"); 
		String nt_title =request.getParameter("title");
		String nt_cont = request.getParameter("editordata");
		int nt_stat=1;
		int ntcont_stat = 1;
		logger.debug("부모글 번호 : {}",nt_panum);
		
		NoticeVo nvo = new NoticeVo();
		nvo.setNtgu_code(ntgu_code);
		nvo.setUser_id(user_id);
		nvo.setNt_title(nt_title);
		nvo.setNt_cont(nt_cont);
		nvo.setNt_stat(nt_stat);
		nvo.setNtcont_stat(ntcont_stat);
		nvo.setNt_panum(nt_panum);
		
		int insertCnt = noticeService.insertNotice(nvo);
		int nt_num = nvo.getNt_num(); // 글번호가져오기
		if(insertCnt>0) {
			logger.debug("등록성공");
		}else {
			logger.debug("등록실패");
		}
		
		// 파일 등록
		
//		Part profile1 = request.getPart("nt_file1");
//		Part profile2 = request.getPart("nt_file2");
//		Part profile3 = request.getPart("nt_file3");
//		Part profile4 = request.getPart("nt_file4");
//		Part profile5 = request.getPart("nt_file5");
//		request.getParameterValues("nt_file")
//		logger.debug("profile : {} ",profile1.getHeader("Content-Disposition"));
//		logger.debug("profile : {} ",profile2.getHeader("Content-Disposition"));
//		logger.debug("profile : {} ",profile3.getHeader("Content-Disposition"));
//		logger.debug("profile : {} ",profile4.getHeader("Content-Disposition"));
//		logger.debug("profile : {} ",profile5.getHeader("Content-Disposition"));
		
//		String realFilename = FileUploadUtil.getFileName(profile1.getHeader("Content-Disposition")); // 파일이름
//		String fileName = UUID.randomUUID().toString();
//		String extension = FileUploadUtil.getExtension(realFilename);
//		String filePath = ""; //파일경로
//		if(profile1.getSize() > 0) { 
//			filePath = "D:\\upload\\" + fileName + "."+extension;
//			profile1.write(filePath);
//		}
//		NoticeFileVo nfvo = new NoticeFileVo();
//		nfvo.setFilename(realFilename);
//		nfvo.setFilepath(filePath);
//		nfvo.setNt_num(nt_num);
//		int inserFiletCnt = noticeService.insertFile(nfvo);
//		
//		if(inserFiletCnt > 0) {
//			logger.debug("파일등록성공");
//		}else {
//			logger.debug("파일등록실패");
//		}
//		logger.debug("파일이름 : {}, 파일 경로 : {}",realFilename,filePath);
		
		
		
		response.sendRedirect("/notice?ntgu_code="+ntgu_code);
	}

}
