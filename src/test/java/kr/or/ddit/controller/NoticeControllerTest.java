package kr.or.ddit.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.WebTestConfig;

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
	public void noticeWriteTest() throws Exception {
		String ntgu_code = "NT001";
		MvcResult result = mockMvc.perform(post("/notice/write").
					param("user_id", "a001")
					.param("ntgu_code", ntgu_code)
					.param("nt_tile", "제목")
					.param("editordata", "가나다람"))
				.andExpect(status().is3xxRedirection())
				.andReturn();
		ModelAndView mav = result.getModelAndView();
		assertEquals("redirect:/noticeGubun/gubun?ntgu_code="+ntgu_code, mav.getViewName());
	}
}
