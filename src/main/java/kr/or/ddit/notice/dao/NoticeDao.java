package kr.or.ddit.notice.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.model.ReplyVo;

@Repository("noticeDao")
public class NoticeDao implements NoticeDaoI {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeDao.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertNoticeGubun(NoticeGubunVo ngvo) {
		return sqlSession.insert("notice.insertNoticeGubun", ngvo);
	}

	@Override
	public int insertNotice(NoticeVo nvo) {
		return sqlSession.insert("notice.insertNotice", nvo);
	}

	@Override
	public int insertFile(NoticeFileVo nfvo) {
		return sqlSession.insert("notice.insertFile", nfvo);
	}

	@Override
	public List<NoticeGubunVo> getAllNoticeGubun() {
		return sqlSession.selectList("notice.getAllNoticeGubun");
	}

	@Override
	public List<NoticeVo> getAllNotice(String ntgu_code) {
		return sqlSession.selectList("notice.getAllNotice", ntgu_code);
	}

	@Override
	public int updateNotice(NoticeVo nvo) {
		return sqlSession.update("notice.updateNotice", nvo);
	}

	@Override
	public int deleteNotice(Map<String, Object> info) {
		return sqlSession.update("notice.deleteNotice", info);
	}

	@Override
	public NoticeGubunVo getNoticeGubun(String ntgu_code) {
		return sqlSession.selectOne("notice.getNoticeGubun", ntgu_code);
	}

	@Override
	public NoticeVo getNotice(int nt_num) {
		return sqlSession.selectOne("notice.getNotice", nt_num);
	}

	@Override
	public List<ReplyVo> getAllReply(int nt_num) {
		return sqlSession.selectList("notice.getAllReply", nt_num);
	}

	@Override
	public int insertReply(ReplyVo rvo) {
		return sqlSession.insert("notice.insertReply",rvo);
	}

	@Override
	public int deleteReply(ReplyVo rvo) {
		return sqlSession.insert("notice.deleteReply",rvo);
	}

	@Override
	public int updateReply(ReplyVo rvo) {
		return sqlSession.update("notice.updateReply",rvo);
	}

	@Override
	public int noticeTotalCnt(String ntgu_code) {
		return sqlSession.selectOne("notice.noticeTotalCnt",ntgu_code);
	}

	@Override
	public List<NoticeVo> getAllNoticePage(Map<String, Object> map) {
		return sqlSession.selectList("notice.getAllNoticePage", map);
	}

	@Override
	public List<NoticeFileVo> getAllFile(int nt_num) {
		return sqlSession.selectList("notice.getAllFile",nt_num);
	}

	@Override
	public NoticeFileVo getFile(int filenum) {
		return sqlSession.selectOne("notice.getFile",filenum);
	}

	@Override
	public int updateNoticeGubun(NoticeGubunVo ngvo) {
		return sqlSession.update("notice.updateNoticeGubun",ngvo);
	}

	@Override
	public int deleteFile(int filenum) {
		return sqlSession.update("notice.deleteFile",filenum);
	}

	@Override
	public int deleteAllFile(int nt_num) {
		return sqlSession.update("notice.deleteAllFile",nt_num);
	}
	

}
