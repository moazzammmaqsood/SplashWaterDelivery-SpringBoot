package com.splash.configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.activation.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableTransactionManagement
//@PropertySource({"classpath:application.properties"})
//@ComponentScan({"com.splash"})
public class ConfigurationClass {
	
//	@Autowired
//	private Environment env;
//
//	public ConfigurationClass(){
//		super();
//	}
//	
//	@Bean
//	public DataSource MysqlDataSource() {
//		final BasicDataSource dataSource= new BasicDataSource();
//		dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("")));
//	}
}
