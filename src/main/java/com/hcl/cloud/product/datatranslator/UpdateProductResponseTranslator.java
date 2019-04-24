package com.hcl.cloud.product.datatranslator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.response.UpdateproductRes;
import static com.hcl.cloud.product.constants.ProductConstants.SUCCESS;


/**
 * @author BrijendraK
 *
 */
public class UpdateProductResponseTranslator {
	static Logger log = LoggerFactory.getLogger(UpdateProductResponseTranslator.class);

	/**
	 * This method is used as translator from backend to frontend.
	 * 
	 * @param creReq
	 * @param env
	 * @return
	 */
	public UpdateproductRes updateProductResponseTranslator(CreateproductReq creReq, Environment env) {
		log.info("Response translation from backend to frontend start");
		UpdateproductRes updateproductRes = new UpdateproductRes();
		if (!StringUtils.isEmpty(creReq.getStatus()) && creReq.getStatus().equals(SUCCESS)) {
			updateproductRes.setStatus(env.getProperty("product.update.successmsg"));
			updateproductRes.setStatusCode(String.valueOf(HttpStatus.OK.value()));
		} else {
			updateproductRes.setStatus(env.getProperty("product.notexistmsg"));
			updateproductRes.setStatusCode(String.valueOf(HttpStatus.NO_CONTENT.value()));
		}
		log.info("Response translation from backend to frontend end");
		return updateproductRes;
	}
}