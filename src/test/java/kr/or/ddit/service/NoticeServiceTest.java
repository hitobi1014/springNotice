package kr.or.ddit.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.model.ReplyVo;
import kr.or.ddit.notice.service.NoticeServiceI;

public class NoticeServiceTest extends ModelTestConfig{
	
	@Resource(name = "noticeService")
	NoticeServiceI noticeService;
	
	// 게시판 생성
	@Test
	public void insertNoticeGubunTest() {
		/***Given***/
		NoticeGubunVo ngvo = new NoticeGubunVo();
		ngvo.setNtgu_code("NT5555");
		ngvo.setNtgu_name("서비스테스트");
		ngvo.setNtgu_stat(1);
		/***When***/
		int insertCnt = noticeService.insertNoticeGubun(ngvo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	// 게시글 작성
	@Test
	public void insertNoticeTest() {
		/***Given***/
		NoticeVo nvo = new NoticeVo();
		nvo.setNt_cont("서비스테스트");
		nvo.setNt_stat(1);
		nvo.setNt_title("서비스테스트");
		nvo.setNtcont_stat(1);
		nvo.setNtgu_code("NT2222");
		nvo.setUser_id("a001");

		/***When***/
		int insertCnt = noticeService.insertNotice(nvo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	// 파일첨부
	@Test
	public void insertFileTest() {
		/***Given***/
		NoticeFileVo nfvo = new NoticeFileVo();
		nfvo.setFilename("서비스 경로");
		nfvo.setFilepath("서비스 경로");
		nfvo.setNt_num(57);
		/***When***/
		int insertCnt = noticeService.insertFile(nfvo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	// 게시판 가져오기
	@Test
	public void getAllNoticeGubunTest() {
		/***Given***/
		/***When***/
		List<NoticeGubunVo> ngvoList = noticeService.getAllNoticeGubun();
		/***Then***/
		assertEquals(19, ngvoList.size());
	}
	
	// 게시판별 게시글 가져오기
	@Test
	public void getAllNoticeTest() {
		/***Given***/
		String ntgu_code = "NT2222";
		/***When***/
		Map<String, Object> map = noticeService.getAllNotice(ntgu_code);
		List<NoticeVo> nvoList = (List<NoticeVo>) map.get("noticeList");
		NoticeGubunVo ngvo = (NoticeGubunVo)map.get("ngvo");
		/***Then***/
		assertEquals(5, nvoList.size() );
		assertEquals(ntgu_code,ngvo.getNtgu_code() );
	}
	
	// 게시글 수정
	@Test
	public void updateNoticeTest() {
		/***Given***/
		NoticeVo nvo = new NoticeVo();
		nvo.setNt_cont("서비스 내용 수정");
		nvo.setNt_num(57);
		nvo.setNt_title("서비스 제목 수정");
		nvo.setNtgu_code("NT2222");
		/***When***/
		int updateCnt = noticeService.updateNotice(nvo);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	// 게시글 삭제
	@Test
	public void deleteNoticeTest() {
		/***Given***/
		Map<String, Object>info = new HashMap<>();
		info.put("user_id", "a001");
		info.put("nt_num", 57);
		/***When***/
		int deleteCnt = noticeService.deleteNotice(info);
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	//댓글 가져오기
	@Test
	public void getNoticeTest() {
		/***Given***/
		int nt_num = 42;
		/***When***/
		Map<String, Object> map = noticeService.getNotice(nt_num);
		List<ReplyVo> rvList = (List<ReplyVo>) map.get("replyList");
		NoticeVo nvo = (NoticeVo) map.get("nvo");
		/***Then***/
		assertEquals(10, rvList.size());
		assertEquals(nt_num, nvo.getNt_num());
	}
	
	// 댓글등록
	@Test
	public void insertReplyTest() {
		/***Given***/
		ReplyVo rvo = new ReplyVo();
		rvo.setRep_cont("댓글 서비스 테스트");
		rvo.setNt_num(42);
		rvo.setRep_stat(1);
		rvo.setUser_id("a001");
		/***When***/
		int insertCnt = noticeService.insertReply(rvo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	// 댓글삭제
	@Test
	public void deleteReplyTest() {
		/***Given***/
		ReplyVo rvo = new ReplyVo();
		rvo.setUser_id("a001");
		rvo.setRep_num(20);
		/***When***/
		int deleteCnt = noticeService.deleteReply(rvo);
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	// 댓글 수정
	@Test
	public void updateReplyTest() {
		/***Given***/
		ReplyVo rvo = new ReplyVo();
		rvo.setRep_num(18);
		rvo.setUser_id("a001");
		rvo.setRep_cont("댓글내용 수정 서비스");

		/***When***/
		int updateCnt = noticeService.updateReply(rvo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	// 게시글에 존재하는 첨부파일가져오기
	@Test
	public void getAllFileTest() {
		/***Given***/
		int nt_num = 57;
		/***When***/
		List<NoticeFileVo> nfvoList = noticeService.getAllFile(nt_num);
		/***Then***/
		assertEquals(5, nfvoList.size());
	}
	
	// 파일다운로드를 위해 파일정보 가져오기
	@Test
	public void getFileTest() {
		/***Given***/
		int filenum =40;
		/***When***/
		NoticeFileVo nfvo = noticeService.getFile(filenum);
		/***Then***/
		assertEquals(filenum, nfvo.getFilenum());
	}
	
	//게시판 사용
	@Test
	public void updateNoticeGubunTest() {
		/***Given***/
		NoticeGubunVo ngvo = new NoticeGubunVo();
		ngvo.setNtgu_code("NT2222");
		ngvo.setNtgu_stat(0);
		/***When***/
		int updateCnt = noticeService.updateNoticeGubun(ngvo);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	//게시글 수정시 파일 삭제
	@Test
	public void deleteFileTest() {
		/***Given***/
		int filenum = 40;
		/***When***/
		int updateCnt = noticeService.deleteFile(filenum);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	//게시글 삭제시 파일 전부삭제
	@Test
	public void deleteAllFileTest() {
		/***Given***/
		int nt_num = 90;
		/***When***/
		int deleteCnt = noticeService.deleteAllFile(nt_num);
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
