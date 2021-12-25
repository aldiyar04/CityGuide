package kz.edu.iitu.CityGuide.repository;

import kz.edu.iitu.CityGuide.repository.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("select p from Place p join p.tags tag where tag.name = ?1")
    List<Place> findPlacesByTag(String tag);
}
