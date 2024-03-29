package com.hcl.cloud.product.datatranslator;

import com.hcl.cloud.product.config.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.response.CreateproductRes;
import static com.hcl.cloud.product.constants.ProductConstants.SUCCESS;

/**
 * 
 * @author BrijendraK
 *
 */
public class CreateProductResponseTranslator {
    static Logger log = LoggerFactory.getLogger(CreateProductResponseTranslator.class);

    @Autowired
    private ConfigLoader configLoader;
    /**
     * This method is used as translator from backend to frontend.
     * 
     * @param createproductReq
     * @param env
     * @return
     */
    public CreateproductRes createproductresponsetranslator(CreateproductReq createproductReq, Environment env)
            throws ProductException {
        log.info("Response translation from backend to frontend start");
        CreateproductRes createproductRes = new CreateproductRes();
        createproductRes.setSkuCode(createproductReq.getSkuCode());
        if (!StringUtils.isEmpty(createproductReq.getStatus())
                && createproductReq.getStatus().equals(SUCCESS)) {
            createproductRes.setStatus(configLoader
                    .getProductCreateSuccessMsg());
            createproductRes.setStatusCode(String
                    .valueOf(HttpStatus.OK.value()));
        } else {
            createproductRes.setStatus(configLoader
                    .getProductAlreadyExistMsg());
            createproductRes.setStatusCode(String
                    .valueOf(HttpStatus.NO_CONTENT.value()));
        }

        log.info("Response translation from backend to frontend end");
        return createproductRes;
    }
}