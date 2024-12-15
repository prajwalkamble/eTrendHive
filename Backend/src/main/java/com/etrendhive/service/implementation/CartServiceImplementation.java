package com.etrendhive.service.implementation;

import org.springframework.stereotype.Service;

import com.etrendhive.exception.ProductException;
import com.etrendhive.model.Cart;
import com.etrendhive.model.CartItem;
import com.etrendhive.model.PaymentDetails;
import com.etrendhive.model.Product;
import com.etrendhive.model.User;
import com.etrendhive.repository.CartRepository;
import com.etrendhive.request.AddItemRequest;
import com.etrendhive.service.CartItemService;
import com.etrendhive.service.CartService;
import com.etrendhive.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@AllArgsConstructor
public class CartServiceImplementation implements CartService{
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;

	@Override
	public Cart createCart(User user) {
		Cart cart=new Cart();
		cart.setUser(user);
		Cart createdCart=cartRepository.save(cart);
		return createdCart;
	}

	@Override
	public CartItem addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart=cartRepository.findByUserId(userId);
		Product product=productService.findProductById(req.getProductId());
		CartItem isPresent=cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		
		if(isPresent==null) {
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price=req.getQuantity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem=cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			return createdCartItem;
		}
	
		return isPresent;
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart=cartRepository.findByUserId(userId);
		
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		
		for(CartItem cartItem:cart.getCartItems()) {
			totalPrice=totalPrice+cartItem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountedPrice();
			totalItem=totalItem+cartItem.getQuantity();
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem); 
		cart.setTotalPrice(totalPrice);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		
		return cartRepository.save(cart);
	}

}
