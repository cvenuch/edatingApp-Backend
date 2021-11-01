package com.dating.app.restservices.edatingapp.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ListToStringConverter implements AttributeConverter<List<String>, String>{

	private  static final String SPLIT_CHAR = ",";
	List<String> emptyList = new ArrayList<String>();
	
	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		return attribute!=null ? String.join(SPLIT_CHAR, attribute) : "";
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		return dbData!=null ? Arrays.asList(dbData.split(SPLIT_CHAR)): emptyList;
	}

}
