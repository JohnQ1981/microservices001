package com.udemy28002.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	BigDecimal b1 = new BigDecimal("10.0"); 
    BigDecimal b2 = new BigDecimal("65.00");
    //BigDecimal b3 = b1.multiply(b2);
    
    
	@GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public  CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		HashMap<String, String> uriVariables= new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8030/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class,uriVariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		//return new CurrencyConversion(10001L,from, to, quantity,BigDecimal.ONE,BigDecimal.ONE,""); //<== this one is hard coded
		return new CurrencyConversion(currencyConversion.getId(),from, to, quantity,
				currencyConversion.getConversionMultiple(),
				b1.multiply(b2),
				
				//quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment());
		
		
		
	}

}
