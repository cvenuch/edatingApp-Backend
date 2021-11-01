package com.dating.app.restservices.edatingapp.service;

public abstract class UserMatcherService {
	
	protected String queryName;
	protected String queryFilterName;
	public abstract String getQueryName();
	public abstract String getQueryFilterName();
	
}
