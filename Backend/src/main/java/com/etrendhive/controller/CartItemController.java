package com.etrendhive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etrendhive.exception.CartItemException;
import com.etrendhive.exception.UserException;
import com.etrendhive.model.CartItem;
import com.etrendhive.model.User;
import com.etrendhive.response.ApiResponse;
import com.etrendhive.service.CartItemService;
import com.etrendhive.service.UserService;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,@RequestHeader("Authorization") String jwt) throws UserException,CartItemException{
		
		User user=userService.findUserProfileByJWT(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res=new ApiResponse();
		res.setMessage("Item deleted from the Cart");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem,@PathVariable Long cartItemId,@RequestHeader("Authorization") String jwt) throws UserException,CartItemException{
		User user=userService.findUserProfileByJWT(jwt);
		CartItem updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		
		return new ResponseEntity<>(updatedCartItem,HttpStatus.OK);
	}
}
