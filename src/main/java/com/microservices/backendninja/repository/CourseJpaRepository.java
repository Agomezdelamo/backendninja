package com.microservices.backendninja.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.backendninja.entitys.Course;

@Repository("courseJpaRepository")
public interface CourseJpaRepository extends JpaRepository<Course, Serializable> {
	
	//esta definición ya lleva una implementación de jpa que buscaria por un campo
	//supongo que usando AOP, en este caso findBy y lo que siga si es un campo de una entidad
	//lo va a usar y darle ya una implementación por defecto.
	Course findByPrice(int price);
	
	//lo mismo con dos campos
	Course findByPriceAndName(int price, String name);
	List<Course> findByNameOrderByHours(String name);
	Course findByNameOrPrice(String name, int price);

	
	
}
