package com.microservices.backendninja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))																						// unique
public class UserRole {
	

	/**
	 * @Column(unique = true) is a shortcut to UniqueConstraint
	 * when it is only a single field
	 */
	@Id
	@GeneratedValue
	@Column(name="user_role_id",unique=true,nullable=false)
	private Integer userRoleId;

	// esta columna va unida a la tabla user, mediante username, de forma entera EAGER,
	// es decir, te traes el objeto entero en la sessión, y no puede ser null
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false)
	private User user;

	// esta columna no puede ser null
	@Column(name = "role", nullable = false, length = 45)
	private String role;

	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

	public UserRole() {

	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
