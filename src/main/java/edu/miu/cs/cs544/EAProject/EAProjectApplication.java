package edu.miu.cs.cs544.EAProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EAProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EAProjectApplication.class, args);

        try {
            List<CountryRegion> countries = Arrays.asList(new ObjectMapper().readValue(new ClassPathResource("CountryStateCity.json")
                    .getFile(), CountryRegion[].class));
            System.out.println(countries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
