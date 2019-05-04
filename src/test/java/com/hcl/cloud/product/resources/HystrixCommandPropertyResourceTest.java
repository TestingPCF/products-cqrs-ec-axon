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
        assertNotNull(hysresource);
    }
     
    @Test(expected=AssertionError.class)
    public void constructorExceptionTest(){
        hysresource = new HystrixCommandPropertyResource();
        assertNull(hysresource);
    }

}
