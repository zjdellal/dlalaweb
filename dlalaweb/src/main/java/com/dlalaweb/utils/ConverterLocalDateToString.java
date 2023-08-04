package com.dlalaweb.utils;

import java.time.LocalDate;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

@SuppressWarnings("serial")
public class ConverterLocalDateToString implements Converter<LocalDate, String> {

	@Override
	public Result<String> convertToModel(LocalDate value, ValueContext context) {
		if (value != null)
			return Result.ok(String.valueOf(value));
		else
			return Result.error("Vérifier date d'achat");
	}

	@Override
	public LocalDate convertToPresentation(String value, ValueContext context) {
	
		if(value != null)
		return LocalDate.parse(value);
		else
			return LocalDate.now();
		
	}


}