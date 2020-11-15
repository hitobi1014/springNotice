package kr.or.ddit.notice.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVO;
import kr.or.ddit.notice.model.NoticeFileVo;
import kr.or.ddit.notice.model.NoticeGubunVo;
import kr.or.ddit.notice.model.NoticeVo;
import kr.or.ddit.notice.model.ReplyVo;

public interface NoticeServiceI {
	/**
	 * 게시판구분 생성(관리자만 생성가능)
	 * @param 게시판 구분의 정보를 담은 vo객체 
	 * @return 게시판구분 생성에 성공시 1이상의 값 반환 실패하면 0 
	 */
	int insertNoticeGubun(NoticeGubunVo ngvo);
	
	/**
	 * 게시글 작성(회원들 누구나 자유롭게 작성가능)
	 * @param 게시글 정보를 담은 vo 객체 
	 * @return 게시글 작성에 성공시 1이상의 값 반환 실패하면 0 
	 */
	int insertNotice(NoticeVo nvo);
	
	/**
	 * 게시글 파일첨부, 게시글 작성시 파일 첨부
	 * @param 파일정보를 담은 vo객체 
	 * @return 파일첨부 성공시 1 이상의 값 반환 실패시 0 
	 */
	int insertFile(NoticeFileVo nfvo);
	
	/**
	 * 게시판 가져오기 (ex.자유게시판 질의게시판 )
	 */
	List<NoticeGubunVo> getAllNoticeGubun();
	
	/**
	 *  전체 게시글을 불러올때 게시글과 게시판구분을 가져오는 메서드
	 * @return noticeVo와 noticeGubunVo를 담은 Map객체
	 * @param notice gubun code
	 */
	Map<String, Object> getAllNotice(String ntgu_code);
	
	/**
	 * 게시글 수정
	 * @param 게시글 정보를 수정할 내용을 담은 vo객체
	 * @return 게시글 수정을 성공하면 1이상 실패하면 0반환
	 */
	int updateNotice(NoticeVo nvo);
	
	/**
	 * 게시글 삭제 , 본인만 삭제할수있음
	 * @param 삭제할 글 번호와 본인 아이디를 담은 map객체
	 * @return 삭제 성공시 1이상의 값 실패하면 0
	 */
	int deleteNotice(Map<String, Object> info);
	
	/**
	 * 게시글, 해당하는 글에 대한 댓글 가져오기
	 * @param 게시글번호
	 * @return 게시글번호에 해당하는 글과 댓글 vo, NoticeVo, ReplyVo
	 */
	Map<String, Object> getNotice(int nt_num);
	
	/**
	 * 댓글 등록하기
	 * @param 댓글을 담은 vo 객체
	 * @return 댓글등록에 성공하면 1이상 반환 아니면 0
	 */
	int insertReply(ReplyVo rvo);
	
	/**
	 * 댓글 삭제하기
	 * @param 유저아이디와, 댓글번호를 담은 map 객체
	 * @return 삭제 성공시 1이상 값 반환 실패시 0
	 */
	int deleteReply(ReplyVo rvo);
	
	/**
	 * 댓글 수정하기
	 * @param 댓글번호, 유저아이디, 댓글내용
	 * @return 수정 성공시 1이상 실패시0 반환
	 */
	int updateReply(ReplyVo rvo);
	
	//PageVO pageVo,String ntgu_code
	Map<String, Object> getAllNoticePage(Map<String, Object> map);
	
	/**
	 * 게시글에 존재하는 첨부파일 가져오기
	 * @param 게시글 번호
	 * @return 게시글 번호에 해당하는 파일 vo객체 리스트 
	 */
	List<NoticeFileVo> getAllFile(int nt_num);
	
	/**
	 * 파일 다운로드를 받기위한 파일정보 가져오기
	 * @param 파일번호
	 * @return 파일번호에 해당하는 파일vo
	 */
	NoticeFileVo getFile(int filenum);
	
	/**
	 * 게시판 사용/ 미사용
	 */
	int updateNoticeGubun(NoticeGubunVo ngvo);
	
	/**
	 * 게시글 수정시 파일 삭제
	 */
	int deleteFile(int filenum);
	
	/**
	 * 게시글 삭제시 파일 삭제
	 */
	int deleteAllFile(int nt_num);
}
