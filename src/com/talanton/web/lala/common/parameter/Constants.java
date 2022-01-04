package com.talanton.web.lala.common.parameter;

import com.talanton.web.lala.common.listener.CommonParameter;

public class Constants {
	public static final String PC_LIST_TYPE = "2";
	public static final String PC_MAIN_TYPE = "3";
	public static final String PC_EXPOSE_TYPE = "4";
	public static final String MOBILE_LIST_TYPE = "5";
	public static final String MOBILE_MAIN_TYPE = "6";
	public static final String MOBILE_EXPOSE_TYPE = "7";
	public static final String STYLE_SHOP_CATEGORY = "0020000";

	public static Object getParameter(String name) {
		CommonParameter cp = CommonParameter.getInstance();
		return cp.get(name);
	}
}