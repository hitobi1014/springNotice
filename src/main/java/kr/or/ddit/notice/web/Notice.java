package kr.or.ddit.notice.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.notice.service.NoticeServiceI;

/**
 * Servlet implementation class FreeNotice
 */
@WebServlet("/notice")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeServiceI noticeService;
	private static final Logger logger = LoggerFactory.getLogger(Notice.class);
	
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. page 파라미터를 받는다
		// 2. page 파라미터가 없을 경우 1이라고 가정한다
		// 3. memberService.selectAllMember메서드를 수정 / 새로운 메서드 만들기
		String ntgu_code = request.getParameter("ntgu_code");
		logger.debug("분류코드 : {}",ntgu_code);
		Map<String, Object> info = new HashMap<>();
		// page
		String page_str = request.getParameter("page");
		int page = page_str == null ? 1 : Integer.parseInt(page_str);
		request.setAttribute("page", page);

		// pageSize
		String pageSize_str = request.getParameter("pageSize");
		int pageSize = pageSize_str == null ? 10 : Integer.parseInt(pageSize_str);
		request.setAttribute("pageSize", pageSize);
		// pageVo : page, pageSize
		PageVO pageVo = new PageVO(page,pageSize);
		
		info.put("ntgu_code", ntgu_code);
		info.put("page", pageVo.getPage());
		info.put("pageSize", pageVo.getPageSize());
		
//		logger.debug("게시판분류코드 : {}, 페이지 : {}, 페이지사이즈 : {}",info.get("ntgu_code"),info.get("page"),info.get("pageSize"));
		
		Map<String, Object> map2 = noticeService.getAllNoticePage(info);

		// 게시판분류 가져오기
		Map<String, Object> map = new HashMap<>();
		map = noticeService.getAllNotice(ntgu_code);
		NoticeGubunVo ngvo = (NoticeGubunVo)map.get("ngvo");
		
		request.setAttribute("noticeList", map2.get("noticeList"));
		request.setAttribute("pages", map2.get("pages"));
		request.setAttribute("ngvo", ngvo);
		
		request.getRequestDispatcher("/notice/notice.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
