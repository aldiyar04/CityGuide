package kz.edu.iitu.CityGuide.repository;

import kz.edu.iitu.CityGuide.repository.entity.Comment;
import kz.edu.iitu.CityGuide.repository.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c inner join Rating r on c.rating = r inner join Place p on r.place = ?1")
    List<Comment> findCommentsOfPlace(Place place);
}
