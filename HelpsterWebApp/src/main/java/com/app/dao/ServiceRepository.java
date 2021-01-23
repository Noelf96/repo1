package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Helpster;
import com.app.pojos.Service;

public interface ServiceRepository extends JpaRepository<Service,Integer>{
		Optional<Service> findByServiceNameAndCategory(String serviceType,String category);
		List<Service> findByAssociatedHelpster(Helpster h);
		List<Service> findByServiceNameAndCategoryAndCityAndLocality(String service_name, String category, String city,String locality);
		List<Service> findByServiceNameAndCityAndLocality(String service_name, String city,String locality);
		List<Service> findByServiceNameAndCategoryAndCity(String service_name, String category, String city);
		List<Service> findByServiceNameAndCity(String service_name, String city);
}
