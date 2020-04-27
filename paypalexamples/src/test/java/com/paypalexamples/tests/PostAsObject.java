package com.paypalexamples.tests;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.paypalexamples.base.BaseClass;
import com.paypalexamples.pojo.Amount;
import com.paypalexamples.pojo.Details;
import com.paypalexamples.pojo.Item_List;
import com.paypalexamples.pojo.Items;
import com.paypalexamples.pojo.Payer;
import com.paypalexamples.pojo.Payment_Options;
import com.paypalexamples.pojo.Redirect_urls;
import com.paypalexamples.pojo.Shipping_address;
import com.paypalexamples.pojo.Transactions;
import com.paypalexamples.pojo.PostObj;

public class PostAsObject extends BaseClass{
	
	static String payment_id;
	
	@Test
	public void createAPayment() {
	
		//Create Redirect urls
		
     Redirect_urls red_urls = new Redirect_urls();
     red_urls.setCancel_url( "https://amazon.com/cancel");
     red_urls.setReturn_url("https://flipkart.com/return");
     
     //create Details
     
     Details details = new Details();
     details.setHandling_fee("1.00");
     details.setInsurance("0.01");
     details.setShipping("0.03");
     details.setShipping_discount("-1.00");
     details.setSubtotal("30.00");
     details.setTax("0.07");
     
     //create Amount
     
     Amount amount = new Amount();
     amount.setCurrency("USD");
     amount.setDetails(details);
     amount.setTotal("30.11");
     
     //Set the shipping address
     
     Shipping_address shipping_address = new Shipping_address();
     shipping_address.setRecepient_name("Brian Robinson");
     shipping_address.setLine1("4th Floor");
     shipping_address.setLine2("Unit #34");
     shipping_address.setCity("San Jose");
     shipping_address.setState("CA");
     shipping_address.setPostal_code("95131");
     shipping_address.setCountry_code("US");
     shipping_address.setPhone("011862212345678");
     
     //set the items
     
     Items item = new Items();
             item.setName("hat");
    	     item.setDescription("Brown hat");
    		 item.setQuantity("5");
    		 item.setPrice("3");
    		 item.setTax("0.01");
    		 item.setSku("1");
    		 item.setCurrency("USD");
    		 
    		 
    	     Items item2= new Items();
    	             item2.setName("handbag");
    	    	     item2.setDescription("Black handbag");
    	    		 item2.setQuantity("1");
    	    		 item2.setPrice("15");
    	    		 item2.setTax("0.02");
    	    		 item2.setSku("product34");
    	    		 item2.setCurrency("USD");
    	    		 
    	    		 List<Items> items =  new ArrayList<>();
    	    		 items.add(item);
    	    		 items.add(item2);
    	    		 
    	    		 
    	    		 //Item List
    	    		 Item_List item_list = new Item_List();
    	    		 item_list.setShipping_address(shipping_address);
    	    		 item_list.setItems(items);
    	    		 
    	    		 //Set payment options
    	    		 
    	    		 Payment_Options payment_options = new Payment_Options();
    	    		 payment_options.setAllowed_payment_method("INSTANT_FUNDING_SOURCE");
    	    			
    	    		 //Set Transactions
    	    		 
    	    		 Transactions transactions = new Transactions(); 
    	    		 transactions.setAmount(amount);
    	    		 transactions.setCustom("EBAY_EMS_90048630024435");
    	    		 transactions.setDescription("The payment transaction description.");
    	    		 transactions.setInvoice_number("48787589673");
    	    		 transactions.setPayment_options(payment_options);
    	    		 transactions.setSoft_descriptor("ECHI5786786");
    	    		 transactions.setItem_list(item_list);
    	    		 
    	    		 
    	    		
    	    		 
    	    		 //set Payer
    	    		 
    	    		 Payer payer = new Payer();
    	    		 payer.setPayment_method("paypal");
    	    		 
    	    		 List<Transactions> transactions1 = new ArrayList<>();
    	    		 transactions1.add(transactions);
    	    		 
    	    		 //create post object request
    	    		 
    	    		 PostObj postObj = new PostObj();
    	    		 postObj.setIntent("sale");
    	    		 postObj.setNote_to_payer("contact Us");
    	    		 postObj.setRedirect_urls(red_urls);
    	    		 postObj.setPayer(payer);
    	    		 postObj.setTransactions(transactions);
    	    		 
     
    	    		 payment_id = given()
		.contentType(ContentType.JSON)
		.auth()
		.oauth2(accessToken)
		.when()
        .body(postObj)
        .post("/payments/payment")
        .then()
        .log()
        .all()
        .extract()
        .path("id");
        
	}
	
	
	@Test
	public void getPaymentDetails() {
		
		given()
		.contentType(ContentType.JSON)
		.auth()
		.oauth2(accessToken)
		.when()
       
        .get("/payments/payment" +payment_id)
        .then()
        .log()
        .all();
	}
	

}


