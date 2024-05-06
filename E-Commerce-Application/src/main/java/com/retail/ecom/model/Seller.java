package com.retail.ecom.model;

import org.springframework.context.annotation.Configuration;
import com.retail.ecom.model.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Seller  extends User {
	
	@OneToOne
	private Address address;

}
