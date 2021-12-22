package kz.edu.iitu.CityGuide.repository.entity;

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
@SequenceGenerator(name = "generator", sequenceName = "user_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseEntity {
    public enum Role {
        ADMIN("admin"),
        USER("user");

        private final String role;

        Role(final String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return role;
        }
    }

    @Column(nullable = false)
    @NotBlank(message = "User role cannot be blank")
    @Size(min = 4, max = 32, message = "User role must be 4-32 characters long")
    private String role;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 64, message = "Email cannot be longer than 64 characters")
    @Email(message = "Email must be valid")
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 32, message = "Username must be 4-32 characters long")
    private String username;

    @Column(columnDefinition = "char(28)", nullable = false)
    @Size(min = 28, max = 28, message = "Password hash must be exactly 28 characters long")
    private String password;

    @Column(columnDefinition = "char(24)", nullable = false)
    @Size(min = 24, max = 24, message = "Salt must be exactly 24 characters long")
    private String salt;

    @Column(columnDefinition = "date default current_date", nullable = false, insertable = false, updatable = false)
    private LocalDate createdOn;

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDate.now();
    }
}
