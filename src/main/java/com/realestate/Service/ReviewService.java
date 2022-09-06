package com.realestate.Service;

import com.realestate.domain.Property;
import com.realestate.domain.Review;
import com.realestate.domain.User;
import com.realestate.dto.ReviewDTO;
import com.realestate.dto.mapper.ReviewMapper;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.ReviewRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;
    UserRepository userRepository;
    PropertyRepository propertyRepository;
    ReviewMapper reviewMapper;


    // tüm review listele
    public List<ReviewDTO> getAllReview(){
        List<Review> allReviews = reviewRepository.findAll();
        return reviewMapper.reviewListToDTO(allReviews);

    }

    // id ye göre review listele
    public ReviewDTO getReviewById(Long id){
        Review review = reviewRepository.findById(id).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));
        return reviewMapper.reviewToDTO(review);
    }

    // property id si verilen property nin tüm reviewlerini listele
    public Set<Review> getAllReviewByPropertyId(Long id){
        Property property = propertyRepository.findById(id).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE , id)));
        Set<Review> reviewList = new HashSet<>();
        for (Review r : property.getReview()) {
            reviewList.add(r);
        }

        return reviewList;
    }

    // id si verilen review güncelle
    public void updateReview(Long id, ReviewDTO reviewDTO){
        Review foundReview = reviewRepository.findById(id).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));

        Review review = reviewMapper.reviewDTOToReview(reviewDTO);

        foundReview.setReview(review.getReview());
        foundReview.setScore(review.getScore());
        foundReview.setCreatedDate(LocalDateTime.now());

        reviewRepository.save(review);
    }

    // review kaydetme
    public void saveReview(ReviewDTO reviewDTO, Long userId, Long propertyId){
        User user =  userRepository.findById(userId).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));

        Property property = propertyRepository.findById(propertyId).orElseThrow(()->new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE , propertyId)));

        Review review = reviewMapper.reviewDTOToReview(reviewDTO);
        review.setUser(user);
        review.setProperty(property);
        review.setCreatedDate(LocalDateTime.now());

        reviewRepository.save(review);
    }

    // id si verilen review sil
    public void deleteReview(Long id){
        Review review = reviewRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE,id)));
        reviewRepository.delete(review);
    }
}
