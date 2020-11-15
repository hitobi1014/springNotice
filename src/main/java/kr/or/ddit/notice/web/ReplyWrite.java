package kr.or.ddit.notice.web;

import java.io.IOException;
import java.util.Date;

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
 * Servlet implementation class Reply
 */
@WebServlet("/reply")
public class ReplyWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReplyWrite.class);
	private NoticeServiceI noticeService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 댓글 등록 필요정보
		// nt_num	rep_stat	rep_cont	user_id
		//글번호	댓글상태	댓글내용	아이디
		int nt_num = Integer.parseInt(request.getParameter("replyNtnum"));
		int rep_stat = 1;
		String rep_cont = request.getParameter("replyCont");
		String user_id = request.getParameter("replyuserid");
		logger.debug("아이디 : {}, 댓글 : {}, 글번호 : {} ",user_id, rep_cont,nt_num);
		
		ReplyVo rvo = new ReplyVo();
		rvo.setNt_num(nt_num);
		rvo.setRep_stat(rep_stat);
		rvo.setRep_cont(rep_cont);
		rvo.setUser_id(user_id);
		
		int insertCnt = noticeService.insertReply(rvo);
		if(insertCnt >0) {
			logger.debug("댓글 등록성공");
		}else {
			logger.debug("댓글 등록실패");
		}
		
		response.sendRedirect("/noticeDetail?nt_num="+nt_num);
		
	}

}
