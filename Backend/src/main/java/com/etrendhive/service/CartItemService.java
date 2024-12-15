package com.etrendhive.service;

import com.etrendhive.exception.CartItemException;
import com.etrendhive.exception.UserException;
import com.etrendhive.model.Cart;
import com.etrendhive.model.CartItem;
import com.etrendhive.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
