package com.hcl.cloud.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.cloud.product.request.CreateproductReq;
/**
 * 
 * @author Brijendra and Kapil
 *
 */
public interface ProductRepository extends JpaRepository<CreateproductReq, String>{

}
