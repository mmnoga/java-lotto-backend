package pl.lotto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("LottoGame API")
                                .description("Generate a lotto ticket and check game results.")
                                .version("0.0.2")
                                .contact(new Contact()
                                        .url("https://github.com/mmnoga")
                                )
                );
    }

}
