package com.cms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import com.cms.controller.AssociateController;
import com.cms.exception.AssociateInvalidException;
import com.cms.model.Associate;
import com.cms.service.AssociateServiceImpl;

//Write mockito tests for the endpoints in the AssociateController

public class AssociateControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Mock
    private AssociateServiceImpl associateService;

    @InjectMocks
    private AssociateController associateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	//Test whether the endpoint /viewByAssociateId/{associateId} is successful
	@Test
    public void test115RestApiCallForViewByAssociateId() throws AssociateInvalidException {
		// Arrange
		String associateId = "123";
		Associate expectedAssociate = new Associate(associateId, "John Doe", "123 Main St", "john.doe@example.com");

		when(associateService.viewByAssociateId(associateId)).thenReturn(expectedAssociate);

		// Act
		Associate result = associateController.viewByAssociateId(associateId);

		// Assert
		assertEquals(expectedAssociate, result);
		verify(associateService, times(1)).viewByAssociateId(associateId);
		
	}
	
	//Test whether the end point /updateAssociate/{associateId}/{associateAddress} is successful
	@Test
    public void test116RestApiCallForUpdateAssociate() throws AssociateInvalidException {
		// Arrange
		String associateId = "123";
		String updatedAddress = "456 New St";
		Associate updatedAssociate = new Associate(associateId, "John Doe", updatedAddress, "john.doe@example.com");

		when(associateService.updateAssociate(associateId, updatedAddress)).thenReturn(updatedAssociate);

		// Act
		Associate result = associateController.updateAssociate(associateId, updatedAddress);

		// Assert
		assertEquals(updatedAssociate, result);
		verify(associateService, times(1)).updateAssociate(associateId, updatedAddress);

	}
	
	//Test whether the endpoint /addAssociate is successful
	@Test
    public void test117RestApiCallForAddAssociate() throws Exception {
		// Arrange
		Associate associateToAdd = new Associate("123", "John Doe", "123 Main St", "john.doe@example.com");

		when(associateService.addAssociate(associateToAdd)).thenReturn(associateToAdd);

		// Act
		Associate result = associateController.addAssociate(associateToAdd);

		// Assert
		assertEquals(associateToAdd, result);
		verify(associateService, times(1)).addAssociate(associateToAdd);

	}
	
	//Test whether the endpoint /viewByAssociateId/{associateId} is successful for invalid token
	@Test
    public void test115RestApiCallForViewByAssociateIdForInvalidToken() throws AssociateInvalidException {
		// Arrange
    String associateId = "123";

    when(associateService.viewByAssociateId(associateId)).thenThrow(AssociateInvalidException.class);

    // Act and Assert
    assertThrows(AssociateInvalidException.class, () -> associateController.viewByAssociateId(associateId));
    verify(associateService, times(1)).viewByAssociateId(associateId);
	}
	
	//Test whether the endpoint /viewByAssociateId/{associateId} is successful for invalid id
	@Test
    public void test115RestApiCallForViewByAssociateIdForInvalidId() throws AssociateInvalidException {
		// Arrange
		String invalidAssociateId = "invalid";
		AssociateInvalidException exception = new AssociateInvalidException("Invalid Associate ID");
		when(associateService.viewByAssociateId(invalidAssociateId)).thenThrow(exception);

		// Act
		try {
			Associate result = associateController.viewByAssociateId(invalidAssociateId);
		} catch (AssociateInvalidException e) {
			// Assert
			assertEquals("Invalid Associate ID", e.getMessage());
			verify(associateService, times(1)).viewByAssociateId(invalidAssociateId);
		}
	}
	
	//Test whether the end point /updateAssociate/{associateId}/{associateAddress} is successful for invalid token
	@Test
    public void test116RestApiCallForUpdateAssociateForInvalidToken() throws AssociateInvalidException {
		// Arrange
        String associateId = "123";
        String updatedAddress = "456 New St";

        when(associateService.updateAssociate(associateId, updatedAddress)).thenThrow(AssociateInvalidException.class);

        // Act & Assert
        assertThrows(AssociateInvalidException.class, () -> associateController.updateAssociate(associateId, updatedAddress));

        verify(associateService, times(1)).updateAssociate(associateId, updatedAddress);
 
	}
	
	//Test whether the end point /updateAssociate/{associateId}/{associateAddress} is successful for invalid id
	@Test
    public void test116RestApiCallForUpdateAssociateForInvalidId() throws AssociateInvalidException {
		// Arrange
        String associateId = "invalid";
        String updatedAddress = "456 New St";

        when(associateService.updateAssociate(associateId, updatedAddress)).thenThrow(AssociateInvalidException.class);

        // Act & Assert
        assertThrows(AssociateInvalidException.class, () -> associateController.updateAssociate(associateId, updatedAddress));

        verify(associateService, times(1)).updateAssociate(associateId, updatedAddress);

	}
	
	@Test
    public void test117RestApiCallForAddAssociateForInvalidToken() throws AssociateInvalidException {
		// Arrange
        Associate associateToAdd = new Associate("123", "John Doe", "123 Main St", "john.doe@example.com");

        when(associateService.addAssociate(associateToAdd)).thenThrow(AssociateInvalidException.class);

        // Act & Assert
        assertThrows(AssociateInvalidException.class, () -> associateController.addAssociate(associateToAdd));

        verify(associateService, times(1)).addAssociate(associateToAdd);
 
	}

	

}