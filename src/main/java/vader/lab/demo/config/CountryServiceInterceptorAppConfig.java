package vader.lab.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import vader.lab.demo.interceptor.CountryServiceInterceptor;

@Component
public class CountryServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    CountryServiceInterceptor countryServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(countryServiceInterceptor);
    }
}
