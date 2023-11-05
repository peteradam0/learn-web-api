package learn.web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LearnWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnWebApiApplication.class, args);
    }

}
