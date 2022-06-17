package fr.sg.bankaccountkata.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String BANK_ACCOUNT = "Bank Account Kata";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.sg.bankaccountkata.web"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(BANK_ACCOUNT, "Make withdrawals and deposits, get your balance and see your operations history from your bank account."));

    }
}
