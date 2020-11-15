package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.UserDaoI;
import kr.or.ddit.user.model.UserVo;

@Service("userService")
public class UserService implements UserServiceI {
	
	@Resource(name="userDao")
	private UserDaoI userDao;
	
	@Override
	public UserVo getUser(String userid) {
		return userDao.getUser(userid);
	}

}
