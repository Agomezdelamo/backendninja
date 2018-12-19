package com.microservices.backendninja.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * test que comprueba la encriptacion de spring
 * @author agome
 *
 */
public class TestEncrypt {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("super"));
	}
}
