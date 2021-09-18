package com.assignment.review;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.assignment.review.config.ServiceConfiguration;
import com.assignment.review.controller.ReviewController;
import com.assignment.review.controller.pojo.AggregatedReview;
import com.assignment.review.controller.pojo.ReviewPojo;
import com.assignment.review.service.ReviewService;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@Ignore
class ReviewPojoApplicationTests {

    @Mock
    private ServiceConfiguration opConfiguration;

    @Mock
    private ReviewService reviewService;

    @MockBean
    private ReviewController reviewController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this.getClass());
    }

    final String basicTokenValid = "QmFzaWNUZXN0OlBhc3N3b3Jk";
    final String basicInValidPassword = "Basic";
    final String basicInValidUserName = "Basic";
    final String basicInValidUser = "Basic";

    @Test
    public void testGetProductSucess() {
        when(reviewService.getReviewForProduct(anyString()))
                .thenReturn(buildSuccessAggregatedViewResponse());
        AggregatedReview response = reviewController.getAggregatedReviewForProduct("1");
        assertEquals(40, response.getNumberOfReviews());
    }

    @Test
    public void testGetProductFailure() {
        when(reviewService.getReviewForProduct(anyString()))
                .thenReturn(buildSuccessAggregatedViewResponse());
        AggregatedReview response = reviewController.getAggregatedReviewForProduct("1");
        assertEquals(40, response.getNumberOfReviews());
    }

    @Test
    public void testGetAuthorizationSucess() {
        when(reviewService.getReviewForProduct(anyString()))
                .thenReturn(buildFailureAggregatedViewResponse());
        AggregatedReview response = reviewController.getAggregatedReviewForProduct("2");
        assertEquals(40, response.getNumberOfReviews());
    }

    @Test
    public void addNewReviewWithAuthorization() {
        when(reviewService.addNewReview(getSuccessReview())).thenReturn(getSuccessReview());
        ReviewPojo response = reviewController.addNewReview(basicTokenValid, getSuccessReview());
        assertEquals(40, response.getProductId());

    }

    public AggregatedReview buildSuccessAggregatedViewResponse() {

        return new AggregatedReview(20.0, 40);

    }

    public AggregatedReview buildFailureAggregatedViewResponse() {

        return new AggregatedReview(10.0, 40);

    }

    public ReviewPojo getSuccessReview() {

        return new ReviewPojo(1l, "1", 20);

    }

}
