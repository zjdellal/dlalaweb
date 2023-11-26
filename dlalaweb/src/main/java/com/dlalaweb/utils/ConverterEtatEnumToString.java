package com.dlalaweb.utils;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

@SuppressWarnings( "serial")
public class ConverterEtatEnumToString implements Converter<EtatEnum, String> {

	@Override
	public Result<String> convertToModel(EtatEnum value, ValueContext context) {
		if (value != null)
			return Result.ok(String.valueOf(value));
		else
			return Result.ok(null);
		
	}

	@Override
	public EtatEnum convertToPresentation(String value, ValueContext context) {
		if(value != null && !value.isEmpty())
		return EtatEnum.valueOf(value);
		else
			return null;
		
	}

}
