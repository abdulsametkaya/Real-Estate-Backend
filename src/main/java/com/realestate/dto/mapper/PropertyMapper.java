package com.realestate.dto.mapper;

import com.realestate.domain.Property;
import com.realestate.dto.PropertyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PropertyMapper {

    Property map(PropertyDTO property);



}
