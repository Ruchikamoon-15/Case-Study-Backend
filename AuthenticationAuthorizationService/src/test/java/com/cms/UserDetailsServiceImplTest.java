package com.cms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cms.model.Login;
import com.cms.repository.UserRepository;
import com.cms.service.UserDetailsServiceImpl;

public class UserDetailsServiceImplTest {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	//Test whether loadByUserName returns UserDetails for valid credentials
	@Test
	public void test118LoadByUserName() {
		String username = "john_doe";
        String password = "password";
        String role = "ROLE_USER";

        Login user = new Login();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        when(userRepository.findByUsername(username)).thenReturn(user);

        // Test the method
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert the result
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertEquals(role, userDetails.getAuthorities().iterator().next().getAuthority());
    }
		
	
	
	//Test whether loadByUserName throws Exception for invalid user
	@Test(expected = UsernameNotFoundException.class)
	public void test118LoadByUserNameForInvalidUser() {
		 // Prepare test data
		 String invalidUsername = "invalid_user";

		 when(userRepository.findByUsername(invalidUsername)).thenReturn(null);
 
		 // Test the method, should throw UsernameNotFoundException
		 userDetailsService.loadUserByUsername(invalidUsername);
	 }
			
	}

