package com.lava.lavafaq.springmvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class DateConverter implements Converter<String, Date> {

	public static final DateFormat DF_LONG = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DF_YEAR = new SimpleDateFormat("yyyy");
	public static final DateFormat DF_MONTH = new SimpleDateFormat("yyyy-MM");
	/**
	 * 短类型日期长度
	 */
	public static final int SHORT_DATE = 10;

	public static final int YEAR_DATE = 4;

	public static final int MONTH_DATE = 7;

	@Override
	public Date convert(String text) {
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			return null;
		}

		try {
			if (text.length() <= YEAR_DATE) {
				return DF_YEAR.parse(text);
			} else if (text.length() <= MONTH_DATE) {
				return DF_MONTH.parse(text);
			} else if (text.length() <= SHORT_DATE) {
				return DF_SHORT.parse(text);
			} else {
				return DF_LONG.parse(text);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}