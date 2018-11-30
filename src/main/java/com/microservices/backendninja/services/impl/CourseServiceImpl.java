package com.microservices.backendninja.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.microservices.backendninja.controller.CourseController;
import com.microservices.backendninja.entitys.Course;
import com.microservices.backendninja.repository.CourseJpaRepository;
import com.microservices.backendninja.services.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {

	private static final Log LOG = LogFactory.getLog(CourseServiceImpl.class);

	
	@Autowired
	@Qualifier("courseJpaRepository")
	private CourseJpaRepository repository;

	@Override
	public List<Course> listAllCourses() {
		LOG.info("call : listAllCourses param --> " );

		return repository.findAll();
	}

	@Override
	public Course addCourse(Course course) {
		LOG.info("call : addCourse param --> " + course.toString());

		// save devuelve el objeto guardado
		return repository.save(course);
	}

	@Override
	public int deleteCourse(int id) {
		LOG.info("call : deleteCourse param --> " + id);
		repository.deleteById(id);
		return 0;
	}

	@Override
	public Course updateCourse(Course course) {
		LOG.info("call : updateCourse param --> " + course.toString());

		// en este caso va a intentar guardar el curso que queramos guardar, si
		// esta relleno, hace un update
		return repository.save(course);
	}

}
