package kz.edu.iitu.CityGuide.repository;

import kz.edu.iitu.CityGuide.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> findUsersByCreatedOnBefore(LocalDate beforeDate);
//    List<User> findUsersByCreatedOnAfter(LocalDate afterDate);
//    List<User> findUsersByCreatedOnBetween(LocalDate beforeDate, LocalDate afterDate);
}
