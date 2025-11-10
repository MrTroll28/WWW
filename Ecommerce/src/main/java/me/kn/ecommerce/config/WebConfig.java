package me.kn.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LayoutInterceptor layout;
    public WebConfig(LayoutInterceptor l) { this.layout = l; }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(layout)
                .excludePathPatterns("/auth/**");;
    }
}
