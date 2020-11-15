package kr.or.ddit.notice.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.model.ReplyVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class NoticeDetail
 */
@WebServlet("/noticeDetail")
public class NoticeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeServiceI noticeService;
	private static final Logger logger = LoggerFactory.getLogger(NoticeDetail.class);
	
    @Override
	public void init() throws ServletException {
    	noticeService = new NoticeService();
	}   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nt_num = Integer.parseInt(request.getParameter("nt_num"));
		// 게시글 가져오기
		Map<String, Object> map = noticeService.getNotice(nt_num);
		NoticeVo nvo = (NoticeVo) map.get("nvo");
		List<ReplyVo> replyList = (List<ReplyVo>) map.get("replyList");
		String ntgu_code = nvo.getNtgu_code();
		Map<String, Object> ngMap = noticeService.getAllNotice(ntgu_code);
		NoticeGubunVo ngvo = (NoticeGubunVo) ngMap.get("ngvo");
		
		request.setAttribute("nvo", nvo);
		request.setAttribute("replyList", replyList);
		request.setAttribute("ngvo", ngvo);
		
		// 파일가져오기
		List<NoticeFileVo> nfvoList = noticeService.getAllFile(nt_num);
		request.setAttribute("nfvoList", nfvoList);
		
//		logger.debug("reply : {}", replyList);
		request.getRequestDispatcher("/notice/noticeDetail.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
