package com.red.social.proyecto.app.controller;


import com.red.social.proyecto.app.entity.Usuario;
import com.red.social.proyecto.app.security.JwtRequest;
import com.red.social.proyecto.app.security.JwtResponse;
import com.red.social.proyecto.app.security.TokenService;
import com.red.social.proyecto.app.service.UsuarioService;
import com.red.social.proyecto.app.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private UsuarioService userService;


    @PostMapping("/login-token")
    public ResponseEntity<?> login(@RequestBody JwtRequest loguinUser) throws Exception {
        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loguinUser.getUsername(),loguinUser.getPassword()));
        }catch (DisabledException disabledException){

            throw new Exception("Usuario Desabilidato"+disabledException.getMessage());

        }catch (BadCredentialsException badCredentialsException){
            return new ResponseEntity<>("Credenciales invalidas "+badCredentialsException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails=userDetailService.loadUserByUsername(loguinUser.getUsername());
        String token=tokenService.generateToken(userDetails);
        Usuario usuario=userService.getUserActual(loguinUser.getUsername(),loguinUser.getUsername()).get();
        userService.loginAndLogoutUser(usuario,true);
        return ResponseEntity.ok(new JwtResponse(token));

    }

    @PostMapping("/logout/{usuarioId}")
    public ResponseEntity<?> logout(@PathVariable Long usuarioId){
        Usuario usuario=userService.getUsuarioById(usuarioId);
        return  ResponseEntity.ok(userService.loginAndLogoutUser(usuario,false));
    }


}
