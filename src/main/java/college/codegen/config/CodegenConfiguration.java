package college.codegen.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CodegenProperties.class, GenerateDataSource.class})
public class CodegenConfiguration {
}
