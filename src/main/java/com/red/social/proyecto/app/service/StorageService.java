package com.red.social.proyecto.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();
    String store(MultipartFile file);

    Resource loadResources(String filename);
    void delete(String filename);
}
