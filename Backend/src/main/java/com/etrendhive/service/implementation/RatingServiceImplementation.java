package com.etrendhive.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.etrendhive.exception.ProductException;
import com.etrendhive.model.Product;
import com.etrendhive.model.Rating;
import com.etrendhive.model.User;
import com.etrendhive.repository.RatingRepository;
import com.etrendhive.request.RatingRequest;
import com.etrendhive.service.ProductService;
import com.etrendhive.service.RatingService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
public class RatingServiceImplementation implements RatingService {
	
	private RatingRepository ratingRepository;
	private ProductService productService;

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}

}
