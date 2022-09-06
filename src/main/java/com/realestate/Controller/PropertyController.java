package com.realestate.Controller;

import com.realestate.Service.PropertyService;
import com.realestate.domain.Property;
import com.realestate.dto.PropertyDTO;
import com.realestate.dto.PropertyGetDTO;
import com.realestate.dto.request.SearchRequest;
import com.realestate.dto.response.CREResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/property")
public class PropertyController {

    private PropertyService propertyService;

    //Create property by agent
    @PostMapping("/add")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<CREResponse> createProperty(HttpServletRequest request, @Valid @RequestBody PropertyDTO propertyDTO){
        Long userId = (Long) request.getAttribute("id");
        propertyService.createProperty(propertyDTO,userId);

        CREResponse cre = new CREResponse();
        cre.setMessage("Property Saved Successfuly");
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.CREATED);
    }

    //Update property by agent
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<CREResponse> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyDTO propertyDTO,HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        propertyService.updateProperty(propertyDTO,id,userId);

        CREResponse cre = new CREResponse();
        cre.setMessage("Property Update Successfuly");
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.OK);
    }

    //Agent get all property
    @GetMapping("/getallagent")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<Property>> getAllPropByAgent(HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        List<Property> list = propertyService.findPropertyByUser(userId);

        return ResponseEntity.ok(list);
    }

    //Agent get property by id
    @GetMapping("/getid/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<Property> getAllPropById(@PathVariable Long id,HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        Property list = propertyService.findPropertyById(id,userId);

        return ResponseEntity.ok(list);
    }

    //Admin get all property
    @GetMapping("/getall")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Property>> getAllPropByAdmin(){

        List<Property> list = propertyService.findAllPropertyByAdmin();

        return ResponseEntity.ok(list);
    }

    //Agent delete property by id
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<CREResponse> deleteProperty(@PathVariable Long id,HttpServletRequest request){
        Long userId = (Long) request.getAttribute("id");
        propertyService.deleteProperty(id,userId);

        CREResponse cre = new CREResponse();
        cre.setMessage("Property Deleted Successfuly");
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.OK);
    }

    //User add like
    @PutMapping("/addlike/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CREResponse> addLike(@PathVariable Long id){
        propertyService.addLike(id);

        CREResponse cre = new CREResponse();
        cre.setMessage("Added Like Successfully");
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.OK);
    }

    //Search By user
    @PostMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Property>> SearchProperty(@Valid @RequestBody SearchRequest searchRequest){
      List<Property> list = propertyService.searchProp(searchRequest);

        return ResponseEntity.ok(list);
    }



}
