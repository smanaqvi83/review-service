package com.assignment.review.controller.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewPojo {

    private Long id;
    @NotBlank
    private String productId;
    @Range(min = 1, max = 5)
    @NotNull
    private Integer reviewScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public ReviewPojo() {
        super();
    }

    public ReviewPojo(Long id, String productId, @NotNull Integer reviewScore) {
        super();
        this.id = id;
        this.productId = productId;
        this.reviewScore = reviewScore;
    }

}
