package com.microservices.backendninja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.microservices.backendninja.services.impl.UserServiceImpl;

/**
 * Clase que configura spring security a la aplicación
 * 
 * @author agome
 *
 */
@Configuration
@EnableWebSecurity
//anotación que habilita el control el acceso a los métodos de los controladores según la seguridad
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userService")
	private UserServiceImpl userService;

	/**
	 * Registramos el servicio que tenemos para autenticar con spring security
	 * 
	 * @param authManagerBuilder
	 *            objeto que registra el servicio de autenticacion
	 * @throws Exception
	 */
	public void configureGlobal(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		// registramos el servicio que tenemos para autenticar y el encoder para
		// codificar la password en la base de datos.
		// de esta forma la password en db nunca esta visible.
		authManagerBuilder.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * @desc Método que configura la securizacion de las llamadas http
	 * 1: permite que todas las request http para /css/* e /imgs/* no necesiten seguridad
	 * 2: el resto de request que no sean esas necesitaran que estes logado
	 * 3: establece la página de login, su controlador, los parametros de entrada para ese controlador
	 * 	  y la página a la que redirige cuando te logas que esta sin securizar
	 * 4: establece la página de logout, y la página a la que redirige cuando te deslogas que esta sin securizar
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		super.configure(http);
		//aqui indicamos las request autorizadas sin necesidad de ningun login
		http.authorizeRequests().antMatchers("/css/*","/imgs/*").permitAll()
		//aqui indicamos que el resto de request necesitan autenticar o logar para poder acceder
		.anyRequest().authenticated()
		//aqui indicamos cual es la página de login
		.and().formLogin().loginPage("/login")
		//Es el path encargado de iniciar el proceso de login, se pone en el post del formulario HTML. Spring Security lo realiza por detrás.
		.loginProcessingUrl("/logincheck")
		//aqui indicamos los parametros de entrada de user y pass que estan puestos en la vista del formulario
		.usernameParameter("userparam").passwordParameter("passwordparam")
		//aqui indicamos una página a la que dirigirse una vez estas logado 
		//(el requestPath del controlador que esta en la clase LoginController) y esa pagina esta abierta sin securizar
		.defaultSuccessUrl("/loginsuccess").permitAll()
		//aqui indicamos cual es la página de logout
		.and().logout().logoutUrl("/logout")
		//aqui indicamos una página a la que dirigirse una vez estas deslogado y esa pagina esta abierta sin securizar
		.logoutSuccessUrl("/login?logout").permitAll()
		//pagina de acceso denegador
		.and().exceptionHandling().accessDeniedPage("/403");
		
	}

}
