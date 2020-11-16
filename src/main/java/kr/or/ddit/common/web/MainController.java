package kr.or.ddit.common.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.notice.service.NoticeServiceI;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserServiceI;

@Controller
@RequestMapping("/main")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource(name = "userService")
	private UserServiceI userService;
	
	@Resource(name="noticeService")
	private NoticeServiceI noticeService;
	
	@RequestMapping(path="login",method = RequestMethod.GET)
	public String loginView() {
		return "login";
	}
	
	@RequestMapping(path="login",method = RequestMethod.POST)
	public String login(UserVo userVo,HttpSession session) {
//		logger.debug("유저정보 : {}", userVo);
		UserVo uvo = userService.getUser(userVo.getUser_id());
		
		if(userVo == null || !uvo.getUser_pass().equals(userVo.getUser_pass())) {
			return "redirect:/main/login";
		}
		// 비밀번호가 일치하는 경우 (메인페이지 이동)
		else if(uvo.getUser_pass().equals(userVo.getUser_pass())) {
			session.setAttribute("S_MEMBER", uvo);
			session.setAttribute("noticeGubun", noticeService.getAllNoticeGubun());
			return "tiles/common/main";
		}else 
		return "redirect:/main/login";
		
	}
	
	@RequestMapping(path="logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}
	
	@RequestMapping(path="mainPage")
	public String mainView() {
		return "tiles/common/main";
	}
}
