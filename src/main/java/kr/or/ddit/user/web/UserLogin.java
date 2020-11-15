//package kr.or.ddit.user.web;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import kr.or.ddit.user.dao.UserDao;
//import kr.or.ddit.user.dao.UserDaoI;
//import kr.or.ddit.user.model.UserVo;
//import kr.or.ddit.user.service.UserService;
//import kr.or.ddit.user.service.UserServiceI;
//
///**
// * Servlet implementation class UserLogin
// */
//@WebServlet("/login")
//public class UserLogin extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private static final Logger logger = LoggerFactory.getLogger(UserLogin.class);
//	
//	private UserServiceI userService;
//	@Override
//	public void init() throws ServletException {
//		userService = new UserService();
//	}
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/login.jsp").forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userId = request.getParameter("userId");
//		String password = request.getParameter("password");
//		logger.debug("userId : {}, password : {}",userId, password);
//		
//		UserVo userVo = userService.getUser(userId);
//		
//		logger.debug("userVo : {}",userVo);
//		
//		// db에 등록된 회원이 없거나, 비밀번호가 틀린경우 (로그인페이지)
//		if(userVo == null || !userVo.getUser_pass().equals(password)) {
//			logger.debug("접속실패");
//			doGet(request, response);
//		}
//		// 비밀번호가 일치하는 경우 (메인페이지 이동)
//		else if(userVo.getUser_pass().equals(password)) {
//			logger.debug("접속성공");
//			request.getSession().setAttribute("S_MEMBER", userVo);
//			request.getRequestDispatcher("/main.jsp").forward(request, response);
//		}
//	}
//
//}
