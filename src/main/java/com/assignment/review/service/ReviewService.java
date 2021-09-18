package com.assignment.review.service;

import com.assignment.review.controller.pojo.AggregatedReview;
import com.assignment.review.controller.pojo.ReviewPojo;
import com.assignment.review.entity.Reviews;

import java.util.List;

public interface ReviewService {
    List<ReviewPojo> getAllReviews();
    AggregatedReview getReviewForProduct(String productId);
    ReviewPojo addNewReview(ReviewPojo reviewPojo);
    ReviewPojo updateReview(long id, ReviewPojo reviewPojo);
    void deleteReview(long id);

}
