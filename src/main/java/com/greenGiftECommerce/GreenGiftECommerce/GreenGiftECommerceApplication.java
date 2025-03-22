package com.greenGiftECommerce.GreenGiftECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.greenGiftECommerce.GreenGiftECommerce.Repository")
public class GreenGiftECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenGiftECommerceApplication.class, args);

		System.out.println("Welcome to GreenGiftECommerce ");

	}

}
