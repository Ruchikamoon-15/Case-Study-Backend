package com.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.exception.AssociateInvalidException;
import com.cms.model.Associate;
import com.cms.repository.AssociateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AssociateServiceImpl implements IAssociateService{

	@Autowired
	AssociateRepository associateRepository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
		
	public Associate addAssociate(Associate cObj) throws AssociateInvalidException {
		try {
			Associate exisitingAssociate = associateRepository.findByAssociateId(cObj.getAssociateId());
			if (exisitingAssociate != null) {
				throw new AssociateInvalidException("AssociateId already Exist");
			}
			String associateId = sequenceGeneratorService.getNextAssociateId();
			cObj.setAssociateId(associateId);
			Associate addedAssociate = associateRepository.save(cObj);
			return addedAssociate;
		} catch (Exception e) {
			throw e;
		}

	}


	
	public Associate viewByAssociateId(String associateId) throws AssociateInvalidException {
		try {
			// Check if the given associate id exists
			Associate associate = associateRepository.findByAssociateId(associateId);
			if (associate == null) {
				throw new AssociateInvalidException("Invalid AssociateId");
			}
			return associate;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Associate updateAssociate(String associateId, String associateAddress) throws AssociateInvalidException {
		try {
			Associate exisitingAssociate = associateRepository.findByAssociateId(associateId);
			if (exisitingAssociate == null) {
				throw new AssociateInvalidException("associateId does not exist");
			}
			exisitingAssociate.setAssociateAddress(associateAddress);
			Associate updateAssociate = associateRepository.save(exisitingAssociate);
			log.info("This method updateAssociate has completed successfully");
			return updateAssociate;
		} catch (Exception e) {
			log.error("Error in updateAssociate: {}", e.getMessage());
			throw e;
		}
	}

	
	
	public List<Associate> viewAll() {
		try {
            return associateRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
	}

}