package br.edu.utfpr.td.tsi.posto.saude;
 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportResource({ "file:./applicationContext.xml" })
public class Main {

	public static void main(String[] args) {
	    System.setProperty("server.servlet.context-path", "/posto-saude");
		SpringApplication.run(Main.class, args);
	} 
}



