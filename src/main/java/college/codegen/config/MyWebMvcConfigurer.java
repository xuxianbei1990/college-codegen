package college.codegen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: xuxianbei
 * Date: 2022/12/21
 * Time: 18:07
 * Version:V1.0
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
