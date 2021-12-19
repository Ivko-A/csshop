package softuni.csshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CsshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsshopApplication.class, args);
    }



}
