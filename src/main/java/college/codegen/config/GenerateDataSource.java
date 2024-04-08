package college.codegen.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * User: EDY
 * Date: 2024/4/8
 * Time: 10:32
 * Version:V1.0
 */

@ConfigurationProperties(prefix = "college.generate")
@Validated
@Data
public class GenerateDataSource {


    @NotNull(message = "url 数据库链接不能为空")
    private String url;

    @NotNull(message = "用户不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

}
