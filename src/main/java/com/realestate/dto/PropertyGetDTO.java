package com.realestate.dto;

import com.realestate.domain.PropertyDetail;
import com.realestate.domain.User;
import com.realestate.domain.enums.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyGetDTO {


    private Long id;

    private String title;

    private String description;

    private String category;

    private String type;

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer garages;

    private Integer area;

    private Double price;

    private String location;

    private String address;

    private String country;

    private String city;

    private String district;

    private LocalDateTime createdDate;

    private Integer likes;

    private Integer visitCount;

    private PropertyStatus status;

    private Long userId;

}
