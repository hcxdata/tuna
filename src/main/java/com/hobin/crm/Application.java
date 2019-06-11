package com.hobin.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.hobin.crm.util.RestConfHelper;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.hobin.crm")
public class Application extends SpringBootServletInitializer {

	public static final String NAME = "HOBIN.CRM";
	public static String VERSION;

	public static void main(String[] args) {
		loadVersion();
		RestConfHelper.getInstance().putAllValueInEnv();
		SpringApplication.run(Application.class, args);
	}

	private static void loadVersion() {
		VERSION = Application.class.getPackage().getImplementationVersion();
		if (VERSION == null) {
			VERSION = "unknown";
		}
	}

}
