package kr.or.ddit.notice.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class NoticeGubunInsert
 */
@WebServlet("/noticeGubunInsert")
public class NoticeGubunInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(NoticeGubunInsert.class);
	
	private NoticeServiceI noticeService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<NoticeGubunVo> noticeGubun = noticeService.getAllNoticeGubun();
		request.getSession().setAttribute("noticeGubun", noticeGubun);
		request.getRequestDispatcher("/notice/noticeManage.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String ntgu_code = request.getParameter("ntgu_code"); // 게시판 코드
		String ntgu_name = request.getParameter("ntgu_name"); // 게시판 이름
		int ntgu_stat = 1; // 게시판 상태 1이면 사용중 0이면 미사용
		
		NoticeGubunVo ngvo = new NoticeGubunVo();
		ngvo.setNtgu_code(ntgu_code);
		ngvo.setNtgu_name(ntgu_name);
		ngvo.setNtgu_stat(ntgu_stat);
		
		logger.debug("게시판 코드 : {}, 게시판이름 : {}, 상태 : {}",ntgu_code,ntgu_name,ntgu_stat);
		
		int insertCnt = noticeService.insertNoticeGubun(ngvo);
		
		if(insertCnt > 0) {
			logger.debug("게시판 생성 성공");
		}else {
			logger.debug("게시판 생성 실패");
		}
		doGet(request, response);
	}

}
