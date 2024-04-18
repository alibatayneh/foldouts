package com.foldouts.foldouts;

import com.foldouts.foldouts.dao.RoleRepository;
import com.foldouts.foldouts.dao.UserRepository;
import com.foldouts.foldouts.models.ApplicationUser;
import com.foldouts.foldouts.models.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FoldoutsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoldoutsApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
//		return args -> {
//			Role adminRole = new Role("ROLE_ADMIN");
//			if(roleRepository.findByAuthority(adminRole.getAuthority()).isPresent()) return;
//
//			roleRepository.save(adminRole);
//			roleRepository.save(new Role("ROLE_USER"));
//
//			Set<Role> roles = new HashSet<>();
//			roles.add(adminRole);
//
//			ApplicationUser admin = new ApplicationUser("admin", passwordEncoder.encode("password"), roles);
//			userRepository.save(admin);
//		};
//	}
}
