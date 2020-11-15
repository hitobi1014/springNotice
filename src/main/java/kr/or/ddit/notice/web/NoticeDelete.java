package kr.or.ddit.notice.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class NoticeDelete
 */
@WebServlet("/noticeDelete")
public class NoticeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(NoticeDelete.class);
	private NoticeServiceI noticeService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ntgu_code = request.getParameter("ntgu");
		String user_id = request.getParameter("userid");
		int nt_num = Integer.parseInt(request.getParameter("ntnum"));
		logger.debug("ntgu_code : {}, user_id : {}, nt_num : {}",ntgu_code,user_id,nt_num);
		//삭제하기위해 map형식으로 값 넘겨줘야함 xml참고
		Map<String, Object> info = new HashMap<>();
		info.put("user_id", user_id);
		info.put("nt_num", nt_num);
		int delCnt = noticeService.deleteNotice(info);
		if(delCnt >0) {
			logger.debug("삭제성공");
		}else {
			logger.debug("삭제실패");
		}
		
		int deleteCnt = noticeService.deleteAllFile(nt_num);
		if(deleteCnt >0) {
			logger.debug("파일삭제 성공");
		}else {
			logger.debug("파일삭제 실패");
		}
		
		response.sendRedirect("/notice?ntgu_code="+ntgu_code);
	}

}
