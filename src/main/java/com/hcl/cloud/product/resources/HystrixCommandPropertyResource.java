package com.hcl.cloud.product.resources;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.netflix.config.ConfigurationManager;
/**
 * 
 * @author BrijendraK
 *
 */
@Component
public class HystrixCommandPropertyResource {

    private final static String THREAD_POOL_IDENTIFIER = "ThreadPool.";
    private final static String COMMAND_KEY_IDENTIFIER = "Command.";
    private final static String COLLAPSER = "Collapser";

    public HystrixCommandPropertyResource() {

        PropertyResourceManager hystrixProperties = new PropertyResourceManager("/HystrixCommand.properties");
        Set<Object> keys = hystrixProperties.getAllKeys();
        for (Object k : keys) {
            String key = (String) k;
            String value = hystrixProperties.getPropertyValue(key);
            if (value != null) {
                if (key.contains(COMMAND_KEY_IDENTIFIER)) {
                    if (value.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Long.valueOf(value));
                        //							System.out.println(key+"="+value);
                    } else if ("true".equals(value) || "false".equals(value)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Boolean.valueOf(value));
                        //							System.out.println(key+"="+value);
                    } else {
                        ConfigurationManager.getConfigInstance().setProperty(key, value);
                        //							System.out.println(key+"="+value);
                    }
                } else if (key.contains(THREAD_POOL_IDENTIFIER)) {
                    if (value.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Long.valueOf(value));
                        //							System.out.println(key+"="+value);
                    } else if ("true".equals(value) || "false".equals(value)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Boolean.valueOf(value));
                        //							System.out.println(key+"="+value);
                    } else {
                        ConfigurationManager.getConfigInstance().setProperty(key, value);
                        //							System.out.println(key+"="+value);
                    }
                } else if (key.contains(COLLAPSER)) {
                    if (value.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Long.valueOf(value));
                        //							System.out.println(key+"="+value);
                    } else if ("true".equals(value) || "false".equals(value)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Boolean.valueOf(value));
                        //							System.out.println(key+"="+value);
                    } else {
                        ConfigurationManager.getConfigInstance().setProperty(key, value);
                        //							System.out.println(key+"="+value);
                    }
                }
            }
        }
    }
}