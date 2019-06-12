
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
import com.hobin.crm.model.dto.CityDictDto;
import com.hobin.crm.service.CityDictService;
import com.hobin.crm.service.exception.MissingPropertyException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "地市字典")
@RestController
@RequestMapping("/api/CityDict")
public class CityDictController {

	@Autowired
	private CityDictService service;

	@ApiOperation(value = "Create a new CityDict")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "One or more properties in '%s' are missing.", response = ErrorResource.class),
			@ApiResponse(code = 409, message = "Resource with '%s'='%s' has already existed.", response = ErrorResource.class) })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(
			@ApiParam(name = "CityDictRequest", value = "The request to create an CityDict", required = true) @RequestBody CityDictDto body,
			HttpServletResponse response) {
		if (StringUtils.isBlank(body.getCityCode())) {
			throw new MissingPropertyException(new String[] { "CityCode" });
		}
		if (StringUtils.isBlank(body.getCityName())) {
			throw new MissingPropertyException(new String[] { "CityName" });
		}
		service.create(body);
	}

	@ApiOperation(value = "Get the CityDict for the given id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CityDictDto detailsById(
			@ApiParam(value = "The CityDict id to query") @PathVariable("id") Integer id) {
		return service.detail(id);
	}

	@ApiOperation(value = "Update the CityDict for the given id")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "One or more properties in '%s' are missing.", response = ErrorResource.class),
			@ApiResponse(code = 409, message = "Resource with '%s'='%s' has already existed.", response = ErrorResource.class) })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody CityDictDto body,
			@ApiParam(value = "The CityDict id to update") @PathVariable("id") Integer id) {
		service.update(id, body);
	}

	@ApiOperation(value = "Delete the CityDict from the given id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@ApiParam(value = "The CityDict id to delete") @PathVariable("id") Integer id) {
		service.delete(id);
	}

	@ApiOperation("List all CityDicts, default to ALL")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "The page number, start from 0"),
			@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "The page size, like 20"),
			@ApiImplicitParam(name = "sort", paramType = "query", allowMultiple = true, value = "The sort string in format 'property,directory', such as 'name,desc'") })
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<CityDictDto> query(@RequestBody CityDictDto body, Pageable pageable) {
		return service.query(body, pageable);
	}

	@ApiOperation(value = "Get all CityDict list")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public List<CityDictDto> queryAll() {
		return service.queryAll();
	}

}