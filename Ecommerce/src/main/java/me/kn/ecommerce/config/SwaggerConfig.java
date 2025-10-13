package me.kn.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI ecommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API Documentation")
                        .description("Tài liệu API cho hệ thống bán hàng Spring Boot")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Đặng Khôi Nguyên")
                                .email("nguyenoke123@gmail.com")
                                .url("https://github.com/MrTroll28/WWW")));
    }
}
