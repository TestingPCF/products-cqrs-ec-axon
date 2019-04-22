package com.hcl.cloud.product.response;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import com.hcl.cloud.product.response.DeleteproductRes;
/**
 * 
 * @author BrijendraK
 *
 */
public class DeleteproductResTest {
	@Test
	public void testDeleteproductRes() {
		DeleteproductRes delRes = Mockito.mock(DeleteproductRes.class);
		delRes.setSkuCode("ABC_12");
		delRes.setStatus("product deleted successfuly");
		delRes.setStatusCode(String.valueOf(HttpStatus.OK.value()));
		assertNotNull("message", delRes);
	}

}
