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
 * Servlet implementation class NoticeGubunModify
 */
@WebServlet("/noticeGubunModify")
public class NoticeGubunModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(NoticeGubunModify.class);
	private NoticeServiceI noticeService;
	
	public NoticeGubunModify() {
		
	}
	
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
		String ntgu_code =  request.getParameter("ntgu_code");
		int ntgu_stat = Integer.parseInt(request.getParameter("noticeUse"));
		logger.debug("게시판코드 : {}, 게시판 상태 : {}",ntgu_code, ntgu_stat);
		
		NoticeGubunVo ngvo = new NoticeGubunVo();
		ngvo.setNtgu_code(ntgu_code);
		ngvo.setNtgu_stat(ntgu_stat);
		
		int updateCnt = noticeService.updateNoticeGubun(ngvo);
		
		if(updateCnt > 0) {
			logger.debug("게시판구분 수정");
		}else {
			logger.debug("게시판구분 수정실패");
		}
		doGet(request, response);
		
	}

}
