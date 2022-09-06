package com.realestate.Controller;

import com.realestate.Service.PropertyDetailService;
import com.realestate.domain.PropertyDetail;
import com.realestate.dto.PropertyDetailDTO;
import com.realestate.dto.response.CREResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/propdetail")
public class PropertyDetailController {

    private PropertyDetailService propertyDetailService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<CREResponse> createPropDetail(@Valid @RequestBody PropertyDetailDTO propDetailDTO){
        propertyDetailService.createPropertyDetail(propDetailDTO);

        CREResponse cre = new CREResponse();
        cre.setMessage("Property Detail Saved Successfuly");
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<PropertyDetailDTO>> getAllPropDetail(){
        List<PropertyDetailDTO> propDetails =  propertyDetailService.getAllPropDetail();

        return ResponseEntity.ok(propDetails);
    }

    @GetMapping("/getid/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<PropertyDetailDTO> getPropDetailById(@PathVariable Long id){
        PropertyDetailDTO propDetail =  propertyDetailService.getPropDetailId(id);

        return ResponseEntity.ok(propDetail);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CREResponse> deleteById(@PathVariable Long id){
        propertyDetailService.deletePropDetailId(id);

        CREResponse cre = new CREResponse();
        cre.setMessage("Property Detail Deleted Successfuly");
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.OK);
    }

    @PutMapping ("/update/{id}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<CREResponse> updatePropDetail(@Valid @PathVariable Long id, @RequestBody PropertyDetailDTO propDetailDTO){
        propertyDetailService.updatePropertyDetail(id, propDetailDTO);

        CREResponse cre = new CREResponse();
        cre.setMessage("Property Detail Updated Successfuly");
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.OK);
    }


}
