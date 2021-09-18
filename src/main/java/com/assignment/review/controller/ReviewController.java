package com.assignment.review.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.review.config.ServiceConfiguration;
import com.assignment.review.controller.pojo.AggregatedReview;
import com.assignment.review.controller.pojo.ReviewPojo;
import com.assignment.review.exceptions.AuthorizationException;
import com.assignment.review.service.ReviewService;

@RestController
@RequestMapping(path="/v1.0/review")
public class ReviewController {

    private final ServiceConfiguration config;
    private final ReviewService reviewService;

    public ReviewController(ServiceConfiguration config, ReviewService reviewService) {
        this.config = config;
        this.reviewService = reviewService;
    }

    @GetMapping(path = "")
    public List<ReviewPojo> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping(path = "/{product_id}")
    public AggregatedReview getAggregatedReviewForProduct(@NotBlank @PathVariable ("product_id") String productId) {
        System.out.println("Got call in Specific GET");
        return reviewService.getReviewForProduct(productId);
    }

    @PostMapping(path = "")
    public ReviewPojo addNewReview(@RequestHeader("Authorization") String basicAuth, @Valid @RequestBody
            ReviewPojo reviewPojo) {
        System.out.println("Got call in POST call");
        areBasicAuthCredentialsValid(basicAuth);
        return reviewService.addNewReview(reviewPojo);
    }

    @PutMapping(path = "/{id}")
    public ReviewPojo updateProduct(@RequestHeader("Authorization") String basicAuth, @NotBlank @PathVariable ("id") long id, @Valid @RequestBody
            ReviewPojo reviewPojo) {
        areBasicAuthCredentialsValid(basicAuth);
        return reviewService.updateReview(id, reviewPojo);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@RequestHeader("Authorization") String basicAuth, @NotBlank @PathVariable ("id") String id) {
        System.out.println("Got call in DELETE");
        areBasicAuthCredentialsValid(basicAuth);
        reviewService.deleteReview(Long.parseLong(id));
    }

    public boolean areBasicAuthCredentialsValid(String header) {
        if (StringUtils.isNotBlank(header) || StringUtils.startsWithIgnoreCase(header, "basic")) {
            String base64Credentials = header.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);

            final String[] values = credentials.split(":", 2);

            if (values.length == 2) {
                String username = values[0];
                String password = values[1];
                if (config.getUsername().equals(username) && config.getPassword().equals(password)) {
                    return true;
                } else {
                    throw new AuthorizationException("Username/Password in auth header is not correct");
                }
            } else {
                throw new AuthorizationException("Basic auth token is not valid");
            }
        } else {
            throw new AuthorizationException("Basic auth token is not valid");
        }
    }


}
