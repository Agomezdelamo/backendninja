package com.microservices.backendninja.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Log")
public class Log {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="details")
	private String details;
	@Column(name="username")
	private String username;
	@Column(name="url")
	private String url;
	
	public Log(){};
	
	
	public Log(Date fecha, String details, String username, String url) {
		this.fecha = fecha;
		this.details = details;
		this.username = username;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
