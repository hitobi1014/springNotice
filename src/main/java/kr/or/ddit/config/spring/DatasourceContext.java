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
@PropertySource("classpath:kr/or/ddit/db/db.properties")
public class DatasourceContext {
	@Autowired
	private Environment env; // 환경설정 및 property 접근하기
	/*
		<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
			<property name="url" value="${jdbc.url}"/>
			<property name="driverClassName" value="${jdbc.driver}"/>
			<property name="username" value="${jdbc.username}"/>
			<property name="password" value="${jdbc.password}"/>
		</bean>
		property => setter
	*/
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
	/*
	 <!-- mybatisUtil 역할 -->
 	<!-- sqlSessionFactoryBean 객체는 객체를 생성하는 역할을 담당하는 객체 
	스프링 프레임워크는 스프링 빈을 생성시 객체가 FactoryBean 인터페이스를 구현한 경우
	인터페이스에 정의되어 있는 getObject() 메소드가 리턴하는 객체를 스프링 빈으로 등록한다 -->
	<bean id="sqlSessionFactory"  class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="configLocation" value="classpath:kr/or/ddit/db/mybatis-config.xml"/>
		<property name="dataSource" ref="dataSource"/>
	</bean>
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 쉽게 resource 타입 구하기 => new ClassPathResource를 통해 클래스 경로를 적어줌
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("kr/or/ddit/db/mybatis-config.xml"));
		// 다른 bean 주입 받기 => 해당 메소드 호출,  새롭게 메모리생성되는게아님 => 스프링에서 관리해줌
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}
	
	//SqlSessionFactoryBean=> 실제로 Object
	
	/*
	 <!-- sqlSession --><!-- 생성자가 필요로 하기에 위에서 만들어준 factoryBean을 참조한다 -->
	<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="sqlSessionFactory"/> 
	</bean>
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean());
		
		return sqlSessionTemplate;
	}
}
