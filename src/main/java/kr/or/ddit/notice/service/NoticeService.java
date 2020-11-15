package kr.or.ddit.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.notice.dao.NoticeDaoI;
import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.model.ReplyVo;

@Service("noticeService")
public class NoticeService implements NoticeServiceI {
	private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);

	@Resource(name="noticeDao")
	private NoticeDaoI noticeDao;
	
	@Override
	public int insertNoticeGubun(NoticeGubunVo ngvo) {
		return noticeDao.insertNoticeGubun(ngvo);
	}

	@Override
	public int insertNotice(NoticeVo nvo) {
		return noticeDao.insertNotice(nvo);
	}

	@Override
	public int insertFile(NoticeFileVo nfvo) {
		return noticeDao.insertFile(nfvo);
	}

	@Override
	public List<NoticeGubunVo> getAllNoticeGubun() {
		return noticeDao.getAllNoticeGubun();
	}

	@Override
	public Map<String, Object> getAllNotice(String ntgu_code) {
		NoticeGubunVo ngvo = noticeDao.getNoticeGubun(ntgu_code);
		List<NoticeVo> noticeList = noticeDao.getAllNotice(ntgu_code);
		Map<String, Object> map = new HashMap<>();
		map.put("ngvo", ngvo);
		map.put("noticeList", noticeList);
		return map;
	}

	@Override
	public int updateNotice(NoticeVo nvo) {
		return noticeDao.updateNotice(nvo);
	}

	@Override
	public int deleteNotice(Map<String, Object> info) {
		return noticeDao.deleteNotice(info);
	}

	@Override
	public Map<String, Object> getNotice(int nt_num) {
		Map<String, Object> map = new HashMap<>();
		NoticeVo nvo = noticeDao.getNotice(nt_num);
		List<ReplyVo> replyVo = noticeDao.getAllReply(nt_num);
		map.put("nvo", nvo);
		map.put("replyList", replyVo);
		return map;
	}

	@Override
	public int insertReply(ReplyVo rvo) {
		return noticeDao.insertReply(rvo);
	}

	@Override
	public int deleteReply(ReplyVo rvo) {
		return noticeDao.deleteReply(rvo);
	}

	@Override
	public int updateReply(ReplyVo rvo) {
		return noticeDao.updateReply(rvo);
	}

	@Override
	public Map<String, Object> getAllNoticePage(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String,Object>();
		info.put("noticeList", noticeDao.getAllNoticePage(map));
		
		// 15건, 페이지 사이즈를 7로 가정했을때 3개의 페이지가 나와야한다
		// 15/7 = 2.14... 올림을 하여 3개의 페이지가 필요
		String ntgu_code = (String) map.get("ntgu_code");
		int totalCount = noticeDao.noticeTotalCnt(ntgu_code);
		int pages = (int)Math.ceil((double)totalCount/10);
		info.put("pages", pages);
		
		return info;
	}

	@Override
	public List<NoticeFileVo> getAllFile(int nt_num) {
		return noticeDao.getAllFile(nt_num);
	}

	@Override
	public NoticeFileVo getFile(int filenum) {
//		logger.debug("getFile service단 실행");
		return noticeDao.getFile(filenum);
	}

	@Override
	public int updateNoticeGubun(NoticeGubunVo ngvo) {
		return noticeDao.updateNoticeGubun(ngvo);
	}

	@Override
	public int deleteFile(int filenum) {
		return noticeDao.deleteFile(filenum);
	}

	@Override
	public int deleteAllFile(int nt_num) {
		return noticeDao.deleteAllFile(nt_num);
	}

}
