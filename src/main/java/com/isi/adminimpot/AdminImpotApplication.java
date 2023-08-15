package com.isi.adminimpot;

import com.isi.adminimpot.dao.DeclarantDao;
import com.isi.adminimpot.entities.Declarant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdminImpotApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminImpotApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(DeclarantDao declarantDao){
		return args -> {
			declarantDao.save(new Declarant(null, "Test", "Dakarrrr", "bah@test.com", "77777777"));
			declarantDao.save(new Declarant(null, "Test2", "Pita", "test@test.com", "77777777"));
			declarantDao.save(new Declarant(null, "Test3", "Guinea", "gn@test.com", "77777777"));
			declarantDao.findAll().forEach(p->{
				System.out.println(p.getEmail());
			});
		};
	}

}