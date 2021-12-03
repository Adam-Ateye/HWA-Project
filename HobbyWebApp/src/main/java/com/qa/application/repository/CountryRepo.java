package com.qa.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.qa.application.model.Country;

public interface CountryRepo extends JpaRepository<Country, Long> {

	@Query(value = "select * from country join login on country.name = login.username where id = ?1",nativeQuery = true)
	Optional<Country> findByID(Long id);
}
