package com.georef.api.provincias.service;

//import java.util.List;
//import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.tutorial.crud.entity.Producto;
//import com.tutorial.crud.georef.entity.Provincia;
//import com.tutorial.crud.georef.repository.ProvinciaRepository;
//import com.tutorial.crud.repository.ProductoRepository;

@Service
@Transactional
public class ProvinciaService {
	
	String nombre = ""; 
	
	public String  getByNombre(String nombre){
        return nombre;
    }

	/*
	@Autowired
    ProductoRepository productoRepository;

    public List<Producto> list(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getOne(int id){
        return productoRepository.findById(id);
    }

   // public Optional<Producto> getByNombre(String nombre){
     //   return productoRepository.findByNombre(nombre);
    //}
	    
*/
}
