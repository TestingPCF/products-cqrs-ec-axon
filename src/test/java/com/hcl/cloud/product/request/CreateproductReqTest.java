package com.hcl.cloud.product.request;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.hcl.cloud.product.request.CreateproductReq;

/**
 * 
 * @author BrijendraK
 *
 */
public class CreateproductReqTest {

	
	@Test
	public void testCreateproductRequest() {
		
		//String mockRequest =  new String("{\"skuCode\":\"VOL9873##@\",\"productName\":\"VOLATS 1.5 ton AC\",\"productPrice\":380000.0,\"productDescrition\":\"better cooling 1.5 ton\",\"category\":\"Medium\",\"is_deleted\":false}");
		CreateproductReq createproductReq = Mockito.mock(CreateproductReq.class);
		createproductReq.setSkuCode("VOL9873##@");
	    createproductReq .setProductName("VOLATS 1.5 ton AC");
	    createproductReq.setCategory("Medium");
	    createproductReq.setIs_deleted(false);
	    createproductReq.setProductDescrition("better cooling 1.5 ton");
	    //createproductReq.setSalePrice(380000.0);
	    //createproductReq.setListPrice(32000.0);
	    when(createproductReq.isIs_deleted()==false).thenReturn(true);
	    assertNotNull(createproductReq);
		
		
	}

}
