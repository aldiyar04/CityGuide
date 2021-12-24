package kz.edu.iitu.CityGuide.repository.entity;

import kz.edu.iitu.CityGuide.controller.dto.UserDto;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @NotBlank(message = "User role cannot be blank")
    @Size(min = 4, max = 32, message = "User role must be 4-32 characters long")
    private String role;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 64, message = "Email must be at most 64 characters")
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 32, message = "Username must be 4-32 characters long")
    private String username;

    @Column(columnDefinition = "char(60)", nullable = false)
    @NotBlank(message = "Password hash cannot be blank")
    @Size(message = "Password hash must be exactly 60 characters long")
    private String password;

    @Column(columnDefinition = "date default current_date", name = "created_on",
            nullable = false, insertable = false, updatable = false)
    private LocalDate createdOn;

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDate.now();
    }
}
