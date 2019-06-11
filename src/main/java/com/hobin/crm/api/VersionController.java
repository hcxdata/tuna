package com.hobin.crm.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hobin.crm.Application;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author xuxx
 */
@Api(tags = "版本信息")
@RestController
@RequestMapping("/")
public class VersionController {

    @ApiOperation("Get the current application version")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, String> versions() {
        Map<String, String> versions = new HashMap<>();
        versions.put("version", Application.VERSION);
        versions.put("name", Application.NAME);
        return versions;
    }
}
