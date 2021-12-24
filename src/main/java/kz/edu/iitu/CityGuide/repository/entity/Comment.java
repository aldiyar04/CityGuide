package kz.edu.iitu.CityGuide.repository.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "comments")
@SequenceGenerator(name = "comment_id_generator", sequenceName = "comment_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "rating_id")
    @NotNull(message = "Comment's rating cannot be null")
    private Rating rating;

    @NotBlank(message = "Comment body cannot be blank")
    @Size(min = 4, max = 2048, message = "Comment body must be 4-2048 characters long")
    private String body;

    @Column(columnDefinition = "timestamp", name = "created_at",
            nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "timestamp", name = "updated_at",
            nullable = false, insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
