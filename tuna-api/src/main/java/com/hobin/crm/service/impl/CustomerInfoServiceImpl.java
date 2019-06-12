
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

import com.hobin.crm.model.CustomerInfo;
import com.hobin.crm.model.dto.CustomerInfoDto;
import com.hobin.crm.model.orm.CustomerInfoRepository;
import com.hobin.crm.service.CustomerInfoService;
import com.hobin.crm.service.exception.AlreadyExistedException;
import com.hobin.crm.service.exception.ErrorCode;
import com.hobin.crm.service.exception.ResourceNotFoundException;
import com.hobin.crm.util.BeanHelper;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerInfoRepository repository;

	@Override
	public void create(CustomerInfoDto dto) {
		CustomerInfo target = new CustomerInfo();
		BeanUtils.copyProperties(dto, target, "id");
		repository.saveAndFlush(target);
	}

	@Override
	public void update(Integer id, CustomerInfoDto dto) {
		Optional<CustomerInfo> optional = repository.findById(id);
		if (!optional.isPresent()) {
			logger.error(String.format("Not found CustomerInfo with id '%s'", id));
			throw new ResourceNotFoundException("CustomerInfo", ErrorCode.NOT_FOUND);
		}
		CustomerInfo source = optional.get();

		BeanUtils.copyProperties(dto, source, BeanHelper.getNullPropertyNames(dto, "id"));
		repository.saveAndFlush(source);
	}

	@Override
	public void delete(Integer id) {
		if (!exists(id)) {
			throw new ResourceNotFoundException("CustomerInfo", ErrorCode.NOT_FOUND);
		}
		repository.deleteById(id);
	}

	private CustomerInfoDto toDto(CustomerInfo source) {
		if (source != null) {
			CustomerInfoDto dto = new CustomerInfoDto();
			BeanUtils.copyProperties(source, dto);
			return dto;
		}
		return null;
	}

	@Override
	public CustomerInfoDto detail(Integer id) {
		Optional<CustomerInfo> found = repository.findById(id);
		if (!found.isPresent()) {
			logger.warn("Not found CustomerInfo with id '{}'", id);
			throw new ResourceNotFoundException("CustomerInfo", ErrorCode.NOT_FOUND);
		}
		return toDto(found.get());
	}

	@Override
	public Page<CustomerInfoDto> query(CustomerInfoDto dto, Pageable pageable) {
		if (dto == null) {
			return repository.findAll(pageable).map(this::toDto);
		}

		CustomerInfo target = new CustomerInfo();
		BeanUtils.copyProperties(dto, target);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "createdAt", "updatedAt")
				.withIgnoreCase().withIgnoreNullValues()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		Example<CustomerInfo> example = Example.of(target, matcher);
		return repository.findAll(example, pageable).map(this::toDto);
	}

	@Override
	public boolean exists(Integer id) {
		return repository.findById(id).isPresent();
	}

	@Override
	public List<CustomerInfoDto> queryAll() {
		List<CustomerInfoDto> result = new ArrayList<CustomerInfoDto>();
		for (CustomerInfo source : repository.findAll()) {
			result.add(toDto(source));
		}
		return result;
	}

}