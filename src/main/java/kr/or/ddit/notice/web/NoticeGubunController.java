package kr.or.ddit.notice.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.service.NoticeServiceI;

@Controller
@RequestMapping("/noticeGubun")
public class NoticeGubunController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeGubunController.class);
	@Resource(name="noticeService")
	private NoticeServiceI noticeService;
	
	@RequestMapping(path = "gubun")
	public String gubunView(NoticeGubunVo ntgu, Model model,
			@RequestParam(name="page", required = false, defaultValue = "1")int page,
			@RequestParam(name="pageSize", required = false, defaultValue = "7")int pageSize) {
		logger.debug("페이지 : {}",page);
		PageVO pageVo = new PageVO(page, pageSize);
		Map<String, Object> info = new HashMap<String, Object>();
		
		info.put("ntgu_code", ntgu.getNtgu_code());
		info.put("page", pageVo.getPage());
		info.put("pageSize", pageVo.getPageSize());
		Map<String, Object> map2 = noticeService.getAllNoticePage(info);
		
		Map<String, Object> map = new HashMap<>();
		map = noticeService.getAllNotice(ntgu.getNtgu_code());
		NoticeGubunVo ngvo = (NoticeGubunVo)map.get("ngvo");
		
		model.addAttribute("noticeList", map2.get("noticeList"));
		model.addAttribute("pages", map2.get("pages"));
		model.addAttribute("ngvo", ngvo);
		model.addAttribute("page", pageVo.getPage());
		return "tiles/notice/notice";
	}
}
