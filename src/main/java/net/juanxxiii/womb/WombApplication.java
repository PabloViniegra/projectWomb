package net.juanxxiii.womb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("net.juanxxiii.womb.database.repositories")
public class WombApplication {

    public static void main(String[] args) {
        SpringApplication.run(WombApplication.class, args);
    }

}
