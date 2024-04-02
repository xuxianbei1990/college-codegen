package college.codegen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: EDY
 * Date: 2024/3/30
 * Time: 13:42
 * Version:V1.0
 */
@RestController
public class SampleController {

    @GetMapping("/sample")
    public String sample() {
        return "Hello, World!";
    }
}
