package com.dating.app.restservices.edatingapp.service;

public class AgeMatcherService extends UserMatcherService{

	@Override
	public String getQueryName() {
		return queryName="UserEntity.findByAge";		
	}

	@Override
	public	String getQueryFilterName() {
		return queryFilterName="Age";
	}

}
