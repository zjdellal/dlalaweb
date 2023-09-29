package com.dlalaweb.utils;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

@SuppressWarnings( "serial")
public class ConverterStatutEnumToString implements Converter<StatutEnum, String> {

	@Override
	public Result<String> convertToModel(StatutEnum value, ValueContext context) {
		if (value != null)
			return Result.ok(String.valueOf(value));
		else
			return Result.error("Vérifier le statut");
		
	}

	@Override
	public StatutEnum convertToPresentation(String value, ValueContext context) {
		if(value != null)
		return StatutEnum.valueOf(value);
		else
			return null;
		
	}

}
