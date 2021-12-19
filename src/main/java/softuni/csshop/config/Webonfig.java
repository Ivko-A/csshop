package softuni.csshop.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.csshop.interceptor.StatsInterceptor;

@Component
public class Webonfig implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;

    public Webonfig(StatsInterceptor statsInterceptor) {
        this.statsInterceptor = statsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(statsInterceptor);
    }
}
