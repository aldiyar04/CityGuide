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
@Table(name = "places")
@SequenceGenerator(name = "generator", sequenceName = "place_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Place extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Place name cannot be blank")
    @Size(min = 3, max = 64, message = "Place name must be 3-64 characters long")
    private String name;

    @Size(min = 64, max = 4096, message = "Place description must be 64-4096 characters long")
    private String description;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", unique = true)
    @NotNull(message = "Place address cannot be null")
    private Address address;

    @Column(name = "phone", unique = true)
    @Size(min = 14, max = 14, message = "Phone number must be exactly 14 characters length. Format: 7-707-123-4567.")
    private String phoneNumber;

    @Column(name = "website_url", unique = true)
    @Size(min = 3, max = 2048, message = "Website URL must be 3-2048 characters long")
    private String websiteUrl;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "place_tags",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}
