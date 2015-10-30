package lv.javaguru.java3.config;

import lv.javaguru.java3.config.liquibase.LiquibaseSchemaUpdate;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DataSourceConfig {

    @Autowired
    private LiquibaseSchemaUpdate liquibaseSchemaUpdate;


    @Bean(destroyMethod = "close")
    public DataSource dataSource(
            @Value("${database.driverClass}") String driver,
            @Value("${database.jdbcUrl}") String url,
            @Value("${database.userName}") String user,
            @Value("${database.password}") String password) throws PropertyVetoException {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);

        liquibaseSchemaUpdate.execute(dataSource);

        return dataSource;
    }

}
