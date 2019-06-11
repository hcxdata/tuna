package com.hobin.crm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class RestConfHelper {
	private static final String PFILE = FileHelper.bashPath() + "/conf/application.properties";
	private static final String LOCAL_PFILE = System.getProperty("user.dir")
			+ "/src/main/resources/application.properties";

	private Properties m_props = null;

	private static RestConfHelper m_instance = new RestConfHelper();

	private File file = new File(PFILE);

	private RestConfHelper() {
		if (!file.exists()) {
			file = new File(LOCAL_PFILE);
		}
		m_props = new Properties();
		try {
			m_props.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getConfigItem(String name, Object defaultVal) {

		m_props.clear(); // Get rid of the old properties
		try {
			m_props.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Object val = m_props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

	public Object getConfigItem(String name) {

		m_props.clear(); // Get rid of the old properties
		try {
			m_props.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return m_props.getProperty(name);
	}

	public Map<String, String> getValueMapByPrefix(String prefix) {
		Map<String, String> map = new Hashtable<String, String>();
		try {
			Enumeration<?> en = getPropertiesObject().propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				if (key.matches(prefix + "\\.[^\\.]+")) {
					String value = m_props.getProperty(key);
					map.put(key.substring(key.lastIndexOf(".") + 1), value);
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public Map<String, String> getValueMapByPrefix2(String prefix) {
		Map<String, String> map = new Hashtable<String, String>();
		try {
			Enumeration<?> en = getPropertiesObject().propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				if (key.matches(prefix + "(\\.[^\\.]+)+")) {
					String value = m_props.getProperty(key);
					map.put(key.substring(prefix.length() + 1), value);
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public Map<String, String> getAllValueInMap() throws Exception {
		Map<String, String> map = new Hashtable<String, String>();
		Enumeration<?> en = getPropertiesObject().propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String value = m_props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	public void putAllValueInEnv(){
		Enumeration<?> en = getPropertiesObject().propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String value = m_props.getProperty(key);
			System.setProperty(key, value);
		}
	}

	private Properties getPropertiesObject() {

		m_props.clear();
		try {
			m_props.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m_props;
	}

	public void saveConfigItem(Object key, Object value) {
		FileOutputStream os = null;
		try {
			Properties pro = getPropertiesObject();
			os = new FileOutputStream(PFILE);
			pro.put(key, value);
			pro.store(os, PFILE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	synchronized public static RestConfHelper getInstance() {
		return m_instance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestConfHelper.getInstance().getConfigItem("jdbc.driverClassName");
	}
}
