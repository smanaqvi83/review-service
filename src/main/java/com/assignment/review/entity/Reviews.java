package com.assignment.review.entity;

import javax.persistence.*;

@Entity
@Table
public class Reviews {
    @Column
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column
    private String productId;
    @Column
    private int reviewScore;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
