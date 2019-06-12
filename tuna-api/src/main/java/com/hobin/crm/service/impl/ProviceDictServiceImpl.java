
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

import com.hobin.crm.model.ProviceDict;
import com.hobin.crm.model.dto.ProviceDictDto;
import com.hobin.crm.model.orm.ProviceDictRepository;
import com.hobin.crm.service.ProviceDictService;
import com.hobin.crm.service.exception.AlreadyExistedException;
import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ResourceNotFoundException;
import com.hobin.crm.util.BeanHelper;

@Service
public class ProviceDictServiceImpl implements ProviceDictService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProviceDictRepository repository;

	@Override
	public void create(ProviceDictDto dto) {
		ProviceDict target = new ProviceDict();
		BeanUtils.copyProperties(dto, target, "id");
		repository.saveAndFlush(target);
	}

	@Override
	public void update(Integer id, ProviceDictDto dto) {
		Optional<ProviceDict> optional = repository.findById(id);
		if (!optional.isPresent()) {
			logger.error(String.format("Not found ProviceDict with id '%s'", id));
			throw new ResourceNotFoundException("ProviceDict", ErrorCode.NOT_FOUND);
		}
		ProviceDict source = optional.get();

		BeanUtils.copyProperties(dto, source, BeanHelper.getNullPropertyNames(dto, "id"));
		repository.saveAndFlush(source);
	}

	@Override
	public void delete(Integer id) {
		if (!exists(id)) {
			throw new ResourceNotFoundException("ProviceDict", ErrorCode.NOT_FOUND);
		}
		repository.deleteById(id);
	}

	private ProviceDictDto toDto(ProviceDict source) {
		if (source != null) {
			ProviceDictDto dto = new ProviceDictDto();
			BeanUtils.copyProperties(source, dto);
			return dto;
		}
		return null;
	}

	@Override
	public ProviceDictDto detail(Integer id) {
		Optional<ProviceDict> found = repository.findById(id);
		if (!found.isPresent()) {
			logger.warn("Not found ProviceDict with id '{}'", id);
			throw new ResourceNotFoundException("ProviceDict", ErrorCode.NOT_FOUND);
		}
		return toDto(found.get());
	}

	@Override
	public Page<ProviceDictDto> query(ProviceDictDto dto, Pageable pageable) {
		if (dto == null) {
			return repository.findAll(pageable).map(this::toDto);
		}

		ProviceDict target = new ProviceDict();
		BeanUtils.copyProperties(dto, target);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "createdAt", "updatedAt")
				.withIgnoreCase().withIgnoreNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<ProviceDict> example = Example.of(target, matcher);
		return repository.findAll(example, pageable).map(this::toDto);
	}

	@Override
	public boolean exists(Integer id) {
		return repository.findById(id).isPresent();
	}

	@Override
	public List<ProviceDictDto> queryAll() {
		List<ProviceDictDto> result = new ArrayList<ProviceDictDto>();
		for (ProviceDict source : repository.findAll()) {
			result.add(toDto(source));
		}
		return result;
	}

}