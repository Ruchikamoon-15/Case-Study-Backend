package com.cms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cms.model.EmailRequest;
import com.cms.service.EmailService;

@RestController

public class EmailController {

 

    @Autowired

    EmailService emailService;

 

    @PostMapping("/sendEmail")

    public void sendEmail(@RequestBody EmailRequest emailRequest) {

        emailService.sendEmail(emailRequest.getToAddress(), emailRequest.getSubject(), emailRequest.getBody());

    }

}