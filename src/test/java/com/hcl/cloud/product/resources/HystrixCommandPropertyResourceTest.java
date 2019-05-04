/**
 * 
 */
package com.hcl.cloud.product.resources;

import static com.hcl.cloud.product.constants.ProductConstants.COLLAPSER;
import static com.hcl.cloud.product.constants.ProductConstants.COMMAND_KEY_IDENTIFIER;
import static com.hcl.cloud.product.constants.ProductConstants.PROP_VAL_PATTERN;
import static com.hcl.cloud.product.constants.ProductConstants.THREAD_POOL_IDENTIFIER;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.ConfigurationManager;

/**
 * @author BrijendraK
 *
 */
public class HystrixCommandPropertyResourceTest {
    
    private HystrixCommandPropertyResource hysresource;
    static Logger log = LoggerFactory.getLogger(HystrixCommandPropertyResourceTest.class);
    @Test
    public void constructorTest(){
        hysresource = new HystrixCommandPropertyResource();
        
        PropertyResourceManager hystrixProperties = new PropertyResourceManager("/HystrixCommand.properties");
        Set<Object> keys = hystrixProperties.getAllKeys();
        for (Object k : keys) {
            String key = (String) k;
            String value = hystrixProperties.getPropertyValue(key);
            if (value != null) {
                if (key.contains(COMMAND_KEY_IDENTIFIER) || key.contains(THREAD_POOL_IDENTIFIER)
                        || key.contains(COLLAPSER)) {
                    if (value.matches(PROP_VAL_PATTERN)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Long.valueOf(value));

                    } else if ("true".equals(value) || "false".equals(value)) {
                        ConfigurationManager.getConfigInstance().setProperty(key, Boolean.valueOf(value));

                    } else {
                        ConfigurationManager.getConfigInstance().setProperty(key, value);

                    }
                }
            } else {
                log.info("HystrixCommand.properties having the null value for key.The key is:" + key);
            }
        }
        assertNotNull(hysresource);
    }
     
    @Test(expected=AssertionError.class)
    public void constructorExceptionTest(){
        hysresource = new HystrixCommandPropertyResource();
        assertNull(hysresource);
    }

}
