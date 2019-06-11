package com.hobin.crm.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import com.hobin.crm.service.EncryptService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private EncryptService phoneEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users").permitAll()
				.antMatchers(HttpMethod.HEAD, "/api/users/*").permitAll()
				.antMatchers(HttpMethod.GET, "/index.html").permitAll()
				.antMatchers(HttpMethod.GET, "/index").permitAll()
				.antMatchers(HttpMethod.POST, "/api/users/actions/reset-password").permitAll()
				.antMatchers(HttpMethod.GET, "/api/settings/configs").permitAll().anyRequest().authenticated().and()
				.httpBasic().and().logout().permitAll().and().csrf().disable();
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new Sha256PasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(timsUserDetailsManager()).passwordEncoder(passwordEncoder());
	}

	public UserDetailsManager timsUserDetailsManager() {
		TimsJdbcUserDetailsManager result = new TimsJdbcUserDetailsManager();
		result.setDataSource(dataSource);
		result.setUsersByUsernameQuery(
				"select username, password, (status != 3) as enabled " + "from users where username=? or phone=?");
		result.setAuthoritiesByUsernameQuery(
				"select u.username,r.role_name from users u left join user_roles ur on u.id = ur.user_id left join roles r on ur.role_id = r.id where u.username=? or u.phone=?");
		return result;
	}

	/**
	 * Override default {@link JdbcUserDetailsManager} to support login using phone
	 * number.
	 * <p>
	 * This manager now ONLY supports 'USER' role, and can't assign by group.
	 * </p>
	 */
	class TimsJdbcUserDetailsManager extends JdbcUserDetailsManager {
		private static final String DEFAULT_USERS_BY_USERNAME_OR_PHONE_QUERY = "select username, password, (status != 3) as enabled from users where username=? or phone=?";
		private static final String DEFAULT_AUTHORITIES_BY_USERNAME_OR_PHONE_QUERY = "select u.username,r.role_name from users u left join user_roles ur on u.id = ur.user_id left join roles r on ur.role_id = r.id where u.username=? or u.phone=?";

		@Override
		protected List<UserDetails> loadUsersByUsername(String username) {
			String[] params = prepareParams(username);
			return getJdbcTemplate().query(DEFAULT_USERS_BY_USERNAME_OR_PHONE_QUERY, params,
					new RowMapper<UserDetails>() {
						@Override
						public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
							String username = rs.getString(1);
							String password = rs.getString(2);
							boolean enabled = rs.getBoolean(3);
							return new User(username, password, enabled, true, true, true,
									AuthorityUtils.NO_AUTHORITIES);
						}
					});
		}

		@Override
		protected List<GrantedAuthority> loadUserAuthorities(String username) {
			String[] params = prepareParams(username);
			return getJdbcTemplate().query(DEFAULT_AUTHORITIES_BY_USERNAME_OR_PHONE_QUERY, params,
					new RowMapper<GrantedAuthority>() {
						@Override
						public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
							String roleName = getRolePrefix() + rs.getString(2);

							return new SimpleGrantedAuthority(roleName);
						}
					});
		}

		private String[] prepareParams(String username) {
			String[] result = new String[2];
			result[0] = username;
			// The db stores the encoded phone.
			result[1] = phoneEncoder.encrypt(username);
			return result;
		}
	}
}
