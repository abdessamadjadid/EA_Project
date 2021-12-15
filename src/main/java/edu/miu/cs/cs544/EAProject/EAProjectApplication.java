package edu.miu.cs.cs544.EAProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs544.EAProject.controller.CountryController;
import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EAProjectApplication {

    @Value("${countrystatecity.forceUpdate}")
    private Boolean forceUpdate;

    private static Boolean isForceUpdate;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EAProjectApplication.class, args);
        CountryController countryController = context.getBean(CountryController.class);

        if (isForceUpdate) {
            try {
                List<CountryRegion> countries = Arrays.asList(new ObjectMapper().readValue(
                        new ClassPathResource("CountryStateCity.json")
                                .getFile(), CountryRegion[].class));
                countryController.saveCountries(countries);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Value("${countrystatecity.forceUpdate}")
    public void setForceUpdate(Boolean value) {
        EAProjectApplication.isForceUpdate = value;
    }

}
