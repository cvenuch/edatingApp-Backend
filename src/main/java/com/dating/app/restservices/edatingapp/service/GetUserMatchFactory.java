package com.dating.app.restservices.edatingapp.service;

import org.springframework.stereotype.Service;

import com.dating.app.restservices.edatingapp.exceptions.MatchNotFoundException;

@Service
public class GetUserMatchFactory {
	
	public UserMatcherService getMatch(String MatchType) {
		if(MatchType.equalsIgnoreCase("GENDER")) {  
            return new GenderMatcherService();  
          }
		else if(MatchType.equalsIgnoreCase("AGE")) {  
            return new AgeMatcherService();  
          }
		else if(MatchType.equalsIgnoreCase("CITY")) {  
            return new CityMatcherService();  
          }
		else if(MatchType.equalsIgnoreCase("COUNTRY")) {  
            return new CountryMatcherService();  
          }
		else {
			throw new MatchNotFoundException("Filter Type : " + MatchType + " doesnot exists");
		}
	}

}
