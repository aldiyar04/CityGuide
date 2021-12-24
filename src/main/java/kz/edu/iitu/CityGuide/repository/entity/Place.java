package kz.edu.iitu.CityGuide.repository.entity;

import kz.edu.iitu.CityGuide.feature.validation.place.*;
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
@Table(name = "places")
@SequenceGenerator(name = "place_id_generator", sequenceName = "place_sequence", allocationSize = 1)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Place extends BaseEntity {
    @Column(nullable = false, unique = true)
    @CheckPlaceName
    private String name;

    @CheckPlaceDescription
    private String description;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", unique = true)
    @NotNull(message = "Place address cannot be null")
    private Address address;

    @Column(name = "phone", unique = true)
    @CheckPhoneNumber
    private String phoneNumber;

    @Column(name = "website_url", unique = true)
    @CheckWebsiteUrl
    private String websiteUrl;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "place_tags",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @NotNull(message = "Place tag list cannot be null")
    @CheckPlaceTags
    private List<Tag> tags;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<Rating> ratings;
}
