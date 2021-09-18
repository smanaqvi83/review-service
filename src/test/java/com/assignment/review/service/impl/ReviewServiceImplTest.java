package com.assignment.review.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.assignment.review.controller.pojo.AggregatedReview;
import com.assignment.review.controller.pojo.ReviewPojo;
import com.assignment.review.entity.Reviews;
import com.assignment.review.repo.ReviewsRepo;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ReviewServiceImplTest {

    @Mock
    private ReviewsRepo repo;

    @Mock
    private ReviewServiceImpl reviewService;

    private static final Long ID = 123L;

    private static final String PRODUCT_ID = "1234";

    private static final int REVIEW_SCORE = 123114;


    @Test
    public void getAllReviewsReturnsAllReviewsListWhenGotFromRepo() {
        doReturn(getListOfAllReviews()).when(repo).findAll();

        List<ReviewPojo> reviewPojoList = reviewService.getAllReviews();

        assertEquals(1, reviewPojoList.size());
        assertEquals(PRODUCT_ID, reviewPojoList.get(0).getProductId());
        verify(repo).findAll();

    }

    @Test
    public void getReviewForProductReturnsReviewWhenGetByProductIdFromRepo() {
        doReturn(getListOfAllReviews()).when(repo).findByProductId(Mockito.anyString());
        AggregatedReview aggregatorReview = reviewService.getReviewForProduct(PRODUCT_ID);

        verify(repo).findByProductId(Mockito.anyString());

    }

    @Test
    public void addNewReviewReturnsReviewPojoWhenAddNewInDB() {
        doReturn(getReviews()).when(repo).save(Mockito.any(Reviews.class));
        ReviewPojo reviewPojo = reviewService.addNewReview(getReviewPojo());

        assertEquals(PRODUCT_ID, reviewPojo.getProductId());
        assertEquals(ID, reviewPojo.getId());
        verify(repo).save(Mockito.any(Reviews.class));
    }

    @Test
    public void updateReviewReturnsReviewPojoWhenUpdateReviewInDB() {
        doReturn(Optional.of(getReviews())).when(repo).findById(Mockito.anyLong());
        doReturn(getReviews()).when(repo).save(Mockito.any(Reviews.class));

        ReviewPojo reviewPojo = reviewService.updateReview(ID, getReviewPojo());

        assertEquals(ID, reviewPojo.getId());
        assertEquals(PRODUCT_ID, reviewPojo.getProductId());
        verify(repo).findById(Mockito.anyLong());
        verify(repo).save(Mockito.any(Reviews.class));
    }



    private List<Reviews> getListOfAllReviews() {
        List<Reviews> allReviews = new ArrayList<Reviews>();
        Reviews review = new Reviews();
        review.setId(ID);
        review.setProductId(PRODUCT_ID);
        review.setReviewScore(12);
        allReviews.add(review);

        return allReviews;
    }

    private Reviews getReviews() {
        Reviews reviews = new Reviews();
        reviews.setProductId(PRODUCT_ID);
        reviews.setReviewScore(REVIEW_SCORE);
        return reviews;
    }


    private ReviewPojo getReviewPojo() {
        ReviewPojo reviewPojo = new ReviewPojo();
        reviewPojo.setId(ID);
        reviewPojo.setProductId(PRODUCT_ID);
        reviewPojo.setReviewScore(REVIEW_SCORE);
        return reviewPojo;
    }


}
