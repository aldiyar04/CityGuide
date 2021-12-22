package kz.edu.iitu.CityGuide.repository.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    Long id; // not private to let derived classes override it, specifying "generator"

    @Version
    private Long version;
}
