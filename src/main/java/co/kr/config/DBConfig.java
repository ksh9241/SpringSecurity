package co.kr.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration //어노테이션 기반 트랜잭션 관리를 사용합니다. <tx:annotation-driven>
@EnableTransactionManagement //DataSourceTransactionManager 빈을 찾아 Transaction Manager로 사용한다.
@PropertySource("classpath:database.properties") // Database Connection 정보를 담아둔 properties를 참조한다.
public class DBConfig implements TransactionManagementConfigurer{
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String pwd;
	
	@Bean //HikariConnectionPool에 DB 정보를 넣은 뒤 사용자가 지정한 DB의 커넥션 풀을 반환한다. 
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(pwd);
		return new HikariDataSource(hikariConfig);
	}

	@Override //트랜잭션을 관리하는 메소드 (commit, rollback, ..)
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
