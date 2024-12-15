package com.etrendhive.service;

import java.util.*;

import com.etrendhive.exception.ProductException;
import com.etrendhive.model.Rating;
import com.etrendhive.model.User;
import com.etrendhive.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	public List<Rating> getProductsRating(Long productId);

}
