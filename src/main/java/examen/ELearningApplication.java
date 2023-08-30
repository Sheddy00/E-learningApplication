package examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Pourque
@ComponentScan({"examen","controller.elearning","dao","model","repository","service"})
public class ELearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearningApplication.class, args);
    }

}