package kr.or.ddit.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.notice.dao.NoticeDaoI;
import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.model.ReplyVo;

public class NoticeDaoTest extends ModelTestConfig{
	
	@Resource(name="noticeDao")
	public NoticeDaoI noticeDao;
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeDaoTest.class);
	
	// 게시판 분류 생성
	@Test
	public void insertNoticeGubunTest() {
		/***Given***/
		NoticeGubunVo ngvo = new NoticeGubunVo();
		ngvo.setNtgu_code("NT3421");
		ngvo.setNtgu_name("테스트코드");
		ngvo.setNtgu_stat(1);
		/***When***/
		logger.debug("ngvo값 : {}", ngvo);
		int insertCnt = noticeDao.insertNoticeGubun(ngvo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	// 게시글생성
	@Test
	public void insertNotice() {
		/***Given***/
		NoticeVo nvo = new NoticeVo();
		nvo.setNt_stat(1);
		nvo.setNt_title("테스트코드");
		nvo.setUser_id("a001");
		nvo.setNtgu_code("NT001");
		nvo.setNt_cont("내용 테스트코드");
		nvo.setNtcont_stat(1);
		/***When***/
		int insertCnt = noticeDao.insertNotice(nvo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	// 게시글 등록시 파일 첨부
	@Test
	public void insertFileTest() {
		/***Given***/
		NoticeFileVo nfvo = new NoticeFileVo();
		nfvo.setFilename("파일 테스트");
		nfvo.setFilepath("파일경로테스트");
		nfvo.setNt_num(55);;
		/***When***/
		int insertCnt = noticeDao.insertFile(nfvo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	//전체 게시판 가져오기
	@Test
	public void getAllNoticeGubunTest() {
		/***Given***/
		/***When***/
		List<NoticeGubunVo> ngvoList = noticeDao.getAllNoticeGubun();
		/***Then***/
		assertEquals(19, ngvoList.size());
	}
	
	//특정 게시판 가져오기
	@Test
	public void getNoticeGubunTest() {
		/***Given***/
		String ntgu_code = "NT001";
		/***When***/
		NoticeGubunVo ngvo = noticeDao.getNoticeGubun(ntgu_code); 
		/***Then***/
		assertEquals(ntgu_code, ngvo.getNtgu_code());
	}
	
	// 게시판코드에 해당하는 게시글 전체조회
	@Test
	public void getAllNoticeTest() {
		/***Given***/
		String ntgu_code = "NT001";
		/***When***/
		List<NoticeVo> nvoList = noticeDao.getAllNotice(ntgu_code);
		/***Then***/
		assertEquals(34, nvoList.size());
	}
	
	// 게시글 상세보기
	@Test
	public void getNoticeTest() {
		/***Given***/
		int nt_num = 56;
		/***When***/
		NoticeVo nvo = noticeDao.getNotice(nt_num);
		/***Then***/
		assertEquals(nt_num, nvo.getNt_num());
	}
	
	// 게시글 수정
	@Test
	public void updateNoticeTest() {
		/***Given***/
		NoticeVo nvo = new NoticeVo();
		nvo.setNt_cont("테스트코드 내용 수정");
		nvo.setNt_title("테스트코드 제목 수정");
		nvo.setNtgu_code("NT001");
		nvo.setNt_num(56);
		/***When***/
		int updateCnt = noticeDao.updateNotice(nvo);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	// 게시글 삭제(본인만가능)
	@Test
	public void deleteNoticeTest() {
		/***Given***/
		Map<String, Object> info = new HashMap<>();
		info.put("user_id", "a001");
		info.put("nt_num", 56);
		/***When***/
		int deleteCnt = noticeDao.deleteNotice(info);
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	// 댓글가져오기
	@Test
	public void getAllReplyTest(){
		/***Given***/
		int nt_num = 22;
		/***When***/
		List<ReplyVo> rvoList = noticeDao.getAllReply(nt_num);
		/***Then***/
		assertEquals(2, rvoList.size());
	}
	
	// 댓글 등록
	@Test
	public void insertReplyTest() {
		/***Given***/
		ReplyVo rvo = new ReplyVo();
		rvo.setNt_num(42);
		rvo.setRep_cont("댓글테스트코드");
		rvo.setRep_stat(1);
		rvo.setUser_id("a001");
		/***When***/
		int insertCnt = noticeDao.insertReply(rvo);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	// 댓글 삭제
	@Test
	public void deleteReplyTest() {
		/***Given***/
		ReplyVo rvo = new ReplyVo();
		rvo.setUser_id("a001");
		rvo.setRep_num(19);
		rvo.setRep_stat(0);
		/***When***/
		int deleteCnt = noticeDao.deleteReply(rvo);
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
		rvo.setRep_cont("댓글 수정 테스트코드");
		/***When***/
		int updateCnt = noticeDao.updateReply(rvo);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	// 게시판별 총 게시글 개수구하기
	@Test
	public void noticeTotalCntTest() {
		/***Given***/
		String ntgu_code = "NT001";
		/***When***/
		int noticeTotalCnt = noticeDao.noticeTotalCnt(ntgu_code);
		/***Then***/
		assertEquals(34, noticeTotalCnt);
	}
	
	// 전체 페이지 가져오기
	@Test
	public void getAllNoticePageTest() {
		/***Given***/
		Map<String, Object> map = new HashMap<>();
		map.put("ntgu_code", "NT001");
		map.put("page", 1);
		map.put("pageSize", 10);
		/***When***/
		List<NoticeVo> nvoList = noticeDao.getAllNoticePage(map);
		/***Then***/
		assertEquals(10, nvoList.size());
	}
	
	// 게시글에 존재하는 첨부파일 가져오기
	@Test
	public void getAllFileTest() {
		/***Given***/
		int nt_num = 55;
		/***When***/
		List<NoticeFileVo> nfvoList = noticeDao.getAllFile(nt_num);
		/***Then***/
		assertEquals(1, nfvoList.size());
	}
	
	//파일 다운로드, 파일정보 가져오기
	@Test
	public void getFileTest() {
		/***Given***/
		int filenum = 81;
		/***When***/
		NoticeFileVo nfvo = noticeDao.getFile(filenum);
		/***Then***/
		assertEquals(91, nfvo.getNt_num());
	}
	
	// 게시판 상태코드 수정
	@Test
	public void updateNoticeGubunTest() {
		/***Given***/
		NoticeGubunVo ngvo = new NoticeGubunVo();
		ngvo.setNtgu_code("NT1111");
		ngvo.setNtgu_stat(0);
		/***When***/
		int updateCnt = noticeDao.updateNoticeGubun(ngvo);
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	// 게시글 수정에서 파일 삭제
	@Test
	public void deletetFileTest() {
		/***Given***/
		int filenum = 81;
		/***When***/
		int deleteCnt = noticeDao.deleteFile(filenum);
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	// 게시글 삭제시 파일 삭제
	@Test
	public void deleteAllFileTest() {
//		/***Given***/
		int nt_num = 91;
		/***When***/
		int deleteCnt = noticeDao.deleteAllFile(nt_num); 
		/***Then***/
		assertEquals(4, deleteCnt);
	}
}
