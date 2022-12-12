package com.cienciasTop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CienciasTopApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CienciasTopApplication.class, args);
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		String [] list_of_users = {"Admin/Prov: Armando","NormalUser: Pugberto","Admin: Andrea","Prov: Ellie"};
		String [] list_of_passwords = {"pugespia","siuuuuu", "1122asswq", "ajolete2021"};
		for(int i = 0; i<list_of_passwords.length; i++) {
			String passwordBcrypt = passwordEncoder.encode(list_of_passwords[i]);
			System.out.println("[" + list_of_users[i] + " ]"
								+ " [ Normal Password: " + list_of_passwords[i] +  " ]"
								+ " [ EncryptedPassword: " + passwordBcrypt + " ]");
		}
	}

}
