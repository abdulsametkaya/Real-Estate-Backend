package com.realestate.Service;

import com.realestate.domain.PropertyDetail;
import com.realestate.dto.PropertyDetailDTO;
import com.realestate.dto.mapper.PropDetailMapper;
import com.realestate.exception.ConflictException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.PropertyDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyDetailService {

    private PropertyDetailRepository propertyDetailRepository;

    private PropDetailMapper propertyDetailMapper;

 public void createPropertyDetail(PropertyDetailDTO propertyDetailDTO){

            if(propertyDetailRepository.existsByTitle(propertyDetailDTO.getTitle())){
                throw new ConflictException(String.format(ErrorMessage.PROPERTY_DETAIL_ALREADY_EXISTS,propertyDetailDTO.getTitle()));
            }
            PropertyDetail propDetail = new PropertyDetail();
            propDetail.setTitle(propertyDetailDTO.getTitle());

        propertyDetailRepository.save(propDetail);
    }



   public List<PropertyDetailDTO> getAllPropDetail(){
       List<PropertyDetail> propDetail = propertyDetailRepository.findAll();
       return propertyDetailMapper.map(propDetail);
   }

   public PropertyDetailDTO getPropDetailId(Long id){
       PropertyDetail propDetail = propertyDetailRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));

       return propertyDetailMapper.propDetailToDTO(propDetail);
   }

   public void deletePropDetailId(Long id){
       PropertyDetail propDetail = propertyDetailRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));

       propertyDetailRepository.deleteById(propDetail.getId());
   }

    public void updatePropertyDetail(Long id,PropertyDetailDTO propertyDetailDTO){

        PropertyDetail propDetail = propertyDetailRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));


        propDetail.setTitle(propertyDetailDTO.getTitle());

        propertyDetailRepository.save(propDetail);

    }



}
