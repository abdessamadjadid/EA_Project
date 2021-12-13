package edu.miu.cs.cs544.EAProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EAProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EAProjectApplication.class, args);

        // need to change classPathResource
//        try {
//            List<CountryRegion> countries = Arrays.asList(new ObjectMapper().readValue(new ClassPathResource("CountryStateCity.json")
//                    .getFile(), CountryRegion[].class));
//            System.out.println(countries);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
