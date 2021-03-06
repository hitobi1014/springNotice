package kr.or.ddit.config.spring;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

//
//<context:component-scan base-package="kr.or.ddit" use-default-filters="false">
//	<context:include-filter type="annotation" expression="org.aspectj.lang.annotation.Aspect"/>
//</context:component-scan>
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false,
			includeFilters = {@Filter(type=FilterType.ANNOTATION, classes = {Aspect.class})})
//<!-- @Aspect 어노테이션이 붙은 클래스에 대한 설정 처리 -->
//<aop:aspectj-autoproxy/>
@EnableAspectJAutoProxy // 위와 동일한 기능
public class AopContext {

	
}
