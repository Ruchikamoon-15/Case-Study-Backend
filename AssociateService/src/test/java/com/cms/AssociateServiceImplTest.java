package com.cms;
	
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cms.exception.AssociateInvalidException;
import com.cms.model.Associate;
import com.cms.repository.AssociateRepository;
import com.cms.service.AssociateServiceImpl;
import com.cms.service.SequenceGeneratorService;

	
//Write Unit Tests for the methods in the AssociateServiceImpl
	
public class AssociateServiceImplTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private AssociateServiceImpl associateService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	

	
	//check whether the addAssociate persists the associate object in the database
	@Test
	public void test118AddAssociate() throws AssociateInvalidException {
        // Prepare test data
        Associate cObj = new Associate();
        cObj.setAssociateId("123");
        cObj.setAssociateAddress("Sample Address");

        when(associateRepository.findByAssociateId(cObj.getAssociateId())).thenReturn(null);
        when(sequenceGeneratorService.getNextAssociateId()).thenReturn("456");
        when(associateRepository.save(cObj)).thenReturn(cObj);

        // Test the method
        Associate addedAssociate = associateService.addAssociate(cObj);

        // Assert the result
        assertNotNull(addedAssociate);
        assertEquals("456", addedAssociate.getAssociateId());
    }
	
	
	
	//check whether the viewByAssociateId returns the associate for the given associate Id
	@Test
	public void test119ViewByAssociateId() throws AssociateInvalidException {
        // Prepare test data
        String associateId = "123";
        Associate cObj = new Associate();
        cObj.setAssociateId(associateId);
        cObj.setAssociateAddress("Sample Address");

        when(associateRepository.findByAssociateId(associateId)).thenReturn(cObj);

        // Test the method
        Associate retrievedAssociate = associateService.viewByAssociateId(associateId);

        // Assert the result
        assertNotNull(retrievedAssociate);
        assertEquals(associateId, retrievedAssociate.getAssociateId());
    }

	
	
	
	//check whether updateAssociate updates the address of the given assciateId in the database
	@Test
	public void test120updateAssociate() throws AssociateInvalidException {
        // Prepare test data
        String associateId = "123";
        Associate cObj = new Associate();
        cObj.setAssociateId(associateId);
        cObj.setAssociateAddress("Sample Address");

        when(associateRepository.findByAssociateId(associateId)).thenReturn(cObj);
        when(associateRepository.save(cObj)).thenReturn(cObj);

        // Test the method
        Associate updatedAssociate = associateService.updateAssociate(associateId, "New Address");

        // Assert the result
        assertNotNull(updatedAssociate);
        assertEquals("New Address", updatedAssociate.getAssociateAddress());
    }
	
	
	
	//check whether viewByAssociateId throws AssociateInvalidException for invalid associateId
	@Test(expected = AssociateInvalidException.class)
	public void test121ViewByAssociateIdForInvalidId() throws AssociateInvalidException {
        // Prepare test data
        String invalidAssociateId = "789";

        when(associateRepository.findByAssociateId(invalidAssociateId)).thenReturn(null);

        // Test the method, should throw AssociateInvalidException
        associateService.viewByAssociateId(invalidAssociateId);
    }

	
	//check whether updateAssociate updates the address of the given assciateId in the database for invalid id
	@Test(expected = AssociateInvalidException.class)
	public void test120UpdateassociateForInvalidId() throws AssociateInvalidException {
        // Prepare test data
        String invalidAssociateId = "789";

        when(associateRepository.findByAssociateId(invalidAssociateId)).thenReturn(null);

        // Test the method, should throw AssociateInvalidException
        associateService.updateAssociate(invalidAssociateId, "New Address");
    }

}