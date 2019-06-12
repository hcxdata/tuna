
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

import com.hobin.crm.model.SalesAreaDict;
import com.hobin.crm.model.dto.SalesAreaDictDto;
import com.hobin.crm.model.orm.SalesAreaDictRepository;
import com.hobin.crm.service.SalesAreaDictService;
import com.hobin.crm.service.exception.AlreadyExistedException;
import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ResourceNotFoundException;
import com.hobin.crm.util.BeanHelper;

@Service
public class SalesAreaDictServiceImpl implements SalesAreaDictService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SalesAreaDictRepository repository;

	@Override
	public void create(SalesAreaDictDto dto) {
		SalesAreaDict target = new SalesAreaDict();
		BeanUtils.copyProperties(dto, target, "id");
		repository.saveAndFlush(target);
	}

	@Override
	public void update(Integer id, SalesAreaDictDto dto) {
		Optional<SalesAreaDict> optional = repository.findById(id);
		if (!optional.isPresent()) {
			logger.error(String.format("Not found SalesAreaDict with id '%s'", id));
			throw new ResourceNotFoundException("SalesAreaDict", ErrorCode.NOT_FOUND);
		}
		SalesAreaDict source = optional.get();

		BeanUtils.copyProperties(dto, source, BeanHelper.getNullPropertyNames(dto, "id"));
		repository.saveAndFlush(source);
	}

	@Override
	public void delete(Integer id) {
		if (!exists(id)) {
			throw new ResourceNotFoundException("SalesAreaDict", ErrorCode.NOT_FOUND);
		}
		repository.deleteById(id);
	}

	private SalesAreaDictDto toDto(SalesAreaDict source) {
		if (source != null) {
			SalesAreaDictDto dto = new SalesAreaDictDto();
			BeanUtils.copyProperties(source, dto);
			return dto;
		}
		return null;
	}

	@Override
	public SalesAreaDictDto detail(Integer id) {
		Optional<SalesAreaDict> found = repository.findById(id);
		if (!found.isPresent()) {
			logger.warn("Not found SalesAreaDict with id '{}'", id);
			throw new ResourceNotFoundException("SalesAreaDict", ErrorCode.NOT_FOUND);
		}
		return toDto(found.get());
	}

	@Override
	public Page<SalesAreaDictDto> query(SalesAreaDictDto dto, Pageable pageable) {
		if (dto == null) {
			return repository.findAll(pageable).map(this::toDto);
		}

		SalesAreaDict target = new SalesAreaDict();
		BeanUtils.copyProperties(dto, target);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "createdAt", "updatedAt")
				.withIgnoreCase().withIgnoreNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<SalesAreaDict> example = Example.of(target, matcher);
		return repository.findAll(example, pageable).map(this::toDto);
	}

	@Override
	public boolean exists(Integer id) {
		return repository.findById(id).isPresent();
	}

	@Override
	public List<SalesAreaDictDto> queryAll() {
		List<SalesAreaDictDto> result = new ArrayList<SalesAreaDictDto>();
		for (SalesAreaDict source : repository.findAll()) {
			result.add(toDto(source));
		}
		return result;
	}

}