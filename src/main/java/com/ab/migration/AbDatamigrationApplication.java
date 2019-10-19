package com.ab.migration;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

//Jpa Auditing 어노테이션 활성화를 위함
@EnableJpaAuditing
@SpringBootApplication
public class AbDatamigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbDatamigrationApplication.class, args);
		
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(new ClassPathResource("/firebase/firebaseAdminKey.json").getInputStream()))
					.setDatabaseUrl("https://agdatadatamigration.firebaseio.com")
					.build();
			
			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
}
