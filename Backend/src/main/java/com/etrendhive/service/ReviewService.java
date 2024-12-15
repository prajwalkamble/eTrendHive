package com.etrendhive.service;

import java.util.*;

import com.etrendhive.exception.ProductException;
import com.etrendhive.model.Review;
import com.etrendhive.model.User;
import com.etrendhive.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	public List<Review> getAllReview(Long productId);
}
