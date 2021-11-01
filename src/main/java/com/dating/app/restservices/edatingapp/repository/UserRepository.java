package com.dating.app.restservices.edatingapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dating.app.restservices.edatingapp.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Boolean existsByUsername(String username);
		
}
