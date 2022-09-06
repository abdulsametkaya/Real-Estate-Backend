package com.realestate.dto;

import com.realestate.domain.enums.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO implements Serializable {


    private Long id;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide title")
    @NotBlank(message = "Title can not be white space")
    private String title;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide description")
    @NotBlank(message = "Description can not be white space")
    private String description;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide category")
    @NotBlank(message = "Category can not be white space")
    private String category;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide type")
    @NotBlank(message = "Type can not be white space")
    private String type;


    @NotNull(message = "Please provide type")
    private Integer bedrooms;

    @NotNull(message = "Please provide type")
    private Integer bathrooms;

    @NotNull(message = "Please provide type")
    private Integer garages;


    @NotNull(message = "Please provide type")
    private Integer area;

    @NotNull(message = "Please provide type")
    private Double price;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide location")
    @NotBlank(message = "Location can not be white space")
    private String location;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide category")
    @NotBlank(message = "Address can not be white space")
    private String address;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide country")
    @NotBlank(message = "County can not be white space")
    private String country;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide city")
    @NotBlank(message = "City can not be white space")
    private String city;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide district")
    @NotBlank(message = "District can not be white space")
    private String district;

    private LocalDateTime createdDate = LocalDateTime.now();

    private PropertyStatus status = PropertyStatus.ACTIVE;

    private Set<Long> propertyDetailId;

}
