package kr.or.ddit.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;
import kr.or.ddit.notice.model.NoticeVo;

public class NoticeControllerTest extends WebTestConfig{

	@Test
	public void noticeDetailViewTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/notice/detail")
				.param("nt_num", "42"))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("tiles/notice/noticeDetail", mav.getViewName());
	}
	
	@Test
	public void noticeWriteViewTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/notice/write"))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("tiles/notice/noticeWrite", mav.getViewName());
	}
	
	@Test
	public void noticeDeleteTest() throws Exception {
		String ntgu_code = "NT003";
		MvcResult result = mockMvc.perform(get("/notice/delete")
						.param("user_id", "a001")
						.param("nt_num", "9")
						.param("ntgu_code", ntgu_code))
				.andExpect(status().is3xxRedirection())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("redirect:/noticeGubun/gubun?ntgu_code="+ntgu_code, mav.getViewName());
	}
	
	@Test
	public void noticeModifyViewTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/notice/modify")
						.param("nt_num", "9"))
				.andExpect(status().isOk())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("tiles/notice/noticeModify", mav.getViewName());
	}
	
}
