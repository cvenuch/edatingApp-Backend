package com.dating.app.restservices.edatingapp.service;

public class CityMatcherService extends UserMatcherService{

	@Override
	public String getQueryName() {
		return queryName="UserEntity.findByCity";		
	}

	@Override
	public String getQueryFilterName() {
		return queryFilterName="City";
	}

}
