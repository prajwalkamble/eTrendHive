package com.etrendhive.service;

import com.etrendhive.exception.ProductException;
import com.etrendhive.model.Cart;
import com.etrendhive.model.CartItem;
import com.etrendhive.model.User;
import com.etrendhive.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId,AddItemRequest req)throws ProductException;
	
	public Cart findUserCart(Long userId);
	
	
}
