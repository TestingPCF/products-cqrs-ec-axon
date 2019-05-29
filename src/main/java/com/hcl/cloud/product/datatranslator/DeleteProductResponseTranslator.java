package com.hcl.cloud.product.datatranslator;

import com.hcl.cloud.product.config.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.response.DeleteproductRes;
import static com.hcl.cloud.product.constants.ProductConstants.SUCCESS;
import static com.hcl.cloud.product.constants.ProductConstants.ALREADY;

/**
 * 
 * @author BrijendraK
 *
 */
public class DeleteProductResponseTranslator {
    static Logger log = LoggerFactory.getLogger(DeleteProductResponseTranslator.class);

    @Autowired
    private ConfigLoader configLoader;

    /**
     * This method is used as translator from backend to frontend.
     * 
     * @param createproductReq
     * @param env
     * @return
     */
    public DeleteproductRes deleteproductresponseTranslator(CreateproductReq createproductReq, Environment env)
            throws ProductException {
        log.info("Response translation from backend to frontend start");
        DeleteproductRes deleteproductRes = new DeleteproductRes();
        if (createproductReq.getStatus().equals(SUCCESS)) {
            deleteproductRes.setSkuCode(createproductReq.getSkuCode());
            deleteproductRes.setStatus(configLoader
                    .getProductDeleteSuccessMsg());
            deleteproductRes.setStatusCode(String.valueOf(HttpStatus
                    .OK.value()));
        } else if (createproductReq.getStatus().equals(ALREADY)) {
            deleteproductRes.setSkuCode(createproductReq.getSkuCode());
            deleteproductRes.setStatus(configLoader
                    .getProductAlreadyDeletedMsg());
            deleteproductRes.setStatusCode(String.valueOf(HttpStatus
                    .NO_CONTENT.value()));
        } else {
            deleteproductRes.setSkuCode(createproductReq.getSkuCode());
            deleteproductRes.setStatus(configLoader
                    .getProductNotExistMsg());
            deleteproductRes.setStatusCode(String.valueOf(HttpStatus
                    .NO_CONTENT.value()));
        }
        log.info("Response translation from backend to frontend end");
        return deleteproductRes;

    }
}