package com.realestate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDetailDTO {

    private Long id;

    @Size(max = 250,message="Size is exceeded")
    @NotNull(message = "Please provide title")
    @NotBlank(message = "Title can not be white space")
    private String title;
}
