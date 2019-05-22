package com.hcl.cloud.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.hcl.cloud.product.controller.ProductController;

/**
 * 
 * @author Brijendra and Kapil
 *
 */
@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@PropertySource("classpath:product.properties")
@PropertySource("classpath:HystrixCommand.properties")
public class ProductApplication {

    static Logger log = LoggerFactory.getLogger(ProductApplication.class);

    /**
     * @param args
     *            the argument for main
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    /**
     * @return JedisConnectionFactory
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

}
