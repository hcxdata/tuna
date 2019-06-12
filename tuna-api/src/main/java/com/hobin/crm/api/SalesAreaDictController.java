
package com.hobin.crm.api;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hobin.crm.api.support.ErrorResource;
import com.hobin.crm.model.dto.SalesAreaDictDto;
import com.hobin.crm.service.SalesAreaDictService;
import com.hobin.crm.service.exception.MissingPropertyException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "销售区域")
@RestController
@RequestMapping("/api/SalesAreaDict")
public class SalesAreaDictController {

	@Autowired
	private SalesAreaDictService service;

	@ApiOperation(value = "Create a new SalesAreaDict")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "One or more properties in '%s' are missing.", response = ErrorResource.class),
			@ApiResponse(code = 409, message = "Resource with '%s'='%s' has already existed.", response = ErrorResource.class) })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(
			@ApiParam(name = "SalesAreaDictRequest", value = "The request to create an SalesAreaDict", required = true) @RequestBody SalesAreaDictDto body,
			HttpServletResponse response) {
		if (StringUtils.isBlank(body.getSalesAreaCode())) {
			throw new MissingPropertyException(new String[] { "SalesAreaCode" });
		}
		if (StringUtils.isBlank(body.getSalesAreaName())) {
			throw new MissingPropertyException(new String[] { "SalesAreaName" });
		}
		service.create(body);
	}

	@ApiOperation(value = "Get the SalesAreaDict for the given id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SalesAreaDictDto detailsById(
			@ApiParam(value = "The SalesAreaDict id to query") @PathVariable("id") Integer id) {
		return service.detail(id);
	}

	@ApiOperation(value = "Update the SalesAreaDict for the given id")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "One or more properties in '%s' are missing.", response = ErrorResource.class),
			@ApiResponse(code = 409, message = "Resource with '%s'='%s' has already existed.", response = ErrorResource.class) })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody SalesAreaDictDto body,
			@ApiParam(value = "The SalesAreaDict id to update") @PathVariable("id") Integer id) {
		service.update(id, body);
	}

	@ApiOperation(value = "Delete the SalesAreaDict from the given id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@ApiParam(value = "The SalesAreaDict id to delete") @PathVariable("id") Integer id) {
		service.delete(id);
	}

	@ApiOperation("List all SalesAreaDicts, default to ALL")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "The page number, start from 0"),
			@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "The page size, like 20"),
			@ApiImplicitParam(name = "sort", paramType = "query", allowMultiple = true, value = "The sort string in format 'property,directory', such as 'name,desc'") })
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<SalesAreaDictDto> query(@RequestBody SalesAreaDictDto body, Pageable pageable) {
		return service.query(body, pageable);
	}

	@ApiOperation(value = "Get all SalesAreaDict list")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public List<SalesAreaDictDto> queryAll() {
		return service.queryAll();
	}

}