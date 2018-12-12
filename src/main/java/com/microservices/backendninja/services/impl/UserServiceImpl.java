package com.microservices.backendninja.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.microservices.backendninja.entity.UserRole;
import com.microservices.backendninja.repository.UserRepository;

@Service("userService")
// immplemntamos un userDetailsService de spring security
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	/**
	 * @desc Metodo que obtiene un UserDetails para que spring security pueda logar y mantener la seguridad, pasas un username, 
	 * recuperas de la base de datos los datos de ese usuario con el username
	 * y construyes la lista de roles de ese usuario mapeando lso roles que  tiene en base de datos en una lista de objetos de roles de spring security.
	 * 
	 * Una vez obtenido el usuario en base de datos, y los roles de base de datos, construimos un userdetails que es el objeto que finalmente maneja springsecurity.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//OBTENEMOS EL USUARIO DE DB
		com.microservices.backendninja.entity.User user = userRepository.findByUsername(username);
		//CONSTRUIMOS LOS ROLES
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		//OBTENEMOS EL USER DETAILS PASANDO ROLES Y USUARIO
		UserDetails userDetails = buildUser(user, authorities);

		return userDetails;
	}

	// spring security necesita un objeto de tipo user
	/**
	 * Transforma un user y unas authorities en un objeto user del paquete de
	 * springSecurity que es el que usa para saber que el usuario se a
	 * autenticado.
	 * 
	 * @param com.microservices.backendninja.entity.User
	 *            user
	 * @param authorities
	 * @return org.springframework.security.core.userdetails.User
	 */
	private org.springframework.security.core.userdetails.User buildUser(
			com.microservices.backendninja.entity.User user, List<GrantedAuthority> authorities) {

		// los tres true son: usuario no expirado, 
		// credenciales no expirados, cuenta no bloqueada
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	/**
	 * @desc metodo que recibe un set de roles y los transforma a una lista de
	 *       grantedAuthority que es lo que spring security necesita para saber
	 *       que rol tiene el usuario autenticado.
	 * @param List<GrantedAuthority>
	 * @return
	 */
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
		// Generamos un set de grantedAuthorities vacio
		Set<GrantedAuthority> setAuthorities = new HashSet<GrantedAuthority>();

		// mapeamos los roles en el set de authorities
		for (UserRole userRole : userRoles) {
			setAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		// creamos la lista de authorities desde el set
		return new ArrayList<GrantedAuthority>(setAuthorities);
	}

}
