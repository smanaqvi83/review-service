package com.assignment.review.service.impl;

import com.assignment.review.controller.pojo.AggregatedReview;
import com.assignment.review.controller.pojo.ReviewPojo;
import com.assignment.review.entity.Reviews;
import com.assignment.review.repo.ReviewsRepo;
import com.assignment.review.service.ReviewService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewsRepo repo;

    public ReviewServiceImpl(ReviewsRepo repo) {
        this.repo = repo;
    }

    @Override public List<ReviewPojo> getAllReviews() {
        List<Reviews> lstReviews = repo.findAll();
        List<ReviewPojo> lstReviewsPojo = new ArrayList<>();
        lstReviews.stream().forEach((reviewEntity) -> {
            ReviewPojo reviewPojo = new ReviewPojo();
            reviewPojo.setReviewScore(reviewEntity.getReviewScore());
            reviewPojo.setId(reviewEntity.getId());
            reviewPojo.setProductId(reviewEntity.getProductId());
            lstReviewsPojo.add(reviewPojo);
        });
        return lstReviewsPojo;
    }

    @Override public AggregatedReview getReviewForProduct(String productId) {
        List<Reviews> lstReviews = repo.findByProductId(productId);
        IntSummaryStatistics statistics = lstReviews.stream().mapToInt((review) -> review.getReviewScore()).summaryStatistics();
        AggregatedReview aggregatedReview = new AggregatedReview();
        aggregatedReview.setAverageReviewScore(statistics.getAverage());
        aggregatedReview.setNumberOfReviews(statistics.getCount());
        return aggregatedReview;
    }

    @Override public ReviewPojo addNewReview(ReviewPojo reviewPojo) {
        Reviews reviews = new Reviews();
        reviews.setProductId(reviewPojo.getProductId());
        reviews.setReviewScore(reviewPojo.getReviewScore());
        reviews = repo.save(reviews);
        reviewPojo.setId(reviews.getId());
        return reviewPojo;
    }

    @Override public ReviewPojo updateReview(long id, ReviewPojo reviewPojo) {
        Optional<Reviews> optionalReview = repo.findById(id);
        if (optionalReview.isPresent()) {
            Reviews reviews = optionalReview.get();
            reviews.setProductId(reviewPojo.getProductId());
            reviews.setReviewScore(reviewPojo.getReviewScore());
            reviews = repo.save(reviews);
            reviewPojo.setId(reviews.getId());
            return reviewPojo;
        } else {
            throw new RuntimeException("No record is present against this ID");
        }

    }

    @Override public void deleteReview(long id) {
        try {
            repo.deleteById(id);
        } catch (Exception ex) {
            if (ex instanceof EmptyResultDataAccessException) {
                throw new EmptyResultDataAccessException("No record found against this id", 1);

            }
        }

    }
}
