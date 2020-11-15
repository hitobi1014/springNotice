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
 * Servlet implementation class ReplyDelete
 */
@WebServlet("/replyDelete")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReplyDelete.class);
	private NoticeServiceI noticeService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		logger.debug("댓글번호 : {}",request.getParameter("repnum"));
//		logger.debug("아이디 : {} ",request.getParameter("userid"));
		int nt_num = Integer.parseInt(request.getParameter("ntnum")); // 글번호 가져오기
		
		int rep_num = Integer.parseInt(request.getParameter("repnum"));
		String user_id = request.getParameter("userid");
		int rep_stat = 0;
		ReplyVo rvo = new ReplyVo();
		rvo.setRep_num(rep_num);
		rvo.setUser_id(user_id);
		rvo.setRep_stat(rep_stat);
		
		int deleteCnt = noticeService.deleteReply(rvo);
		if(deleteCnt > 0 ) {
			logger.debug("댓글 삭제성공");
		}else {
			logger.debug("댓글 삭제실패");
		}
			
		// 댓글 정보 변경후 페이지 재 로딩
		response.sendRedirect("/noticeDetail?nt_num="+nt_num);
	}

}
