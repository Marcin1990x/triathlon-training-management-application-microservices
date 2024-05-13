package pl.koneckimarcin.functionsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FunctionsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionsServiceApplication.class, args);
    }

}
