package kr.or.ddit.common.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeServiceI noticeService = new NoticeService();
		List<NoticeGubunVo> noticeGubun = noticeService.getAllNoticeGubun();
		request.getSession().setAttribute("noticeGubun", noticeGubun);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
