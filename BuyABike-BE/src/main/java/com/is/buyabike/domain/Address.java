package com.is.buyabike.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String houseNumber;
	private String city;
	private String state;
	private String country;

}
