package com.microservices.backendninja.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.backendninja.entitys.Course;
import com.microservices.backendninja.model.CourseModel;

public interface CourseService {
	CourseModel getById(String id);
	
	List<CourseModel> listAllCourses();
	
	CourseModel addCourse(CourseModel courseModel);
	
	int deleteCourse(int id);
	
	CourseModel updateCourse(CourseModel courseModel, String id);
}
