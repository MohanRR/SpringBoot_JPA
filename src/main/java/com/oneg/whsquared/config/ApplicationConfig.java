/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneg.whsquared.config;

import java.util.Properties;

import javax.persistence.Column;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.oneg.whsquared.service.DateTimeService;
import com.oneg.whsquared.util.AuditingDetailsProvider;
import com.oneg.whsquared.util.UsernameAuditorAware;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author Anbukkani G
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableJpaRepositories("com.oneg")
@ComponentScan("com.oneg")
public class ApplicationConfig {

	@Column(name = "instagram_url")
	private String instgramUrl;

	/**
	 * 
	 * @return
	 * 
	 *         To Get current logged in user
	 * 
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {
		return new UsernameAuditorAware();
	}

	/**
	 * @param dateTimeService
	 * @return
	 */
	@Bean
	public DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
		return new AuditingDetailsProvider(dateTimeService);
	}

	@Bean
	DataSource dataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(("com.mysql.jdbc.Driver"));
		dataSourceConfig.setJdbcUrl(("jdbc:mysql://localhost:3306/wh_squared20161118?zeroDateTimeBehavior=convertToNull"));
		dataSourceConfig.setUsername(("root"));
		dataSourceConfig.setPassword(("root"));

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		;
		entityManagerFactoryBean.setPackagesToScan("com.oneg.whsquared.entity");

		Properties jpaProperties = new Properties();

		// Configures the used database dialect. This allows Hibernate to create
		// SQL
		// that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", ("org.hibernate.dialect.MySQLDialect"));
		jpaProperties.put("hibernate.connection.zeroDateTimeBehavior", "convertToNull");

		// Specifies the action that is invoked to the database when the
		// Hibernate
		// SessionFactory is created or closed.
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		// Configures the naming strategy that is used when Hibernate creates
		// new database objects and schema elements
		jpaProperties.put("hibernate.ejb.naming_strategy", ("org.hibernate.cfg.ImprovedNamingStrategy"));

		// If the value of this property is true, Hibernate writes all SQL
		// statements to the console.
		jpaProperties.put("hibernate.show_sql", ("false"));

		// If the value of this property is true, Hibernate will format the SQL
		// that is written to the console.
		jpaProperties.put("hibernate.format_sql", ("false"));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	@Primary
	JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
