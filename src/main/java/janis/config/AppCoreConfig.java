package janis.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"janis"})
//@Import({DataSourceConfig.class, HibernateConfig.class,
//        TransactionConfig.class, AppPropertiesConfig.class})
public class AppCoreConfig {

        public static void main(String[] args) throws Exception {
                SpringApplication.run(AppCoreConfig.class, args);
       }

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
