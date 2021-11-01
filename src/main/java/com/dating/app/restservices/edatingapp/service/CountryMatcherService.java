package com.dating.app.restservices.edatingapp.service;

public class CountryMatcherService extends UserMatcherService{

	@Override
	public String getQueryName() {
		return queryName="UserEntity.findByCountry";		
	}

	@Override
	public String getQueryFilterName() {
		return queryFilterName="Country";
	}

}
