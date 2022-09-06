package com.realestate.dto.request;

import com.realestate.domain.enums.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    @Size(max = 250,message="Size is exceeded")
    private String title;


    @Size(max = 250,message="Size is exceeded")
    private String type;

    private Integer bedrooms;

    private Integer bathrooms;

    @Size(max = 250,message="Size is exceeded")
    private String country;

    @Size(max = 250,message="Size is exceeded")
    private String city;

    @Size(max = 250,message="Size is exceeded")
    private String district;

    private Double priceMin;

    private Double priceMax;

    private PropertyStatus status ;



}
