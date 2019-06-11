package com.hobin.crm.util;

import org.apache.commons.lang3.StringUtils;

public class StringHelper {

	public static String capitalizeBySplit(String str, String split) {
		String text = "";
		for (String s : str.split(split)) {
			text = text + StringUtils.capitalize(s);
		}
		return text;
	}

	public static void main(String[] args) {
		System.out.println(capitalizeBySplit("codegen_templates", "_"));
	}

}
