package sistema.crud.producto.security.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.crud.producto.dto.Mensaje;
import sistema.crud.producto.security.dto.JwtDto;
import sistema.crud.producto.security.dto.LoginUsuario;
import sistema.crud.producto.security.dto.NuevoUsuario;
import sistema.crud.producto.security.entity.Rol;
import sistema.crud.producto.security.entity.Usuario;
import sistema.crud.producto.security.enums.RolNombre;
import sistema.crud.producto.security.jwt.JwtProvider;
import sistema.crud.producto.security.service.RolService;
import sistema.crud.producto.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthController {
	
	    @Autowired
	    PasswordEncoder passwordEncoder;

	    //@Autowired
	    //AuthenticationManager authenticationManager;

	    @Autowired
	    UsuarioService usuarioService;

	    @Autowired
	    RolService rolService;

	    //@Autowired
	    //JwtProvider jwtProvider;
	    
	    
	    private final AuthenticationManager authenticationManager;
	    private final JwtProvider jwtProvider;

	    @Autowired
	    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
	        this.authenticationManager = authenticationManager;
	        this.jwtProvider = jwtProvider;
	    }
	    
	    
	    
	    

	    @PostMapping("/nuevo")
	    public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
	        if(bindingResult.hasErrors())
	            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
	        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
	            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
	        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
	            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
	        Usuario usuario =
	                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
	                        passwordEncoder.encode(nuevoUsuario.getPassword()));
	        Set<Rol> roles = new HashSet<>();
	        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
	        if(nuevoUsuario.getRoles().contains("admin"))
	            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
	        usuario.setRoles(roles);
	        usuarioService.save(usuario);
	        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
	        if(bindingResult.hasErrors())
	            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
	        Authentication authentication =
	                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtProvider.generateToken(authentication);
	        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
	        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	        return new ResponseEntity(jwtDto, HttpStatus.OK);
	    }
	

}
