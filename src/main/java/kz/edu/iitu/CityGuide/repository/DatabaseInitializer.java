package kz.edu.iitu.CityGuide.repository;

import kz.edu.iitu.CityGuide.repository.entity.User;
import kz.edu.iitu.CityGuide.repository.entity.User.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class DatabaseInitializer {
    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            User admin = User.builder()
                    .role(Role.ADMIN.toString())
                    .email("splitter4774@gmail.com")
                    .username("admin")
                    .password("")
                    .salt("")
                    .build();
            Iterable<User> users = Collections.singletonList(admin);
            repository.saveAll(users);
            repository.findAll().forEach(user -> log.info("Preloaded " + user));
        };
    }
}
