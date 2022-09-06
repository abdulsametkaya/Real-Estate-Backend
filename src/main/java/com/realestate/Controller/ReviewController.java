package com.realestate.Controller;

import com.realestate.Service.ReviewService;
import com.realestate.dto.ReviewDTO;
import com.realestate.dto.mapper.ReviewMapper;
import com.realestate.dto.response.CREResponse;
import com.realestate.dto.response.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    ReviewService reviewService;
    ReviewMapper reviewMapper;

    // tüm review listele
    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTO>> getAllReview(ReviewDTO reviewDTO) {
        List<ReviewDTO> allReviews = reviewService.getAllReview();
        return ResponseEntity.ok(allReviews);
    }

    // id si verilen review listele
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        ReviewDTO reviewDTO = reviewService.getReviewById(id);
        return ResponseEntity.ok(reviewDTO);
    }

    //  review ekleme
    @PostMapping("/add")
    public ResponseEntity<CREResponse> addReview(@RequestParam("userId") Long userId,
                                                 @RequestParam("propertyId") Long propertyId,
                                                 @Valid @RequestBody ReviewDTO reviewDTO) {

        reviewService.saveReview(reviewDTO,userId, propertyId);
        CREResponse response = new CREResponse();
        response.setMessage(ResponseMessage.REVIEW_SAVE_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //  review güncelleme
    @PutMapping("/update/{id}")
    public ResponseEntity<CREResponse> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO) {
        reviewService.updateReview(id, reviewDTO);

        CREResponse response = new CREResponse();
        response.setMessage(ResponseMessage.REVIEW_UPDATE_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // review silme
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CREResponse> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);

        CREResponse response = new CREResponse();
        response.setMessage(ResponseMessage.REVIEW_DELETED_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
