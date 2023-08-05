package com.mycourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    public static final String[] ENDPOINTS_WHITELIST = {
            "/assets/**",
            "/",
            "/login",
            "/registration"
    };
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    private static final String SQL_LOGIN = "select u.username, up.hashed_password as password, true as active "
            + "from s_user u inner join s_user_password up on u.id = up.id_s_user "
            + "where u.username = ?";

    private static final String SQL_PERMISSION = """
	select u.username, p.value as authority 
	from s_user u 
	inner join s_role r on r.id = u.id_s_role 
	inner join s_role_permission rp on rp.id_role = r.id 
	inner join s_permission p on rp.id_permission = p.id 
	where u.username = ?""";


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Bean
    public static UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(SQL_LOGIN);
        manager.setAuthoritiesByUsernameQuery(SQL_PERMISSION);
        return manager;
    }

    @Bean @Order(1)
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request ->
                        request.requestMatchers(ENDPOINTS_WHITELIST).permitAll()
                                .anyRequest().authenticated())
                .csrf().disable()
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .failureUrl(LOGIN_FAIL_URL)
                        .defaultSuccessUrl("/dashboard", true))
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
