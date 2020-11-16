package kr.or.ddit;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.config.spring.DatasourceContextDev;
import kr.or.ddit.config.spring.RootContext;
import kr.or.ddit.config.spring.TransactionContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootContext.class, TransactionContext.class, DatasourceContextDev.class})
public class ModelTestConfig {

	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Ignore
	@Test
	public void dummy() {
		
	}
	
	// db 테스트전 데이터를 초기화하는 스크립트 실행
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		populator.setContinueOnError(false);
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
}
