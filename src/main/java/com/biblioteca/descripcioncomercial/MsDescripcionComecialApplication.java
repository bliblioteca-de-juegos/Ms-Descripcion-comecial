package com.biblioteca.descripcioncomercial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsDescripcionComecialApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDescripcionComecialApplication.class, args);
	}

}
