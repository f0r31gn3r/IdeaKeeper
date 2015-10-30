package lv.javaguru.java3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"lv.javaguru.java3"})
//@Import({DataSourceConfig.class, HibernateConfig.class,
//        TransactionConfig.class, AppPropertiesConfig.class})
public class AppCoreConfig {


}

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//
//
//@SpringBootApplication
//@ComponentScan(basePackages = {"lv.javaguru.java3"})
//
//public class AppCoreConfig {
//
//        public static void main(String[] args) throws Exception {
//                SpringApplication.run(AppCoreConfig.class, args);
//        }
//
//}
