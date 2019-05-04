/**
 * 
 */
package com.hcl.cloud.product.resources;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author BrijendraK
 *
 */
public class HystrixCommandPropertyResourceTest {
    
    private HystrixCommandPropertyResource hysresource;
    
    @Test
    public void constructorTest(){
        hysresource = new HystrixCommandPropertyResource();
        assertNotNull(hysresource);
    }
     
    @Test(expected = Exception.class)
    public void constructorExceptionTest(){
        hysresource = new HystrixCommandPropertyResource();
        assertNotNull(hysresource);
    }

}
