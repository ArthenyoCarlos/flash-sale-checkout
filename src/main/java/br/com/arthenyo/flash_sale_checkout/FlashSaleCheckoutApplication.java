package br.com.arthenyo.flash_sale_checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

@SpringBootApplication
@EnableResilientMethods
public class FlashSaleCheckoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashSaleCheckoutApplication.class, args);
	}

}
