package com.hcl.cloud.product.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpEventPublicationConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(AmqpEventPublicationConfiguration.class);

    @Value("${axon.amqp.exchange:ProductEvents}")
    String exchangeName;

    //    Add @Bean annotated exchange() method to declare a spring amqp Exchange
    //    Return the exchange from ExchangeBuilder.fanoutExchange(“ComplaintEvents”).build();

    @Bean
    public Exchange exchange(){
    	LOG.info("Executing exchange");
        return ExchangeBuilder.fanoutExchange(exchangeName).build();
    }

    //    Add @Bean annotated queue() method to declate a Queue
    //    Return the queue from QueueBuilder.durable(“ComplaintEvents”).build()

    @Bean
    public Queue queue(){
    	LOG.info("Executing queue");
        return QueueBuilder.durable(exchangeName).build();
    }

    //    Add @Bean annotated binding() method to declare a Binding
    //    Return the binding from BindingBuilder.bind(queue()).to(exchange()).with(“*”).noargs()

    @Bean
    public Binding binding(Queue queue, Exchange exchange){
    	LOG.info("Executing binding");
        return BindingBuilder.bind(queue).to(exchange).with("*").noargs();
    }

    //    Add @Autowired method to configure(AmqpAdmin admin)
    //    Make admin.declareExchange(exchange());
    //    Make admin.declareQueue(queue());
    //    Make admin.declareBinding(binding());

    @Autowired
    public void configure(AmqpAdmin amqpAdmin, Exchange exchange, Queue queue, Binding binding){
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
        LOG.info("Executing configure with binding, queue, exchange");
    }
}
