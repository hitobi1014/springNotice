package kr.or.ddit.user.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

@Repository("userDao")
public class UserDao implements UserDaoI {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public UserVo getUser(String userid) {
		return sqlSession.selectOne("user.getUser", userid);
	}

}
