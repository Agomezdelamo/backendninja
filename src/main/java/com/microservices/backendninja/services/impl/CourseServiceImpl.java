package com.microservices.backendninja.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.microservices.backendninja.controller.CourseController;
import com.microservices.backendninja.converter.CourseConverter;
import com.microservices.backendninja.entitys.Course;
import com.microservices.backendninja.model.CourseModel;
import com.microservices.backendninja.repository.CourseJpaRepository;
import com.microservices.backendninja.services.CourseService;


@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {

	private static final Log LOG = LogFactory.getLog(CourseServiceImpl.class);

	@Autowired
	@Qualifier("courseConverter")
	private CourseConverter converter;
	
	@Autowired
	@Qualifier("courseJpaRepository")
	private CourseJpaRepository repository;
	
	@Override
	public CourseModel getByNameOrPrice(String name, int price) {
		return converter.entity2model(repository.findByNameOrPrice(name, price));
	};

	
	@Override
	public List<CourseModel> listAllCourses() {
		LOG.info("call : listAllCourses param --> " );
		
		//aplicamos lógica de negocio para convertir la lista de Entitys a Lista de Models y que la vista lo entienda
		List<CourseModel> list2View = new ArrayList<CourseModel>();
		
		//consulta db
		List<Course> listRepo = repository.findAll();
		
		Iterator i = listRepo.iterator();
		
		while(i.hasNext()) {
			Course c = (Course) i.next();
			CourseModel cm = converter.entity2model(c);
			list2View.add(cm);
		}
		
		return list2View;
	}

	@Override
	public CourseModel addCourse(CourseModel courseModel) {
		LOG.info("call : addCourse param --> " + courseModel.toString());

		//aplicamos lógica de negocio para convertir el Model a Entity y que el DAO lo entienda
		Course course = converter.model2entity(courseModel);
		
		// save devuelve el objeto guardado como entity y lo transformamos a la vista
		return converter.entity2model(repository.save(course));
	}

	@Override
	public int deleteCourse(int id) {
		LOG.info("call : deleteCourse param --> " + id);
		repository.deleteById(id);
		return 0;
	}

	@Override
	public CourseModel updateCourse(CourseModel courseModel) {
		LOG.info("call : updateCourse param --> " + courseModel.toString());
		
		//trato de traerlo del repo (con la ñapa esta, al final no me garantiza q sea unico...
		Course course = repository.findByNameOrPrice(courseModel.getName(), 0);
		//sino esta...
		if(course == null) {
			//aplicamos lógica de negocio para convertir el Model a Entity y que el DAO lo entienda
			course = converter.model2entity(courseModel);
		} else {
			//asi mantiene el id de referencia y se machaca..
			course.setName(courseModel.getName());
			course.setHours(courseModel.getHours());
			course.setPrice(courseModel.getPrice());
			course.setDescription(courseModel.getDescription());
		}
		
		
		// en este caso va a intentar guardar el curso que queramos guardar, si
		// esta relleno, hace un update, ojo, no pilla bien el update, he tenido q traerlo de db para q lo pille bien
		return converter.entity2model(repository.save(course));
	}

}
