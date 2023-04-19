package com.gdu.app06.config;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@EnableAspectJAutoProxy // AOP 동작은 허용한다
@EnableTransactionManagement // 트랜잭션 처리를 허용한다
@Configuration
public class DBConfig {
	// DriverManagerDataSource Bean
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("GDJ61");
		dataSource.setPassword("1111");
		return dataSource;
	}
	// JdbcTemplate Bean (Connection, PreparedStatement, ResultSet을 이용하여 동작하는 스프링 클래스)
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource()); // DriverManagerDataSource bean을 JdbcTemplate 생성자에 집어넣음
	}
	// DataSourceTransactionManager Bean
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	// 아래부분은 AOP를 이용해서 트랜잭션 처리를 하기 위한 Bean
	
	// TransactionInterceptor Bean
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute();
		ruleBasedTransactionAttribute.setName("*"); // 이름 상관없이 작동할 수 있도록
		ruleBasedTransactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		source.setTransactionAttribute(ruleBasedTransactionAttribute);
		return new TransactionInterceptor(transactionManager(), source);
	}
	
	// Advisor Bean
	@Bean
	public Advisor advisor() {
		// 포인트컷 설정(어드바이스(트랜잭션)를 동작시킬 메소드)
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("excutation(* com.gdu.app06.service.BoardServiceImpl.*Tx(..))");
		
		return new DefaultPointcutAdvisor(pointcut, transactionInterceptor());
	}
}
