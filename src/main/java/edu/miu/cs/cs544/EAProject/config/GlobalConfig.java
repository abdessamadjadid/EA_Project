package edu.miu.cs.cs544.EAProject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class GlobalConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
