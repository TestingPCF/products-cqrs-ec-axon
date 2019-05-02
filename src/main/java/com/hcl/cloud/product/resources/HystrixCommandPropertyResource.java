package com.hcl.cloud.product.resources;

import java.util.Set;

import org.springframework.stereotype.Component;

import static com.hcl.cloud.product.constants.ProductConstants.PROP_VAL_PATTERN;
import static com.hcl.cloud.product.constants.ProductConstants.THREAD_POOL_IDENTIFIER;
import static com.hcl.cloud.product.constants.ProductConstants.COMMAND_KEY_IDENTIFIER;
import static com.hcl.cloud.product.constants.ProductConstants.COLLAPSER;
import com.netflix.config.ConfigurationManager;

/**
 * 
 * @author BrijendraK
 *
 */
@Component
public class HystrixCommandPropertyResource {

    public HystrixCommandPropertyResource() {

        PropertyResourceManager hystrixProperties = new PropertyResourceManager("/HystrixCommand.properties");
        Set<Object> keys = hystrixProperties.getAllKeys();
        for (Object k : keys) {
            String key = (String) k;
            String value = hystrixProperties.getPropertyValue(key);
            if (value != null) {
                if (key.contains(COMMAND_KEY_IDENTIFIER)) {
                    if (value.matches(PROP_VAL_PATTERN)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Long.valueOf(value));

                    } else if ("true".equals(value) || "false".equals(value)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Boolean.valueOf(value));

                    } else {
                        ConfigurationManager.getConfigInstance().setProperty(key, value);

                    }
                } else if (key.contains(THREAD_POOL_IDENTIFIER)) {
                    if (value.matches(PROP_VAL_PATTERN)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Long.valueOf(value));

                    } else if ("true".equals(value) || "false".equals(value)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Boolean.valueOf(value));

                    } else {
                        ConfigurationManager.getConfigInstance().setProperty(key, value);

                    }
                } else if (key.contains(COLLAPSER)) {
                    if (value.matches(PROP_VAL_PATTERN)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Long.valueOf(value));

                    } else if ("true".equals(value) || "false".equals(value)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Boolean.valueOf(value));

                    } else {
                        ConfigurationManager.getConfigInstance().setProperty(key, value);

                    }
                }
            }
        }
    }
}