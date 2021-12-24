package kz.edu.iitu.CityGuide.repository.entity;

import kz.edu.iitu.CityGuide.feature.validation.user.CheckEmail;
import kz.edu.iitu.CityGuide.feature.validation.user.CheckPasswordHash;
import kz.edu.iitu.CityGuide.feature.validation.user.CheckUserRole;
import kz.edu.iitu.CityGuide.feature.validation.user.CheckUsername;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "users")
@SequenceGenerator(name = "user_id_generator", sequenceName = "user_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseEntity {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Column(nullable = false)
    @CheckUserRole
    private String role;

    @Column(nullable = false, unique = true)
    @CheckEmail
    private String email;

    @Column(nullable = false, unique = true)
    @CheckUsername
    private String username;

    @Column(columnDefinition = "char(60)", nullable = false)
    @CheckPasswordHash
    private String password;

    @Column(columnDefinition = "date default current_date", name = "created_on",
            nullable = false, insertable = false, updatable = false)
    private LocalDate createdOn;

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDate.now();
    }
}
