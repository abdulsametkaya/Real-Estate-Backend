package com.realestate.Service;


import com.realestate.domain.Property;
import com.realestate.domain.PropertyDetail;
import com.realestate.domain.User;
import com.realestate.domain.enums.PropertyStatus;
import com.realestate.dto.PropertyDTO;
import com.realestate.dto.request.SearchRequest;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.PropertyDetailRepository;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PropertyService {

    private PropertyRepository propertyRepository;
    private PropertyDetailRepository propertyDetailRepository;
    private UserRepository userRepository;


    //Create property by agent
    @Transactional
    public void createProperty(PropertyDTO propertyDTO,Long userId) {
        Property prop = new Property();
        Set<PropertyDetail> propDetails = new HashSet<>();

        //gelen propertyDetail id-id'lerin db'de var olup olmadığı kontrol ediliyor. PropertyDetail id'ler Set<> olarak geliyor.
        for (Long w : propertyDTO.getPropertyDetailId()) {
            PropertyDetail detail = propertyDetailRepository.findById(w).orElseThrow(() ->
                    new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, w)));
            propDetails.add(detail);
        }

        //User bilgisi alınıyor
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USER_RESOURCE_NOT_FOUND_MESSAGE, userId)));

        prop.setTitle(propertyDTO.getTitle());
        prop.setDescription(propertyDTO.getDescription());
        prop.setCategory(propertyDTO.getCategory());
        prop.setType(propertyDTO.getType());
        prop.setBedrooms(propertyDTO.getBedrooms());
        prop.setBathrooms(propertyDTO.getBathrooms());
        prop.setGarages(propertyDTO.getGarages());
        prop.setArea(propertyDTO.getArea());
        prop.setPrice(propertyDTO.getPrice());
        prop.setLocation(propertyDTO.getLocation());
        prop.setAddress(propertyDTO.getAddress());
        prop.setCountry(propertyDTO.getCountry());
        prop.setCity(propertyDTO.getCity());
        prop.setDistrict(propertyDTO.getDistrict());
        prop.setCreatedDate(propertyDTO.getCreatedDate());
        prop.setStatus(propertyDTO.getStatus());
        prop.setLikes(0);
        prop.setVisitCount(0);
        prop.setUser(user);
        prop.setPropertyDetail(propDetails);

        propertyRepository.save(prop);
    }


    //Update property by agent
    public void updateProperty(PropertyDTO propertyDTO, Long id, Long userId) throws ResourceNotFoundException {
        // id ile db'den property alınıyor
        Property prop = propertyRepository.findById(id).orElseThrow(()->
                     new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));


        //Property ilgili User'a ait mi kontrolü yapılıyor.
        if (prop.getUser().getId() != userId) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_MATCH_MESSAGE, userId));
        }

        Set<PropertyDetail> propDetails = new HashSet<>();

        //gelen propertyDetail id-id'lerin db'de var olup olmadığı kontrol ediliyor. PropertyDetail id'ler Set<> olarak geliyor.
        for (Long w : propertyDTO.getPropertyDetailId()) {
            PropertyDetail detail = propertyDetailRepository.findById(w).orElseThrow(() ->
                    new ResourceNotFoundException(String.format(ErrorMessage.PROPERTY_DETAIL_NOT_FOUND, w)));
            propDetails.add(detail);
        }

        prop.setTitle(propertyDTO.getTitle());
        prop.setDescription(propertyDTO.getDescription());
        prop.setCategory(propertyDTO.getCategory());
        prop.setType(propertyDTO.getType());
        prop.setBedrooms(propertyDTO.getBedrooms());
        prop.setBathrooms(propertyDTO.getBathrooms());
        prop.setGarages(propertyDTO.getGarages());
        prop.setArea(propertyDTO.getArea());
        prop.setPrice(propertyDTO.getPrice());
        prop.setLocation(propertyDTO.getLocation());
        prop.setAddress(propertyDTO.getAddress());
        prop.setCountry(propertyDTO.getCountry());
        prop.setCity(propertyDTO.getCity());
        prop.setDistrict(propertyDTO.getDistrict());
        prop.setCreatedDate(propertyDTO.getCreatedDate());
        prop.setStatus(propertyDTO.getStatus());
        prop.setLikes(0);
        prop.setVisitCount(0);
        prop.setPropertyDetail(propDetails);

        propertyRepository.save(prop);
    }


    //Agent get all belong property
    @Transactional(readOnly = true)
    public List<Property> findPropertyByUser(Long userId) throws ResourceNotFoundException {
        List<Property> prop = propertyRepository.findAllByUserId(userId);

        return prop;
    }

    // Agent get property by id
    @Transactional(readOnly = true)
    public Property findPropertyById(Long id, Long userId) throws ResourceNotFoundException {
        // id ile db'den property alınıyor
        Property prop = propertyRepository.findPropertyUserId(id);

        //Property var mı yok mu kontrol ediliyor
        if (prop == null) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id));
        }
        //Property ilgili User'a ait mi kontrolü yapılıyor.
        if (prop.getUser().getId() != userId) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_MATCH_MESSAGE, userId));
        }

        return prop;
    }

    //Admin get all property
    public List<Property> findAllPropertyByAdmin() {
        List<Property> propAll = propertyRepository.findAll();

        return propAll;
    }

    //Agent delete property
    public void deleteProperty(Long id, Long userId) {
        // id ile db'den property alınıyor
        Property prop = propertyRepository.findPropertyUserId(id);

        //Property var mı yok mu kontrol ediliyor
        if (prop == null) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id));
        }
        //Property ilgili User'a ait mi kontrolü yapılıyor.
        if (prop.getUser().getId() != userId) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_MATCH_MESSAGE, userId));
        }

        propertyRepository.deleteById(prop.getId());
    }

    /*
       //User add likes, ilk yöntem db'den tüm veriyi çekerek içinden like sayısını alıp +1 yapıyordu.
    @Transactional
   public void addLike(Long id){
       Property prop = propertyRepository.findById(id).orElseThrow(()->
               new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));

        propertyRepository.updateLike(prop.getLikes()+1,id);
   }
     */

    //User add likes
    @Transactional
    public void addLike(Long id) {
        Boolean exists = propertyRepository.existsById(id);

        if(exists){
            Integer likes = propertyRepository.getLikes(id);
            propertyRepository.updateLike(likes+1, id);
        }else  throw new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id));

    }

    public List<Property> searchProp(SearchRequest searchRequest){
        String title = searchRequest.getTitle();

        String type = searchRequest.getType();
        Integer bedrooms = searchRequest.getBedrooms();
        Integer bathrooms = searchRequest.getBathrooms();
        String country = searchRequest.getCountry();
        String city = searchRequest.getCity();
        String district = searchRequest.getDistrict();
        Double priceMin = searchRequest.getPriceMin();
        Double priceMax = searchRequest.getPriceMax();
        PropertyStatus status = searchRequest.getStatus();

        List<Property> prop =   propertyRepository.searchRepo(title,type,bedrooms,bathrooms,country,city,district,status,priceMin,priceMax);
        return prop;
    }



}