package com.dating.app.restservices.edatingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dating.app.restservices.edatingapp.entity.InterestsEntity;

@Repository
public interface InterestsRepository extends JpaRepository<InterestsEntity, Long>{
			
	
}
