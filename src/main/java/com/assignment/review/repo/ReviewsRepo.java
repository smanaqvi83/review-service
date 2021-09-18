package com.assignment.review.repo;


import com.assignment.review.entity.Reviews;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewsRepo extends CrudRepository<Reviews, Long> {

    List<Reviews> findAll();
    List<Reviews> findByProductId(String productId);
}
