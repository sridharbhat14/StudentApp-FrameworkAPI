package com.paypalexamples.pojo;

public class Payment_Options {
	private String allowed_payment_method;
	private String soft_descriptor;

	public String getAllowed_payment_method() {
		return allowed_payment_method;
	}

	public void setAllowed_payment_method(String allowed_payment_method) {
		this.allowed_payment_method = allowed_payment_method;
	}

	public String getSoft_descriptor() {
		return soft_descriptor;
	}

	public void setSoft_descriptor(String soft_descriptor) {
		this.soft_descriptor = soft_descriptor;
	}

}
