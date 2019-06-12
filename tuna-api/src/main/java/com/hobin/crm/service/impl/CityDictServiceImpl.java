
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

import com.hobin.crm.model.CityDict;
import com.hobin.crm.model.dto.CityDictDto;
import com.hobin.crm.model.orm.CityDictRepository;
import com.hobin.crm.service.CityDictService;
import com.hobin.crm.service.exception.AlreadyExistedException;
import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ResourceNotFoundException;
import com.hobin.crm.util.BeanHelper;

@Service
public class CityDictServiceImpl implements CityDictService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityDictRepository repository;

	@Override
	public void create(CityDictDto dto) {
		CityDict target = new CityDict();
		BeanUtils.copyProperties(dto, target, "id");
		repository.saveAndFlush(target);
	}

	@Override
	public void update(Integer id, CityDictDto dto) {
		Optional<CityDict> optional = repository.findById(id);
		if (!optional.isPresent()) {
			logger.error(String.format("Not found CityDict with id '%s'", id));
			throw new ResourceNotFoundException("CityDict", ErrorCode.NOT_FOUND);
		}
		CityDict source = optional.get();

		BeanUtils.copyProperties(dto, source, BeanHelper.getNullPropertyNames(dto, "id"));
		repository.saveAndFlush(source);
	}

	@Override
	public void delete(Integer id) {
		if (!exists(id)) {
			throw new ResourceNotFoundException("CityDict", ErrorCode.NOT_FOUND);
		}
		repository.deleteById(id);
	}

	private CityDictDto toDto(CityDict source) {
		if (source != null) {
			CityDictDto dto = new CityDictDto();
			BeanUtils.copyProperties(source, dto);
			return dto;
		}
		return null;
	}

	@Override
	public CityDictDto detail(Integer id) {
		Optional<CityDict> found = repository.findById(id);
		if (!found.isPresent()) {
			logger.warn("Not found CityDict with id '{}'", id);
			throw new ResourceNotFoundException("CityDict", ErrorCode.NOT_FOUND);
		}
		return toDto(found.get());
	}

	@Override
	public Page<CityDictDto> query(CityDictDto dto, Pageable pageable) {
		if (dto == null) {
			return repository.findAll(pageable).map(this::toDto);
		}

		CityDict target = new CityDict();
		BeanUtils.copyProperties(dto, target);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "createdAt", "updatedAt")
				.withIgnoreCase().withIgnoreNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<CityDict> example = Example.of(target, matcher);
		return repository.findAll(example, pageable).map(this::toDto);
	}

	@Override
	public boolean exists(Integer id) {
		return repository.findById(id).isPresent();
	}

	@Override
	public List<CityDictDto> queryAll() {
		List<CityDictDto> result = new ArrayList<CityDictDto>();
		for (CityDict source : repository.findAll()) {
			result.add(toDto(source));
		}
		return result;
	}

}