package kr.or.ddit.notice.web;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/fileDownload")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeServiceI noticeService;
	private static final Logger logger = LoggerFactory.getLogger(FileDownload.class);
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 사용자 아이디 파라미터 확인하고
//			 db에서 사용중인 filename 확인
		int filenum = Integer.parseInt(request.getParameter("filenum"));
//		logger.debug("파일 번호 : {}",filenum);
		NoticeFileVo nfvo = noticeService.getFile(filenum);
//		logger.debug("파일 이름 : {}",nfvo.getFilename());
//		logger.debug("파일 경로 : {}", nfvo.getFilepath());
		
			//response content-type 설정
			//setContentType  or addHeader (타입이름, 값)
			response.setHeader("Content-Disposition", "attachment; filename=\""+nfvo.getFilepath()+"\"");
//			logger.debug("파일 헤더 : {}",response.getHeader("Content-Disposition"));
			response.setContentType("application/octet-stream");
//			 경로 확인 후 파일 입출력을 통해 응답생성
			// 파일읽기 
			// 응답생성
			FileInputStream fis = new FileInputStream(nfvo.getFilepath());
			ServletOutputStream sos = response.getOutputStream();
			byte[] buffer = new byte[512];
			while(fis.read(buffer)!= -1) {
				sos.write(buffer);
			}
			fis.close();
			sos.flush();
			sos.close();
			
		}
	
}
