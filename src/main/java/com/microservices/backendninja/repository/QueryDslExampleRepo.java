package com.microservices.backendninja.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.microservices.backendninja.entitys.Course;
import com.microservices.backendninja.entitys.QCourse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("QueryDslExampleRepo")
public class QueryDslExampleRepo {
	
	//se pone una q y el nombre de la entidad
	//si no detecta la QCourse, hago desde el cmd en el directorio del proyecto
	// mvn eclipe:eclipse y genera las QClases dentro del project
	QCourse qcourse = QCourse.course;
	
	//entity manager se encarga de la persistencia de la aplicacion
	@PersistenceContext
	private EntityManager em;
	
	
	//metodo que genera una consulta querydsl
	public void find(boolean exist){
		//con esta clase hacemos la consulta, pasandole el contexto de la persistencia de la aplicación
		JPAQuery<Course> qQuery = new JPAQuery<Course>(em);
		
		//objeto que aglutina una condicion
		Predicate predicate1 = qcourse.description.endsWith(".");
		
		//objeto que aglutina varias condiciones
		BooleanBuilder bb = new BooleanBuilder(predicate1);
		
		if(exist) {
			Predicate predicate2 = qcourse.id.eq(9);
			bb.and(predicate2);
			
		} else {
			Predicate predicate3 = qcourse.name.endsWith("gular");
			bb.and(predicate3);
		}
		
		//generamos una query y recuperamos un resultado
		Course course = (Course) qQuery.select(qcourse.name, qcourse.description).from(qcourse).where(bb).fetchOne();
	
		//seleccionamos todas las columnas de curso entre 5 y 20 horas
		List<Course> list = (List<Course>) qQuery.select(qcourse).from(qcourse).where(qcourse.hours.between(5, 20)).fetch();
	}
	
}
