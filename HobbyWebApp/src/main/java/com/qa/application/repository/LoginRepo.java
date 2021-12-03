package com.qa.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.qa.application.model.Login;

public interface LoginRepo extends JpaRepository<Login, Long> {
	
	@Query(value = "select * from login join country on login.username = country.name where id=?1", nativeQuery= true)
	Optional<Login> findById(Long id);
	

}
