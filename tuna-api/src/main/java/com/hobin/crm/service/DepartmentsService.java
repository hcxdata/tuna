
package com.hobin.crm.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hobin.crm.model.dto.DepartmentsDto;

public interface DepartmentsService {
	void create(DepartmentsDto dto);

	void update(Long id, DepartmentsDto dto);

	void delete(Long id);

	DepartmentsDto detail(Long id);

	Page<DepartmentsDto> query(DepartmentsDto dto, Pageable pageable);

	List<DepartmentsDto> queryAll();

	boolean exists(Long id);

	boolean existsByDepartmentName(String departmentName);

	DepartmentsDto detailByDepartmentName(String departmentName);

}

