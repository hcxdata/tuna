
package com.hobin.crm.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hobin.crm.model.dto.CustomerInfoDto;

public interface CustomerInfoService {
	void create(CustomerInfoDto dto);

	void update(Integer id, CustomerInfoDto dto);

	void delete(Integer id);

	CustomerInfoDto detail(Integer id);

	Page<CustomerInfoDto> query(CustomerInfoDto dto, Pageable pageable);

	List<CustomerInfoDto> queryAll();

	boolean exists(Integer id);

}

