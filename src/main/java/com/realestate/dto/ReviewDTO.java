package com.realestate.dto;

import com.realestate.domain.Property;
import com.realestate.domain.Review;
import com.realestate.domain.User;
import com.realestate.domain.enums.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long id;

    @Size(min = 3, max = 500, message = "Please comment between 3 and 500 characters")
    @NotNull(message = "Please enter review info")
    private String review;

    private LocalDateTime createdDate;

    @Size(min = 0, max = 5, message = "Please entry between 1 and 5 char")
    private Byte score;

    private ReviewStatus status;

    private User user;

    private Property property;

    public ReviewDTO(Review review){

        this.id=review.getId();
        this.review=review.getReview();
        this.createdDate=review.getCreatedDate();
        this.score=review.getScore();
        this.status=review.getStatus();
        this.user=review.getUser();
        this.property=review.getProperty();
    }
}
