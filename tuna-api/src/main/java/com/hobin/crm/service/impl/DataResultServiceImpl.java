
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

import com.hobin.crm.model.DataResult;
import com.hobin.crm.model.dto.DataResultDto;
import com.hobin.crm.model.orm.DataResultRepository;
import com.hobin.crm.service.DataResultService;
import com.hobin.crm.service.exception.AlreadyExistedException;
import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ResourceNotFoundException;
import com.hobin.crm.util.BeanHelper;

@Service
public class DataResultServiceImpl implements DataResultService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DataResultRepository repository;

	@Override
	public void create(DataResultDto dto) {
		DataResult target = new DataResult();
		BeanUtils.copyProperties(dto, target, "id");
		repository.saveAndFlush(target);
	}

	@Override
	public void update(Integer id, DataResultDto dto) {
		Optional<DataResult> optional = repository.findById(id);
		if (!optional.isPresent()) {
			logger.error(String.format("Not found DataResult with id '%s'", id));
			throw new ResourceNotFoundException("DataResult", ErrorCode.NOT_FOUND);
		}
		DataResult source = optional.get();

		BeanUtils.copyProperties(dto, source, BeanHelper.getNullPropertyNames(dto, "id"));
		repository.saveAndFlush(source);
	}

	@Override
	public void delete(Integer id) {
		if (!exists(id)) {
			throw new ResourceNotFoundException("DataResult", ErrorCode.NOT_FOUND);
		}
		repository.deleteById(id);
	}

	private DataResultDto toDto(DataResult source) {
		if (source != null) {
			DataResultDto dto = new DataResultDto();
			BeanUtils.copyProperties(source, dto);
			return dto;
		}
		return null;
	}

	@Override
	public DataResultDto detail(Integer id) {
		Optional<DataResult> found = repository.findById(id);
		if (!found.isPresent()) {
			logger.warn("Not found DataResult with id '{}'", id);
			throw new ResourceNotFoundException("DataResult", ErrorCode.NOT_FOUND);
		}
		return toDto(found.get());
	}

	@Override
	public Page<DataResultDto> query(DataResultDto dto, Pageable pageable) {
		if (dto == null) {
			return repository.findAll(pageable).map(this::toDto);
		}

		DataResult target = new DataResult();
		BeanUtils.copyProperties(dto, target);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "createdAt", "updatedAt")
				.withIgnoreCase().withIgnoreNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<DataResult> example = Example.of(target, matcher);
		return repository.findAll(example, pageable).map(this::toDto);
	}

	@Override
	public boolean exists(Integer id) {
		return repository.findById(id).isPresent();
	}

	@Override
	public List<DataResultDto> queryAll() {
		List<DataResultDto> result = new ArrayList<DataResultDto>();
		for (DataResult source : repository.findAll()) {
			result.add(toDto(source));
		}
		return result;
	}

}