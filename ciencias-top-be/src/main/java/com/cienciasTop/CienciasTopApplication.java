package com.cienciasTop;

import java.time.Duration;
import java.time.LocalDate;

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
		/* String [] list_of_users = {"Admin/Prov: Armando","NormalUser: Pugberto","Admin: Andrea","Prov: Ellie"};
		String [] list_of_passwords = {"pugespia","siuuuuu", "1122asswq", "ajolete2021"};
		for(int i = 0; i<list_of_passwords.length; i++) {
			String passwordBcrypt = passwordEncoder.encode(list_of_passwords[i]);
			System.out.println("[" + list_of_users[i] + " ]"
								+ " [ Normal Password: " + list_of_passwords[i] +  " ]"
								+ " [ EncryptedPassword: " + passwordBcrypt + " ]");
		} */
		LocalDate fecha_despues = LocalDate.now();
		//LocalDate fecha_ahora = LocalDate.parse("2001-11-11");
		//Duration dd = Duration.between(fecha_despues, fecha_ahora);
		//System.out.println(fecha_ahora);
		//System.out.println("Dias transcurridos: " + dd.toDays() + " dias");
		//
		
		//DateTimeFormatter isoFecha = DateTimeFormatter.ISO_LOCAL_DATE;
        //System.out.println(fecha.format(isoFecha));
        //DateTimeFormatter isoHora = DateTimeFormatter.ISO_LOCAL_TIME;
        //System.out.println(fecha.format(isoHora));
	}

}
