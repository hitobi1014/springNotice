package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface UserServiceI {
	/**
	 * 회원 로그인시 사용되는 메소드
	 * @return  userid에 맞는 정보를 담은 vo객체
	 * @param 로그인시 userid를 인자로 받음 
	 */
	UserVo getUser(String userid);
}
