package kr.or.ddit.notice.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.service.NoticeServiceI;

@Controller
@RequestMapping("/manage")
public class NoticeManageController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeManageController.class);
	
	@Resource(name="noticeService")
	private NoticeServiceI noticeService;
	
	@RequestMapping("/noti")
	public String noticeManageView() {
		
		return "tiles/notice/noticeManage";
	}
	
	@RequestMapping(path="insert",method = RequestMethod.POST)
	public String noticeGubunInsert(NoticeGubunVo ngvo) {
		ngvo.setNtgu_stat(1);
		int insertCnt = noticeService.insertNoticeGubun(ngvo);
		
		if(insertCnt > 0) {
			logger.debug("게시판 생성 성공");
		}else {
			logger.debug("게시판 생성 실패");
		}
		
		return "redirect:/manage/insert?ngvo="+ngvo;
	}
	
	@RequestMapping(path="insert",method = RequestMethod.GET)
	public String noticeGubunInsertView(HttpSession session, NoticeGubunVo ngvo) {
		logger.debug("받은 ngvo : {}",ngvo);
		List<NoticeGubunVo> noticeGubun = noticeService.getAllNoticeGubun();
		session.setAttribute("noticeGubun", noticeGubun);
		return "tiles/notice/noticeManage";
	}
	
	@RequestMapping(path="modify",method=RequestMethod.POST)
	public String modifyView(NoticeGubunVo ngvo) {
		int updateCnt = noticeService.updateNoticeGubun(ngvo);
		if(updateCnt > 0) {
			logger.debug("게시판 구분 수정");
		}else {
			logger.debug("게시판 구분 수정 실패");
		}
		return "redirect:/manage/modify?ngvo="+ngvo;
	}
	
	@RequestMapping(path="modify",method = RequestMethod.GET)
	public String modifySuccess(NoticeGubunVo ngvo, HttpSession session) {
		List<NoticeGubunVo> noticeGubun = noticeService.getAllNoticeGubun();
		session.setAttribute("noticeGubun", noticeGubun);
		return "tiles/notice/noticeManage";
	}
}
