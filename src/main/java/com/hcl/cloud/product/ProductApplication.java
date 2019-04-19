package com.hcl.cloud.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.PropertySource;

import com.hcl.cloud.product.controller.ProductController;
/**
 * 
 * @author BrijendraK
 *
 */
@EnableHystrix
@EnableCircuitBreaker
@SpringBootApplication
@PropertySource("classpath:product.properties")
@PropertySource("classpath:HystrixCommand.properties")
public class ProductApplication {

	static Logger log = LoggerFactory.getLogger(ProductController.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
