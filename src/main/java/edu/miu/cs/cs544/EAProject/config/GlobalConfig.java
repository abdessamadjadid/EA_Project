package edu.miu.cs.cs544.EAProject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "edu.miu.cs.cs544.EAProject.repository")
@EnableTransactionManagement
@Configuration
public class GlobalConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
