package com.etrendhive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etrendhive.exception.ProductException;
import com.etrendhive.exception.UserException;
import com.etrendhive.model.Cart;
import com.etrendhive.model.CartItem;
import com.etrendhive.model.User;
import com.etrendhive.request.AddItemRequest;
import com.etrendhive.response.ApiResponse;
import com.etrendhive.service.CartService;
import com.etrendhive.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.findUserProfileByJWT(jwt);
		Cart cart=cartService.findUserCart(user.getId());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization")String jwt) throws UserException,ProductException{
		User user=userService.findUserProfileByJWT(jwt);
		CartItem item = cartService.addCartItem(user.getId(), req);
		
		
		ApiResponse res=new ApiResponse();
		res.setMessage("Item added to the cart");
		res.setStatus(true);
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}
	

}
