package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

import com.cms.exception.AssociateInvalidException;
import com.cms.model.Associate;
import com.cms.service.AssociateServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController 
@RequestMapping("/associate")
public class AssociateController {

    @Autowired
    AssociateServiceImpl associateServiceImpl;

    RestTemplate restTemplate;

    @PostMapping("/addAssociate")
    @ResponseStatus(HttpStatus.OK)
    public Associate addAssociate(@RequestBody Associate cObj)throws AssociateInvalidException{
        return associateServiceImpl.addAssociate(cObj);
    }

    @GetMapping("/viewByAssociateId/{associateId}")
    @ResponseStatus(HttpStatus.OK)
	public Associate viewByAssociateId(@PathVariable String associateId) throws AssociateInvalidException{
        return associateServiceImpl.viewByAssociateId(associateId);
    }
    
    @PutMapping("/updateAssociate/{associateId}/{associateAddress}")
    @ResponseStatus(HttpStatus.OK)
	public Associate updateAssociate(@PathVariable String associateId,@PathVariable String associateAddress)throws AssociateInvalidException{
        return associateServiceImpl.updateAssociate(associateId, associateAddress);
    } 
    
    @GetMapping("/viewAll")
    @ResponseStatus(HttpStatus.OK)
	public List<Associate> viewAll(){
        return associateServiceImpl.viewAll();
    }
    
}





