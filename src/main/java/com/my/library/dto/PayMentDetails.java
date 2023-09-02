package com.my.library.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PayMentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String amount;
	String currency;
	String paymentId;
	String orderId;
	String status;
	String keyDetails;

}
