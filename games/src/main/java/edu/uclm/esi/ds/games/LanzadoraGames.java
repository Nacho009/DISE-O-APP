package edu.uclm.esi.ds.games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;

@SpringBootApplication
@ServletComponentScan
public class LanzadoraGames 
{
	
    public static void main( String[] args ) {
    	
        SpringApplication.run(LanzadoraGames.class,args);
        
    }

    @Bean
    public LoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }
}
