package com.dating.app.restservices.edatingapp.service;

public class GenderMatcherService extends UserMatcherService{

	@Override
	public String getQueryName() {
		return queryName="UserEntity.findByGender";		
	}

	@Override
	public String getQueryFilterName() {
		return queryFilterName="genDer";
	}

}
