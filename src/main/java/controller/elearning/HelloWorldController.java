package controller.elearning;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping({"/hello/{name}","/hello/"})
    public String helloWorld(@PathVariable(required = false) String name) {
        if (name == null) return "hello world";
        else return "Hello "+ name;
    }
}
