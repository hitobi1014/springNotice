package kr.or.ddit.notice.web;

import java.io.IOException;
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
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class NoticeWrite
 */
@WebServlet("/noticeWrite")
@MultipartConfig
public class NoticeWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(NoticeWrite.class);   
	private NoticeServiceI noticeService;

	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/notice/noticeWrite.jsp").forward(request, response);
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
		String np= request.getParameter("nt_panum");
		NoticeVo nvo = new NoticeVo();
		logger.debug("구분코드, 아이디, 제목, 입력글 : {} {} {} {}",ntgu_code, user_id, nt_title, nt_cont);
		if(np !=null) {
			int nt_panum = Integer.parseInt(np);
			nvo.setNt_panum(nt_panum);
			logger.debug("부모게시글번호 : {}",nt_panum);
		}
		
		nvo.setNtgu_code(ntgu_code);
		nvo.setUser_id(user_id);
		nvo.setNt_title(nt_title);
		nvo.setNt_cont(nt_cont);
		nvo.setNt_stat(nt_stat);
		nvo.setNtcont_stat(ntcont_stat);
		
		int insertCnt = noticeService.insertNotice(nvo);
		int nt_num = nvo.getNt_num(); // 글번호가져오기
		if(insertCnt>0) {
			logger.debug("등록성공");
		}else {
			logger.debug("등록실패");
		}
		
		// 파일 등록
		
		for(int i=1; i<6; i++) {
			Part profile = request.getPart("nt_file" +i);
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
					logger.debug("파일등록성공");
				}else {
					logger.debug("파일등록실패");
				}
			}
			
			
			logger.debug("파일이름 : {}, 파일 경로 : {}",realFilename,filePath);
		}
		
		
		
		response.sendRedirect("/notice?ntgu_code="+ntgu_code);
	}

}
