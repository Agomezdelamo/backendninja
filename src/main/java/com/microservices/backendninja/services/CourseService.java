package com.microservices.backendninja.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.backendninja.entitys.Course;

public interface CourseService {
	
	List<Course> listAllCourses();
	
	Course addCourse(Course course);
	
	int deleteCourse(int id);
	
	Course updateCourse(Course course);
}
