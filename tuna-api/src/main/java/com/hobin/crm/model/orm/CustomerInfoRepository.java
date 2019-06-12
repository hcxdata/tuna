
package com.hobin.crm.model.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hobin.crm.model.CustomerInfo;

public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Integer> {

}
