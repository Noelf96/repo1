package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Helpster;

public interface HelpsterRepository extends JpaRepository<Helpster,Integer> {
	
	Optional<Helpster> findByEmailAndPassword(String email,String password); 
	Optional<Helpster> findByEmail(String email); 
	
	
}
