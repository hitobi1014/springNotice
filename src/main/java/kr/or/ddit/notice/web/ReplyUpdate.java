package kr.or.ddit.notice.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.notice.model.ReplyVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class ReplyUpdate
 */
@WebServlet("/replyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReplyUpdate.class);
	private NoticeServiceI noticeService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("replyuserid");
		int rep_num = Integer.parseInt(request.getParameter("repnum"));
		String rep_cont = request.getParameter("replyCont");
		int nt_num = Integer.parseInt(request.getParameter("replyNtnum"));
//		logger.debug("아이디: {}, 댓글번호 : {}, 댓글내용 : {}",user_id,rep_num, rep_cont);
//		logger.debug("글 번호 : {}",nt_num);
		
		ReplyVo rvo = new ReplyVo();
		rvo.setUser_id(user_id);
		rvo.setRep_num(rep_num);
		rvo.setRep_cont(rep_cont);
		
		int updateCnt = noticeService.updateReply(rvo);
		if(updateCnt > 0) {
			logger.debug("댓글 수정성공");
		}else {
			logger.debug("댓글 수정실패");
		}
		
		response.sendRedirect("/noticeDetail?nt_num="+nt_num);
		
	}

}

