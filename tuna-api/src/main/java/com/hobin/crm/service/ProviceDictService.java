
package com.hobin.crm.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hobin.crm.model.dto.ProviceDictDto;

public interface ProviceDictService {
	void create(ProviceDictDto dto);

	void update(Integer id, ProviceDictDto dto);

	void delete(Integer id);

	ProviceDictDto detail(Integer id);

	Page<ProviceDictDto> query(ProviceDictDto dto, Pageable pageable);

	List<ProviceDictDto> queryAll();

	boolean exists(Integer id);

}

