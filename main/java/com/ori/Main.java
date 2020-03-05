package com.ori;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ori.api.UserApi;
import com.ori.entities.User;

@SpringBootApplication

public class Main {

	
	public static void main(String[] args) throws Exception {
		
		Date today = new Date();
		Timestamp timestamp = new Timestamp(today.getTime());
		System.out.println(timestamp);

		
				
		SpringApplication.run(Main.class, args);
				
			
				

				
				
				
		
				


//				ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);
//				UsersApi usersApi = ctx.getBean(UsersApi.class);
//				User user = new User("Avi", "1234");
//				usersApi.createUser(user);
				
				
				// Can add additional configuration files.
				//ctx.register(SpringConfig1.class, SpringConfig2.class);
				//ctx.refresh();
			}
		

	}


