package com.serratec.techbank1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.serratec.techbank1.controller.AboutUsConfig;

@SpringBootApplication
public class TechBank1Application {

	public static void main(String[] args) {
		ApplicationContext aptCont = SpringApplication.run(TechBank1Application.class, args);
		AboutUsConfig about = aptCont.getBean(AboutUsConfig.class);
		about.bemVindoAoSistema();
	}

}
