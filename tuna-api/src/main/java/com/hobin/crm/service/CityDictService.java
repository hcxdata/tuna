
package com.hobin.crm.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hobin.crm.model.dto.CityDictDto;

public interface CityDictService {
	void create(CityDictDto dto);

	void update(Integer id, CityDictDto dto);

	void delete(Integer id);

	CityDictDto detail(Integer id);

	Page<CityDictDto> query(CityDictDto dto, Pageable pageable);

	List<CityDictDto> queryAll();

	boolean exists(Integer id);

}

