package haroldo.stub.api.execution.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Hello World");

        return "<html><body><h1>Hello, Haroldo!</h1><p>This is a dynamic HTTP page.</p></body></html>";
    }
}
