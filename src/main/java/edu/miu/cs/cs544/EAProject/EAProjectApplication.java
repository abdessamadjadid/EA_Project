package edu.miu.cs.cs544.EAProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EAProjectApplication {

//    @Value("${countrystatecity.forceUpdate}")
//    private Boolean forceUpdate;
//
//    private static Boolean isForceUpdate;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EAProjectApplication.class, args);
    }

//    @Value("${countrystatecity.forceUpdate}")
//    public void setForceUpdate(Boolean value) {
//        EAProjectApplication.isForceUpdate = value;
//    }

}
