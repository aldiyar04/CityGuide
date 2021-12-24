package kz.edu.iitu.CityGuide.repository.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "tags")
@SequenceGenerator(name = "generator", sequenceName = "tag_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Tag name cannot be blank")
    @Size(min = 2, max = 64, message = "Tag name must be 2-64 characters long")
    private String name;

    @Column(name = "lft")
    @NotNull(message = "Tag.left cannot be null")
    private Integer left;

    @Column(name = "rgt")
    @NotNull(message = "Tag.right cannot be null")
    private Integer right;

    @ManyToMany(mappedBy = "tags")
    private List<Place> places;
}
