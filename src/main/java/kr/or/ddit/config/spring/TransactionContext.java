package kr.or.ddit.config.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//스프링 빈중에 PlatformTransactionManager 타입의 빈이 있으면 해당 빈을 
//트랜잭션 매니저로 등록
//DataSourceContext에 등록된 dataSource 스프링 빈을 주입받기 위해 선언
@EnableTransactionManagement // 자바기반으로 트랜잭션을 관리함
@Configuration
public class TransactionContext {
	/*
	 <!-- 임의의 id로 주어도 상관없음  -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<!-- datasource-context.xml에 있는 bean 참조, 다른파일에 있어도 같은 파일에 있는것처럼 사용 가능 -->
		<property name="dataSource" ref="dataSource"/> 
	</bean>
	 */
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager() { // 클래스타입을 상위객체인 인터페이스로 했음
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
	
	//<tx:advice id="txAdvisor" transaction-manager="transactionManager">
	//<aop:config>
	//위 두 부분에 해당하는 java 설정은 없음 => 어노테이션으로 처리해야함
	//@Transactional 어노테이션을 트랜잭션으로 삼을 메소드나, 클래스 단위로 붙여준다
	//스프링 빈을 <bean> 엘레멘트를 통해 일일이 등록=> @Controller, @Service
}
