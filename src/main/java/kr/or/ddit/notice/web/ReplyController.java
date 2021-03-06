package kr.or.ddit.notice.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.notice.model.ReplyVo;
import kr.or.ddit.notice.service.NoticeServiceI;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	@Resource(name="noticeService")
	private NoticeServiceI noticeService;
	
	@RequestMapping(path="write",method = RequestMethod.POST)
	public String replyWrite(ReplyVo rvo) {
		rvo.setRep_stat(1);
		int insertCnt = noticeService.insertReply(rvo);
		return "redirect:/notice/detail?nt_num="+rvo.getNt_num();
	}
	
	@RequestMapping(path="delete")
	public String replyDelete(ReplyVo rvo) {
		rvo.setRep_stat(0);
		int deleteCnt = noticeService.deleteReply(rvo);
		return "redirect:/notice/detail?nt_num="+rvo.getNt_num();
	}
}
