package com.hcl.cloud.product.response;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import com.hcl.cloud.product.response.CreateproductRes;
/**
 * 
 * @author BrijendraK
 *
 */
public class CreateproductResTest {

	@Test
	public void testCreateproductRes() {
		CreateproductRes cerRes = Mockito.mock(CreateproductRes.class);
		cerRes.setSkuCode("ABC_12");
		cerRes.setStatus("product created successfuly");
		cerRes.setStatusCode(HttpStatus.OK.value());
		assertNotNull("message", cerRes);
	}
}
