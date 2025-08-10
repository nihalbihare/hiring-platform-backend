package com.hiring_platform.Hiring.Platform;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableScheduling
public class HiringPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiringPlatformApplication.class, args);
	}
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((req->
				req.requestMatchers("/**").permitAll().anyRequest().authenticated()));
		http.csrf(csrf->csrf.disable());
		return http.build();
	}
}
