package br.edu.utfpr.td.tsi.posto.saude;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
public class DBConfig {
//	@Autowired
//	private DataSource dataSource;

	//@Bean
//	public JdbcTemplate jdbcTemplate() {
	//	return new JdbcTemplate(dataSource);
	//}

	//@Bean(name = "transactionManager")
	//public PlatformTransactionManager txManager() {
	///	DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
	//	return transactionManager;
//	}
}