package com.retail.ecom.model;

import org.springframework.context.annotation.Configuration;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
@Configuration
@Entity
public class Seller  extends User {

}
