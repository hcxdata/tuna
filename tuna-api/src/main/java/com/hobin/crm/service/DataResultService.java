
package com.hobin.crm.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hobin.crm.model.dto.DataResultDto;

public interface DataResultService {
	void create(DataResultDto dto);

	void update(Integer id, DataResultDto dto);

	void delete(Integer id);

	DataResultDto detail(Integer id);

	Page<DataResultDto> query(DataResultDto dto, Pageable pageable);

	List<DataResultDto> queryAll();

	boolean exists(Integer id);

}

