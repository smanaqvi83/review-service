package com.assignment.review.controller.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregatedReview {

    private double averageReviewScore;
    private long numberOfReviews;

    public double getAverageReviewScore() {
        return averageReviewScore;
    }

    public void setAverageReviewScore(double averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public long getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(long numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public AggregatedReview(double averageReviewScore, long numberOfReviews) {
        super();
        this.averageReviewScore = averageReviewScore;
        this.numberOfReviews = numberOfReviews;
    }

    public AggregatedReview() {
        super();
    }

}
