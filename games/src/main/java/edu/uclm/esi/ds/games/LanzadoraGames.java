package edu.uclm.esi.ds.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = "edu.uclm.esi.ds.games")
public class LanzadoraGames 
{
	
    public static void main( String[] args ) {
    	
        SpringApplication.run(LanzadoraGames.class,args);
        
    }


}
