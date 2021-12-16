package edu.miu.cs.cs544.EAProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition( info = @Info(
            title = "Course Registration API",
            description = "API Definitions of course registrations Microservice",
            version = "1.0.1"

))
public class EAProjectApplication {

//    @Value("${countrystatecity.forceUpdate}")
//    private Boolean forceUpdate;
//
//    private static Boolean isForceUpdate;

    public static void main(String[] args) {
         SpringApplication.run(EAProjectApplication.class, args);
    }

//    @Value("${countrystatecity.forceUpdate}")
//    public void setForceUpdate(Boolean value) {
//        EAProjectApplication.isForceUpdate = value;
//    }

}
