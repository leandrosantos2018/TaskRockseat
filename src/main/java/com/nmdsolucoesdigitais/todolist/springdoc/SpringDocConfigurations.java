package com.nmdsolucoesdigitais.todolist.springdoc;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                       ).info(new Info()
                        .title("Task")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Squard Backend")
                                .email("backend@nmd.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("www.nmdsolucoesdigitais.com.br")));
    }
}
