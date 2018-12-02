package com.microservices.backendninja.converter;

import org.springframework.stereotype.Component;

import com.microservices.backendninja.entitys.Course;
import com.microservices.backendninja.model.CourseModel;

@Component("courseConverter")
public class CourseConverter {

	//entity --> model, lo que viene de la db para que lo entienda la vista y que solo tenga lo que necesita la vista
	public CourseModel entity2model(Course course) {
		CourseModel courseModel = new CourseModel();
		
		courseModel.setName(course.getName());	
		courseModel.setDescription(course.getDescription());
		courseModel.setHours(course.getHours());	
		courseModel.setPrice(course.getPrice());	
		
		return courseModel;
	}
	
	//model --> entity, lo que viene de la vista para que lo entienda la db y tenga todo lo que necesita la db.
	public Course model2entity(CourseModel courseModel) {
		Course course = new Course();
		
		course.setName(courseModel.getName());
		course.setDescription(courseModel.getDescription());
		course.setHours(courseModel.getHours());
		course.setPrice(courseModel.getPrice());
		
		return course;
	}
}
