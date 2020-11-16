package kr.or.ddit.config.spring;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
//<context:property-placeholder location="classpath:kr/or/ddit/config/db/DB.properties"/>
@PropertySource("classpath:kr/or/ddit/config/db/DB_dev.properties")
public class DatasourceContextDev {
	@Autowired
	private Environment env; // 환경설정 및 property 접근하기
	
	@Bean
	public BasicDataSource dataSource() { // BasicDataSource 또는 상위클래스인 DataSource로 해도 무방
		BasicDataSource datasource = new BasicDataSource();
		//getProperty를 이용해 db.properties에 있는 키 값들로 value 가져오기
		datasource.setUrl(env.getProperty("jdbc.url"));
		datasource.setDriverClassName(env.getProperty("jdbc.driver"));
		datasource.setUsername(env.getProperty("jdbc.username"));
		datasource.setPassword(env.getProperty("jdbc.password"));
		return datasource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 쉽게 resource 타입 구하기 => new ClassPathResource를 통해 클래스 경로를 적어줌
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("kr/or/ddit/db/mybatis-config.xml"));
		// 다른 bean 주입 받기 => 해당 메소드 호출,  새롭게 메모리생성되는게아님 => 스프링에서 관리해줌
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean());
		
		return sqlSessionTemplate;
	}
}
