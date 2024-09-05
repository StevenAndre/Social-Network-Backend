package com.red.social.proyecto.app.controller;

import com.red.social.proyecto.app.dto.PasswordDto;
import com.red.social.proyecto.app.dto.UserUpdateDto;
import com.red.social.proyecto.app.dto.UsuarioDto;
import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.security.JwtResponse;
import com.red.social.proyecto.app.security.TokenService;
import com.red.social.proyecto.app.service.StorageService;
import com.red.social.proyecto.app.service.UsuarioService;
import com.red.social.proyecto.app.service.impl.CustomUserDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private CustomUserDetailService userDetailService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestPart("user") UsuarioDto usuarioDto, BindingResult result, @RequestPart (value = "image",required = false) MultipartFile file){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().body( result.getFieldErrors());
        }
        if(usuarioService.usernameUsed(usuarioDto.getUsername())){
            return new ResponseEntity<>("El username ya esta en uso intente con otro", HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.emailUsed(usuarioDto.getEmail())){
            return new ResponseEntity<>("El email ya esta registrado ingrese otro", HttpStatus.BAD_REQUEST);
        }
        if(file!=null && !file.isEmpty()){

                usuarioDto.setPhotoProfile(storageService.store(file));

        }
        return new ResponseEntity<>(usuarioService.saveUsuario(usuarioDto), HttpStatus.CREATED);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestPart("user") UserUpdateDto userDto, BindingResult result, @RequestPart (value = "image",required = false) MultipartFile file, @PathVariable Long id, Principal principal) {





        if(result.hasErrors()){
            return ResponseEntity.badRequest().body( result.getFieldError());
        }

        System.out.println("Usuario desde controlador==> "+userDto);
        Map<String, Object> respuesta= new HashMap<>();

        if(!id.equals(userDto.getId()) && usuarioService.getUserActual(principal.getName(),principal.getName()).get().getId().equals(id)){
            return new ResponseEntity<>("No tiene Permisos para acceder aestos recursos", HttpStatus.UNAUTHORIZED);
        }

        if(!usuarioService.updateUsernameCheck(userDto.getUsername(),userDto.getId())){

            return new ResponseEntity<>("El username ya esta en uso intente con otro", HttpStatus.BAD_REQUEST);
        }
        if(!usuarioService.updateEmailCheck(userDto.getEmail(),userDto.getId())){
            return new ResponseEntity<>("El email ya esta registrado ingrese otro", HttpStatus.BAD_REQUEST);
        }
        if(file!=null && !file.isEmpty()){
            storageService.delete(usuarioService.getUsuarioById(userDto.getId()).getPhotoProfile());
                userDto.setPhotoProfile(storageService.store(file));
        }


        respuesta.put("usuario",usuarioService.updateUsuario(userDto));
        String token= tokenService.generateToken(userDetailService.loadUserByUsername(userDto.getUsername()));
        respuesta.put("token",new JwtResponse(token));
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }


    @PostMapping("/password-confirm")
    public ResponseEntity<Boolean> comprobarPassword(Principal principal, @RequestBody PasswordDto contrasena){

        if (passwordEncoder.matches(contrasena.getPassword(), usuarioService.getUserActual(principal.getName(), principal.getName()).get().getPassword())) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }

    @GetMapping("/user-actual")
    public ResponseEntity<Usuario> obtenerUsuarioActual(Principal principal){
        System.out.println("Principall=== "+principal.getName());

        return ResponseEntity.ok(usuarioService.getUserActual(principal.getName(),principal.getName()).get());
    }

    @GetMapping("/profile/{filename:.+}")
    public ResponseEntity<Resource> getPhotoProfile(@PathVariable String filename) throws IOException {

        Resource file= storageService.loadResources(filename);
        String contentType= Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,contentType)
                .body(file);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDto>> getAll(){

        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }


}
