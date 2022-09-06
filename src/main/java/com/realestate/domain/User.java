package com.realestate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please enter valid phone number")
    @Column(nullable = false, length = 14)
    private String phone;

    @Column(nullable = false, length = 50)
    private String email;
    @JsonIgnore
    @Column(nullable = false, length = 100)
    private String password;

    private boolean builtIn = false;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private Set<Property> property = new HashSet<>();

    @OneToMany(mappedBy="user")
    private Set<Review> review = new HashSet<>();

    @OneToMany(mappedBy="user")
    private Set<TourRequest> tourRequest = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tbl_user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();


}
