package kz.edu.iitu.CityGuide.repository;

import kz.edu.iitu.CityGuide.repository.entity.Address;
import kz.edu.iitu.CityGuide.repository.entity.Place;
import kz.edu.iitu.CityGuide.repository.entity.Tag;
import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@Log4j2
@AllArgsConstructor
class LoadDatabase {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final PlaceRepository placeRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            loadUsers();
            loadTagsAndPlaces();
        };
    }

    private void loadUsers() {
        User admin = User.builder()
                .role(User.ROLE_ADMIN)
                .email("admin@test.com")
                .username("gokublack")
                .password(passwordEncoder.encode("password"))
                .build();
        List<User> users = Collections.singletonList(admin);
        users.forEach(user -> log.info("Preloading " + userRepository.save(user)));
    }

    private void loadTagsAndPlaces() {
        // TOURISM MODULE:
        Tag tourismTag = new Tag("tourism", 1, 10);
        Tag hotelTag = new Tag("hotel", 2, 3);
        Tag restaurantTag = new Tag("restaurant", 4, 5);
        Tag atmTag = new Tag("atm", 6, 7);
        Tag theaterTag = new Tag("theater", 8, 9);

        Place place1 = Place.builder()
                .name("MONTANA HOTEL")
                .address(new Address("Kazhymukan", "12/2"))
                .phoneNumber("7‒778‒005‒0549")
                .tags(Arrays.asList(tourismTag, hotelTag))
                .build();
        Place place2 = Place.builder()
                .name("Versailles")
                .description("Welcome! Juicy offers for dear guests! Foamy drinks promotions, updated cuisine from the best chefs. We are glad to open our doors for everyone who wants to have a good time in the company of family and friends.")
                .address(new Address("Timiryazev" , "32c"))
                .phoneNumber("7‒705‒600‒0917")
                .tags(Arrays.asList(tourismTag, restaurantTag))
                .build();
        Place place3 = Place.builder()
                .name("Kaspi Bank")
                .address(new Address("Abylai Khan", "74"))
                .phoneNumber("7-727-258‒5965")
                .websiteUrl("https://kaspi.kz/")
                .tags(Arrays.asList(tourismTag, atmTag))
                .build();
        Place place4 = Place.builder()
                .name("State Puppet Theater")
                .address(new Address("Pushkin", "63"))
                .phoneNumber("7-727-234‒7922")
                .websiteUrl("https://puppet.kz/")
                .tags(Arrays.asList(tourismTag, theaterTag))
                .build();

        // STUDENT MODULE:
        Tag studentTag = new Tag("student", 11, 16);
        Tag universityTag = new Tag("university", 12, 13);
        Tag libraryTag = new Tag("library", 14, 15);

        Place place5 = Place.builder()
                .name("International Information Technology University")
                .address(new Address("Manas", "34/1"))
                .phoneNumber("7-727-330‒8560")
                .websiteUrl("https://iitu.edu.kz/en/")
                .tags(Arrays.asList(studentTag, universityTag))
                .build();
        Place place6 = Place.builder()
                .name("National Library of the Republic of Kazakhstan")
                .address(new Address("Abay", "14"))
                .phoneNumber("7-727-267‒2849")
                .websiteUrl("http://www.nlrk.kz/index.php?lang=kz")
                .tags(Arrays.asList(studentTag, libraryTag))
                .build();

        // JOB MODULE:
        Tag jobTag = new Tag("job", 16, 19);
        Tag employmentAgencyTag = new Tag("employment-agency", 17, 18);

        Place place7 = Place.builder()
                .name("Optimum Recruitment And Executive Search")
                .description("Optimum Recruitment & Executive Search agency is one of the leading players in the personnel market in Kazakhstan, which specializes in recruiting top and middle managers.\n" +
                        "\n" +
                        "In the recruiting agency \"Optimum Recruitment & Executive Search\" you can order:\n" +
                        "1. Selection of qualified personnel;\n" +
                        "2. Executive Search - special direct search and selection of managers, top personnel;\n" +
                        "3. Training for recruiters and novice HR specialists (training programs \"Recruiter's Workshop\" and \"Interview on competencies\");\n" +
                        "4. More than 30 computerized test methods (Human technologies) - the initial assessment of candidates using test methods of the Moscow laboratory at Moscow State University \"Humanitarian Technologies\".")
                .address(new Address("Bayzakov", "280"))
                .phoneNumber("7‒701‒495‒5524")
                .websiteUrl("https://www.optimist.kz/")
                .tags(Arrays.asList(jobTag, employmentAgencyTag))
                .build();

        // BUSINESS MODULE:
        Tag businessTag = new Tag("business", 19, 22);
        Tag businessCenterTag = new Tag("business-center", 20, 21);

        Place place8 = Place.builder()
                .name("Globus")
                .address(new Address("Abay", "109c"))
                .phoneNumber("7-727-356‒1515")
                .websiteUrl("http://globus-almaty.kz/ru/")
                .tags(Arrays.asList(businessTag, businessCenterTag))
                .build();

        // load tags
        List<Tag> tags = Arrays.asList(tourismTag, hotelTag, restaurantTag, atmTag, theaterTag,
                studentTag, universityTag, libraryTag,
                jobTag, employmentAgencyTag,
                businessTag, businessCenterTag);
        tags.forEach(tag -> log.info("Preloading " + tagRepository.save(tag)));

        List<Place> places = Arrays.asList(place1, place2, place3, place4, place5, place6, place7, place8);
        places.forEach(place -> log.info("Preloading " + placeRepository.save(place)));
    }
}
