
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
import com.hobin.crm.model.dto.DepartmentsDto;
import com.hobin.crm.service.DepartmentsService;
import com.hobin.crm.service.exception.MissingPropertyException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "部门")
@RestController
@RequestMapping("/api/Departments")
public class DepartmentsController {

	@Autowired
	private DepartmentsService service;

	@ApiOperation(value = "Create a new Departments")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "One or more properties in '%s' are missing.", response = ErrorResource.class),
			@ApiResponse(code = 409, message = "Resource with '%s'='%s' has already existed.", response = ErrorResource.class) })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(
			@ApiParam(name = "DepartmentsRequest", value = "The request to create an Departments", required = true) @RequestBody DepartmentsDto body,
			HttpServletResponse response) {
		if (StringUtils.isBlank(body.getDepartmentName())) {
			throw new MissingPropertyException(new String[] { "DepartmentName" });
		}
		if (StringUtils.isBlank(body.getShowName())) {
			throw new MissingPropertyException(new String[] { "ShowName" });
		}
		service.create(body);
	}

	@ApiOperation(value = "Get the Departments for the given id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DepartmentsDto detailsById(
			@ApiParam(value = "The Departments id to query") @PathVariable("id") Long id) {
		return service.detail(id);
	}

	@ApiOperation(value = "Update the Departments for the given id")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "One or more properties in '%s' are missing.", response = ErrorResource.class),
			@ApiResponse(code = 409, message = "Resource with '%s'='%s' has already existed.", response = ErrorResource.class) })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody DepartmentsDto body,
			@ApiParam(value = "The Departments id to update") @PathVariable("id") Long id) {
		service.update(id, body);
	}

	@ApiOperation(value = "Delete the Departments from the given id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@ApiParam(value = "The Departments id to delete") @PathVariable("id") Long id) {
		service.delete(id);
	}

	@ApiOperation("List all Departmentss, default to ALL")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "The page number, start from 0"),
			@ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "The page size, like 20"),
			@ApiImplicitParam(name = "sort", paramType = "query", allowMultiple = true, value = "The sort string in format 'property,directory', such as 'name,desc'") })
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public Page<DepartmentsDto> query(@RequestBody DepartmentsDto body, Pageable pageable) {
		return service.query(body, pageable);
	}

	@ApiOperation(value = "Get all Departments list")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public List<DepartmentsDto> queryAll() {
		return service.queryAll();
	}

	@ApiOperation(value = "Get the Departments for the given departmentName")
	@RequestMapping(value = "/departmentName/{departmentName}", method = RequestMethod.GET)
	public DepartmentsDto detailsByDepartmentName(
			@ApiParam(value = "The departmentName to query") @PathVariable("departmentName") String departmentName) {
		return service.detailByDepartmentName(departmentName);
	}
}