package com.realestate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.realestate.domain.enums.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, length = 250)
    private String description;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false)
    private Integer bedrooms;

    @Column(nullable = false)
    private Integer bathrooms;

    @Column(nullable = false)
    private Integer garages;

    @Column(nullable = false)
    private Integer area;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false, length = 250)
    private String address;

    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String district;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
    private LocalDateTime createdDate;


    private Integer likes;

    private Integer visitCount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    @OneToMany(mappedBy="property")
    private Set<Review> review = new HashSet<>();

    @OneToMany(mappedBy="property")
    private Set<Image> image = new HashSet<>();

    @OneToMany(mappedBy="property")
    private Set<TourRequest> tourRequest = new HashSet<>();


    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="tbl_prop_propdetail", joinColumns = @JoinColumn(name="prop_id"),
            inverseJoinColumns = @JoinColumn(name="propdetail_id"))
    private Set<PropertyDetail> propertyDetail = new HashSet<>();

}
