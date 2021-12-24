package kz.edu.iitu.CityGuide.repository;

import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
@Log4j2
class LoadDatabase {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoadDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            User admin = User.builder()
                    .role(User.ROLE_ADMIN)
                    .email("admin@test.com")
                    .username("gokublack")
                    .password(passwordEncoder.encode("password"))
                    .bigDecimal(new BigDecimal("10.3298573589"))
                    .build();
            log.info("Preloading " + repository.save(admin));
        };
    }
}
