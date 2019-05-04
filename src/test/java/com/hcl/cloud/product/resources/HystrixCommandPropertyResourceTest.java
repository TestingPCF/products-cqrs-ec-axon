/**
 * 
 */
package com.hcl.cloud.product.resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author BrijendraK
 *
 */
public class HystrixCommandPropertyResourceTest {

    private HystrixCommandPropertyResource hysresource;
    static Logger log = LoggerFactory.getLogger(HystrixCommandPropertyResourceTest.class);

    @Test
    public void constructorTest() {
        hysresource = new HystrixCommandPropertyResource("");
        assertNotNull(hysresource);
    }

    @Test(expected = AssertionError.class)
    public void constructorExceptionTest() {
        hysresource = new HystrixCommandPropertyResource("");
        assertNull(hysresource);
    }

}
