package com.lff.conf;

import javax.sql.DataSource;

import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DBConfiguration {
	
	@Bean(name = "sqlManager")
	@Primary
	public SQLManager getSqlManagerFactoryBean(DataSource dataSource) throws Exception {
		System.out.println(dataSource.getClass());
		if(dataSource instanceof DruidDataSource) {
			improveSettings((DruidDataSource)dataSource);
		}	
 
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(dataSource);
		factory.setCs(source);
		factory.setDbStyle(new MySqlStyle());
		factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
//		factory.setNc(new DefaultNameConversion());
		factory.setNc(new UnderlinedNameConversion());
		return factory.getObject();
	}
	
	
	private DruidDataSource improveSettings(DruidDataSource dataSource) {
		dataSource.setMinIdle(2);
		dataSource.setMaxActive(50);
		dataSource.setInitialSize(2);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxOpenPreparedStatements(40);
		
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		return dataSource;
	}
}
