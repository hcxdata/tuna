
package com.hobin.crm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hobin.crm.model.Departments;
import com.hobin.crm.model.dto.DepartmentsDto;
import com.hobin.crm.model.orm.DepartmentsRepository;
import com.hobin.crm.service.DepartmentsService;
import com.hobin.crm.service.exception.AlreadyExistedException;
import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ResourceNotFoundException;
import com.hobin.crm.util.BeanHelper;
import com.hobin.crm.util.DateHelper;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DepartmentsRepository repository;

	@Override
	public void create(DepartmentsDto dto) {
		String departmentName = dto.getDepartmentName();
		if (existsByDepartmentName(departmentName)) {
			throw new AlreadyExistedException("departmentName", departmentName);
		}
		Departments target = new Departments();
		BeanUtils.copyProperties(dto, target, "id");
		target.setCreatedAt(DateHelper.getCurrTimestamp());
		target.setUpdatedAt(DateHelper.getCurrTimestamp());
		repository.saveAndFlush(target);
	}

	@Override
	public void update(Long id, DepartmentsDto dto) {
		Optional<Departments> optional = repository.findById(id);
		if (!optional.isPresent()) {
			logger.error(String.format("Not found Departments with id '%s'", id));
			throw new ResourceNotFoundException("Departments", ErrorCode.NOT_FOUND);
		}
		Departments source = optional.get();
		String departmentName = dto.getDepartmentName();
		if (!source.getDepartmentName().equalsIgnoreCase(departmentName) && existsByDepartmentName(departmentName)) {
			throw new AlreadyExistedException("departmentName", departmentName);
		}		

		BeanUtils.copyProperties(dto, source, BeanHelper.getNullPropertyNames(dto, "id"));
		source.setUpdatedAt(DateHelper.getCurrTimestamp());
		repository.saveAndFlush(source);
	}

	@Override
	public void delete(Long id) {
		if (!exists(id)) {
			throw new ResourceNotFoundException("Departments", ErrorCode.NOT_FOUND);
		}
		repository.deleteById(id);
	}

	private DepartmentsDto toDto(Departments source) {
		if (source != null) {
			DepartmentsDto dto = new DepartmentsDto();
			BeanUtils.copyProperties(source, dto);
			return dto;
		}
		return null;
	}

	@Override
	public DepartmentsDto detail(Long id) {
		Optional<Departments> found = repository.findById(id);
		if (!found.isPresent()) {
			logger.warn("Not found Departments with id '{}'", id);
			throw new ResourceNotFoundException("Departments", ErrorCode.NOT_FOUND);
		}
		return toDto(found.get());
	}

	@Override
	public Page<DepartmentsDto> query(DepartmentsDto dto, Pageable pageable) {
		if (dto == null) {
			return repository.findAll(pageable).map(this::toDto);
		}

		Departments target = new Departments();
		BeanUtils.copyProperties(dto, target);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "createdAt", "updatedAt")
				.withIgnoreCase().withIgnoreNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<Departments> example = Example.of(target, matcher);
		return repository.findAll(example, pageable).map(this::toDto);
	}

	@Override
	public boolean exists(Long id) {
		return repository.findById(id).isPresent();
	}

	@Override
	public List<DepartmentsDto> queryAll() {
		List<DepartmentsDto> result = new ArrayList<DepartmentsDto>();
		for (Departments source : repository.findAll()) {
			result.add(toDto(source));
		}
		return result;
	}

	@Override
	public boolean existsByDepartmentName(String departmentName){
		return repository.findOneByDepartmentName(departmentName) != null;
	}

	@Override
	public DepartmentsDto detailByDepartmentName(String departmentName){
		Departments found = repository.findOneByDepartmentName(departmentName);
		if (found == null) {
			logger.warn("Not found Departments with departmentName '{}'", departmentName);
			throw new ResourceNotFoundException("Departments", ErrorCode.NOT_FOUND);
		}
		return toDto(found);
	}

}