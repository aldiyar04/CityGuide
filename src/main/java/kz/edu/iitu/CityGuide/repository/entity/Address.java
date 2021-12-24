package kz.edu.iitu.CityGuide.repository.entity;

import kz.edu.iitu.CityGuide.feature.validation.place.CheckBuildingNumber;
import kz.edu.iitu.CityGuide.feature.validation.place.CheckStreetName;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

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
    @Column(nullable = false)
    @CheckStreetName
    private String street;

    @Column(nullable = false)
    @CheckBuildingNumber
    private String buildingNumber;
}
