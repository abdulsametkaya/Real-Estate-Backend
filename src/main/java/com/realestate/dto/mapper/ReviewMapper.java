package com.realestate.dto.mapper;

import com.realestate.domain.Review;
import com.realestate.dto.ReviewDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO reviewToDTO(Review review);
    List<ReviewDTO> reviewListToDTO(List<Review> reviewList);

    Review reviewDTOToReview(ReviewDTO reviewDto);
    List<Review> reviewToDTOList(List<ReviewDTO> reviewDTOList);


}
