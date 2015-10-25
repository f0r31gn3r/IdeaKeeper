package lv.javaguru.java3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"lv.javaguru.java3"})
@Import({DataSourceConfig.class, HibernateConfig.class,
        TransactionConfig.class, AppPropertiesConfig.class})
public class AppCoreConfig {


}
