package com.retail.ecom.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor@NoArgsConstructor
@Getter
@Setter
public class ContactResponse {
	private int contactId;
	private String name;
	private String email;
	private Long phoneNumber;

}
