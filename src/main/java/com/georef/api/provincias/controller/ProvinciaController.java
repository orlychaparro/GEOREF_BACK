package com.georef.api.provincias.controller;




import com.georef.api.provincias.service.ProvinciaService;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping(value="/georef", method=RequestMethod.GET)

@CrossOrigin(origins = "http://localhost:4200")

public class ProvinciaController {
	
	@Autowired
    ProvinciaService provinciaService;
	
    @PostMapping("/provincia/{nombre}")
    
		  public ResponseEntity<Object> listref(@PathVariable String nombre){
		  
		    	  RestTemplate restTemplate = new RestTemplate(); 
		    	  String Url = "https://apis.datos.gob.ar/georef/api/provincias?nombre=" + nombre ;
		    	  
		    	  
		    	 // List<Producto> list = provinciaService.list();
		    	  
				  ResponseEntity<String> response = restTemplate.getForEntity(Url ,String.class); 
				 
				  System.out.println(response.getBody().toString());
				  System.out.println();
		    return new ResponseEntity(response.getBody(), HttpStatus.OK);
		    	   
    }
    
   
	
	
	  public RestTemplate restTemplate() { return new RestTemplate(); }
	  
	/*
	 * @EnableWebSecurity
	 * 
	 * public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .authorizeRequests() .anyRequest().authenticated() .and() .httpBasic(); }
	 * 
	 * }
	 */
	 
}
