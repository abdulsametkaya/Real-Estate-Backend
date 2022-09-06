package com.realestate.dto.mapper;

import com.realestate.domain.PropertyDetail;
import com.realestate.dto.PropertyDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface PropDetailMapper {

    List<PropertyDetailDTO> map(List<PropertyDetail> propDetail);

    PropertyDetailDTO propDetailToDTO(PropertyDetail propDetail);

}
