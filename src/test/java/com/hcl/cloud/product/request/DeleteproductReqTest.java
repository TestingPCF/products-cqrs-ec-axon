package com.hcl.cloud.product.request;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.cloud.product.request.DeleteproductReq;
/**
 * 
 * @author BrijendraK
 *
 */
@SpringBootTest
public class DeleteproductReqTest {
	@Test(expected = NullPointerException.class)
	public void testDeleteproductRequest() {

		DeleteproductReq deReq = Mockito.spy(DeleteproductReq.class);
		when(deReq.getSkuCode() == null).thenThrow(NullPointerException.class);
		assertNull(deReq.getSkuCode());

	}

}
