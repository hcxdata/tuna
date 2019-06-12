
package com.hobin.crm.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hobin.crm.model.dto.SalesAreaDictDto;

public interface SalesAreaDictService {
	void create(SalesAreaDictDto dto);

	void update(Integer id, SalesAreaDictDto dto);

	void delete(Integer id);

	SalesAreaDictDto detail(Integer id);

	Page<SalesAreaDictDto> query(SalesAreaDictDto dto, Pageable pageable);

	List<SalesAreaDictDto> queryAll();

	boolean exists(Integer id);

}

