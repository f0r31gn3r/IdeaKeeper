package lv.javaguru.java3.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"lv.javaguru.java3"})
public class Application{


    public static void main(String[] args) throws Exception {

        // for Jersey (REST)
        //System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "rest");

        // for authentication
        //System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "auth");

        SpringApplication.run(Application.class, args);


    }

}
