package kz.edu.iitu.CityGuide.repository;

import kz.edu.iitu.CityGuide.repository.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
