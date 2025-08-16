package haroldo.stub.api.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0] != null)
            System.setProperty("server.port", args[0]);
        SpringApplication.run(Main.class, args);
    }
}
