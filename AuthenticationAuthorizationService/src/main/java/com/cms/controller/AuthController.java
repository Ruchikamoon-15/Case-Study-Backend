package com.cms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cms.jwt.JwtUtility;
import com.cms.request.LoginRequest;
import com.cms.response.JSONResponse;
import com.cms.service.UserDetailsImpl;
import com.cms.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {
    
    @Autowired
    JwtUtility jwtTokenUtil;
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;
    

    
    @PostMapping("/app/signin")
    public ResponseEntity<?> validateUser(@RequestBody LoginRequest loginRequest){  
       
      

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenUtil.generateToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JSONResponse(jwt, 
												 userDetails.getUsername(), 
												 roles));
	
    }
    

    @GetMapping("/app/validateToken/{authorization}")
    public boolean isValidToken(@PathVariable String authorization) {
        String token=authorization.substring(7);
        if(jwtTokenUtil.validateToken(token)) {
            return true;
        }
        else {
            return false;
        }
    
    
}

}