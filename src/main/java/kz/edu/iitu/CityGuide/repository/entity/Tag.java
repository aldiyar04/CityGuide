package kz.edu.iitu.CityGuide.repository.entity;

import kz.edu.iitu.CityGuide.feature.validation.CheckTagName;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "tags")
@SequenceGenerator(name = "tag_id_generator", sequenceName = "tag_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag extends BaseEntity {
    @Column(nullable = false, unique = true)
    @CheckTagName
    private String name;

    @Column(name = "lft")
    @NotNull(message = "Tag.left cannot be null")
    private Integer left;

    @Column(name = "rgt")
    @NotNull(message = "Tag.right cannot be null")
    private Integer right;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private List<Place> places;
}
