package kr.or.ddit.config.spring;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//<context:component-scan base-package="kr.or.ddit" use-default-filters="false">
//<context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>
//<context:include-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
//</context:component-scan>

//@ImportResource({"classpath:kr/or/ddit/config/spring/aop-context.xml"}) <- 이런식으로 사용가능
//@Import({AopContext.class,DataSource.class,TransactionContext.class})
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false,
			includeFilters = {@Filter(type=FilterType.ANNOTATION, classes = {Service.class, Repository.class})})
public class RootContext {
	
	@Bean
	public MessageSource messageSource () {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:kr/or/ddit/message/error","classpath:kr/or/ddit/message/msg"); // xml에서 list형식으로 썻던것을 가변인자로 쓸 수 있다
		return messageSource;
	}
}
