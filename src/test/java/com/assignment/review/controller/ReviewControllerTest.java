package com.assignment.review.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.assignment.review.config.ServiceConfiguration;
import com.assignment.review.controller.pojo.AggregatedReview;
import com.assignment.review.controller.pojo.ReviewPojo;
import com.assignment.review.service.ReviewService;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ReviewControllerTest {

    @Mock
    private ServiceConfiguration config;

    @Mock
    private ReviewService reviewService;

    @MockBean
    private ReviewController reviewController;

    private static final String AGGREGATED_PRODUCT_ID = "12";

    private static final String AGGREGATED_PRODUCT_REVIEW_SCORE = "1221";

    private static final String REVIEW_PRDOUCT_ID = "11091928";

    private static final String BASIC_USERNAME = "BasicTest";

    private static final String BASIC_PASSWORD = "Password";

    private static final String BASIC_TOKEN_VALID = "Basic QmFzaWNUZXN0OlBhc3N3b3Jk";
    final String basicInValidPassword = "Basic";
    final String basicInValidUserName = "Basic";
    final String basicInValidUser = "Basic";


    @SuppressWarnings("deprecation")
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAggregatedReviewForProductReturnSucessWhenCredentialsAreValid() {
        doReturn(BASIC_USERNAME).when(config).getUsername();
        doReturn(BASIC_PASSWORD).when(config).getPassword();
        doReturn(getProductReviews()).when(reviewService).getReviewForProduct(Mockito.anyString());
        AggregatedReview aggregatedReview = reviewController
                .getAggregatedReviewForProduct(AGGREGATED_PRODUCT_ID);

        assertEquals(AGGREGATED_PRODUCT_REVIEW_SCORE, aggregatedReview.getAverageReviewScore());

    }

    @Test
    public void updateProductReturnsSuccessWhenBasicAuthIsValidAndProductUpdatedSuccessfuly() {
        doReturn(BASIC_USERNAME).when(config).getUsername();
        doReturn(BASIC_PASSWORD).when(config).getPassword();
        doReturn(getReviewPojo()).when(reviewService).updateReview(Mockito.any(Long.class),
                Mockito.any(ReviewPojo.class));
        ReviewPojo reviewPojo = reviewController.updateProduct(BASIC_TOKEN_VALID, 123,
                getReviewPojo());

        assertEquals(REVIEW_PRDOUCT_ID, reviewPojo.getProductId());
        verify(config).getUsername();
        verify(config).getPassword();
    }

    @Test
    public void deleteProductDeleteProductsSuccessfulyWhenBasicAuthIsValid() {
        doReturn(BASIC_USERNAME).when(config).getUsername();
        doReturn(BASIC_PASSWORD).when(config).getPassword();
        doNothing().when(reviewService).deleteReview(Mockito.anyLong());

        reviewController.deleteProduct(BASIC_TOKEN_VALID, REVIEW_PRDOUCT_ID);

        verify(config).getUsername();
        verify(config).getPassword();
    }


    @Test
    public void getAllReviewsReturnListOfReviewsWhenGetFromReviewService() {
        doReturn(getListOfReviews()).when(reviewService).getAllReviews();

        List<ReviewPojo> listOfReviews = reviewController.getAllReviews();

        assertEquals(1, listOfReviews.size());
        verify(reviewService).getAllReviews();
    }

    private AggregatedReview getProductReviews() {
        AggregatedReview aggregatedReview = new AggregatedReview();
        aggregatedReview.setNumberOfReviews(3);
        aggregatedReview.setAverageReviewScore(45);
        return aggregatedReview;

    }

    private ReviewPojo getReviewPojo() {
        ReviewPojo reviewPojo = new ReviewPojo();
        reviewPojo.setId(1L);
        reviewPojo.setProductId(REVIEW_PRDOUCT_ID);
        reviewPojo.setReviewScore(22);
        return reviewPojo;

    }

    private List<ReviewPojo> getListOfReviews() {
        ArrayList<ReviewPojo> reviewList = new ArrayList<ReviewPojo>();
        reviewList.add(getReviewPojo());
        return reviewList;
    }

}
