package kz.edu.iitu.CityGuide.repository.entity;

import kz.edu.iitu.CityGuide.feature.validation.CheckRatingValue;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "ratings")
@SequenceGenerator(name = "rating_id_generator", sequenceName = "rating_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rating extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "place_id")
    @NotNull(message = "Rating's place cannot be null")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "Rating's user cannot be null")
    private User user;

    @NotNull(message = "Rating value cannot be null")
    @CheckRatingValue
    private BigDecimal value;
}
