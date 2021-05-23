package co.kr.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"co.kr.security.mapper"})
public class MybatisConfig {
	
	//mybatis sqlSessionFactoryBean
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource); 						// DBConfig에서 연결한 DB
		factory.setTypeAliasesPackage("co.kr.security.model"); // TypeAliases 클래스명으로 바꿔줌. (resultType, parameterType)
		factory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapper/*.xml"));		// src/main/resources/mapper/모든명.xml을 읽음
		
		System.out.println("factory===="+factory);
		return factory.getObject();
	}
}
