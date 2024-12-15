package com.etrendhive.request;

import java.time.LocalDateTime;
import java.util.List;

import com.etrendhive.model.Address;
import com.etrendhive.model.PaymentInformation;
import com.etrendhive.model.Rating;
import com.etrendhive.model.Review;
import com.etrendhive.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddItemRequest {
	
	private Long productId;
	private String size;
	private int quantity;
	private Integer price;
	
	

}
