package com.red.social.proyecto.app.service.impl;

import com.red.social.proyecto.app.service.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageFileServiceImpl implements StorageService {

    @Value("${media.location}")
    private String mediaLocation;

    private final Path rootLocation;


    public StorageFileServiceImpl(@Value("${media.location}") String mediaLocation) {
        this.mediaLocation = mediaLocation;
        this.rootLocation = Paths.get(mediaLocation);
    }

    @PostConstruct
    @Override
    public void init() {
        if (mediaLocation == null) {
            throw new RuntimeException("La propiedad media.location no se ha cargado correctamente.");
        }
    }


    @Override
    public String store(MultipartFile file) {


       try{
           if(file.isEmpty()){
               throw new RuntimeException("Error al guardar el archivo");
           }
           String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

           Path destinoFile= rootLocation.resolve(Paths.get(fileName))
                   .normalize()
                   .toAbsolutePath();
           try(InputStream inputStream= file.getInputStream()){
               Files.copy(inputStream,destinoFile, StandardCopyOption.REPLACE_EXISTING);
           }
           return fileName;
       }catch (IOException e){
           throw new RuntimeException("Error al guardar el archivo : ".concat(file.getOriginalFilename()));
       }



    }

    @Override
    public Resource loadResources(String filename) {
       try{
           Path file=rootLocation.resolve(filename);
           Resource resource=new UrlResource((file.toUri()));
           if (resource.exists()){
           return resource;
           }else{
               throw new RuntimeException("No se encuentra el archivo ".concat(filename));
           }
       }catch (MalformedURLException e){
           throw new RuntimeException("No de puede leer el archivo");
       }


    }

    @Override
    public void delete(String filename) {
        Path file = rootLocation.resolve(filename).normalize().toAbsolutePath();
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error al borrar el archivo " + filename, e);
        }
    }


}
