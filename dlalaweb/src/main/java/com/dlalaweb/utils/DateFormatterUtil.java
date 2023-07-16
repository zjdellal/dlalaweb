package com.dlalaweb.utils;

import java.time.LocalDate;



public class DateFormatterUtil {
	public static LocalDate ConvertStringToLocalDate(String date) {
	
		return LocalDate.parse(date);
	}

}
