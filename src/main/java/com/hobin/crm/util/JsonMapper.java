package com.hobin.crm.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * An utility class mapping between objects and JSON data strings.
 * 
 * @author xuxx
 */
public class JsonMapper {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ObjectMapper jsonMapper = new ObjectMapper();

	public String toJson(Object obj) {
		try {
			return jsonMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.warn("Convert data to json string failed", e);
			return null;
		}
	}

	public <T> T readValue(String jsonStr, Class<T> valueType) {
		try {
			return jsonMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			logger.warn("Convert data to bean failed", e);
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		System.out.println(new JsonMapper().toJson(map));
	}
}
