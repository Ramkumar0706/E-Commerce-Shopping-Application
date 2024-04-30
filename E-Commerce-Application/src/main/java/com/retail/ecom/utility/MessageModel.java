package com.retail.ecom.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Getter
public class MessageModel {
	
	private String to;
	private String subject;
	private String text;

}
