package com.etrendhive.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.etrendhive.exception.ProductException;
import com.etrendhive.model.Address;
import com.etrendhive.model.PaymentInformation;
import com.etrendhive.model.Product;
import com.etrendhive.model.Rating;
import com.etrendhive.model.Review;
import com.etrendhive.model.User;
import com.etrendhive.repository.ProductRepository;
import com.etrendhive.repository.ReviewRepository;
import com.etrendhive.request.ReviewRequest;
import com.etrendhive.service.ProductService;
import com.etrendhive.service.ReviewService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
public class ReviewServiceImplementation implements ReviewService{
	
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		product.getReviews().add(review);
		productRepository.save(product);
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}
