package kz.edu.iitu.CityGuide.repository.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "addresses")
@SequenceGenerator(name = "address_id_generator", sequenceName = "address_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address extends BaseEntity {
    @Size(min = 3, max = 64, message = "Region name must be 3=64 characters long")
    private String region;

    @Column(nullable = false)
    @NotBlank(message = "Street name cannot be blank")
    @Size(min = 3, max = 64, message = "Street name must be 3-64 characters long")
    private String street;

    @Column(nullable = false)
    @NotNull(message = "Building number cannot be null")
    @Size(min = 1, max = 4, message = "Building number must be 1-4 characters long")
    private String building;

//    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
//    @NotNull(message = "Address' place cannot be null")
//    private Place place;
}
