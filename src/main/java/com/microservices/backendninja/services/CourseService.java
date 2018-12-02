package com.microservices.backendninja.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.backendninja.entitys.Course;
import com.microservices.backendninja.model.CourseModel;

public interface CourseService {
	CourseModel getByNameOrPrice(String name, int price);
	
	List<CourseModel> listAllCourses();
	
	CourseModel addCourse(CourseModel courseModel);
	
	int deleteCourse(int id);
	
	CourseModel updateCourse(CourseModel courseModel);
}
