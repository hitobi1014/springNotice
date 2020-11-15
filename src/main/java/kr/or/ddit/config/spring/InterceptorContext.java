package kr.or.ddit.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//<mvc:annotation-driven/>
@EnableWebMvc
public class InterceptorContext extends WebMvcConfigurerAdapter{
	
	
	/*
		<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<bean class="kr.or.ddit.mvc.interceptor.PerformanceCheckInterceptor"></bean>
		</mvc:interceptor>
		
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<mvc:exclude-mapping path="/login/**"/>
			<mvc:exclude-mapping path="/js/**"/>
			<mvc:exclude-mapping path="/css/**"/>
			<mvc:exclude-mapping path="/resources/**"/>
			<bean class="kr.or.ddit.mvc.interceptor.SessionCheckInterceptor"/>
		</mvc:interceptor>
	</mvc:interceptors>
	@Override //인터셉터 등록
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PerformanceCheckInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new SessionCheckInterceptor()).addPathPatterns("/**")
		.excludePathPatterns("/login/**","/js/**","/css/**","resources/**");
	}
	 */
	
}
