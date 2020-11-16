package kr.or.ddit;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.config.spring.ApplicationContext;
import kr.or.ddit.config.spring.DatasourceContextDev;
import kr.or.ddit.config.spring.RootContext;
import kr.or.ddit.config.spring.TransactionContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootContext.class, ApplicationContext.class, DatasourceContextDev.class,TransactionContext.class})
@WebAppConfiguration	//스프링 컨테이너를 웹기반에서 동작하는 컨테이너로 생성하는 옵션 => @Controller, @RequestMapping을 썻기때문에
public class WebTestConfig {
	
	
	@Autowired
	protected WebApplicationContext context;
	
	protected MockMvc mockMvc; 	//dispatcher servlet 역할을 하는 객체
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	// Before(setup) => Test => After(tearDown) <- 많이 쓰는 함수명
	@Before
	public void setup()	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
	
	//get(),post() : get/post 요청
	//param(파라미터명, 파라미터값) : 요청시 보낼 파라미터

	//status() : 스프링 프레임워크에 의해 요청이 처리되고 생성된 응답 코드
	//view() : 스프링 프레임워크에 의해 요청이 처리되는 과정에서 반환된 viewName
	//model() : 컨트롤러에서 설정한 속성값을 담는 객체
	//request() : 요청 객체
		
	@Ignore
	@Test
	public void test() {
		
	}

}
