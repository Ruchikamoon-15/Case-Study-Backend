package com.cms.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cms.exception.AdmissionInvalidException;
import com.cms.model.Admission;
import com.cms.payment.Order;
import com.cms.payment.PaypalService;
import com.cms.service.AdmissionServiceImpl;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admission")
public class AdmissionController {

    @Autowired
    AdmissionServiceImpl admissionServiceImpl;

    private static final String CANCEL = "http://localhost:3000/cancel";
    private static final String SUCCESS_URL = "http://localhost:3000/success";

    @Autowired
    PaypalService paypalService;

    @PostMapping("/register/{associateId}/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public Admission registerAssociateForCourse(@RequestBody Admission admission)throws AdmissionInvalidException{
        return admissionServiceImpl.registerAssociateForCourse(admission);
    }

    @PutMapping("/calculateFees/{associateId}")
    @ResponseStatus(HttpStatus.OK)
	public int calculateFees(@PathVariable String associateId)throws AdmissionInvalidException{
        return admissionServiceImpl.calculateFees(associateId);
    }

    @PostMapping("/feedback/{regNo}/{feedback}")
    @ResponseStatus(HttpStatus.OK)
	public Admission addFeedback(@PathVariable Long regNo,@PathVariable String feedback) throws AdmissionInvalidException{
        return admissionServiceImpl.addFeedback(regNo, feedback);
    }

    // @PostMapping("/feedback/{regNo}/{feedback}/{feedbackRating}")
    // @ResponseStatus(HttpStatus.OK)
	// public Admission addFeedback(@PathVariable Long regNo,@PathVariable String feedback,@PathVariable float feedbackRating) throws AdmissionInvalidException{
    //     return admissionServiceImpl.addFeedback(regNo, feedback, feedbackRating);
    // }

    @GetMapping("/highestFee/{associateId}")
    @ResponseStatus(HttpStatus.OK)
	public List<String> highestFeeForTheRegisteredCourse(@PathVariable String associateId)throws AdmissionInvalidException{
        return admissionServiceImpl.highestFeeForTheRegisteredCourse(associateId);
    }

    @GetMapping("/viewFeedbackByCourseId/{courseId}")
    @ResponseStatus(HttpStatus.OK)
	public List<String> viewFeedbackByCourseId(@PathVariable String courseId) throws AdmissionInvalidException{
        return admissionServiceImpl.viewFeedbackByCourseId(courseId);
    }

    @DeleteMapping("/deactivate/{courseId}")
    @ResponseStatus(HttpStatus.OK)
	public boolean deactivateAdmission(@PathVariable String courseId)throws AdmissionInvalidException{
        return admissionServiceImpl.deactivateAdmission(courseId);
    }

    @PostMapping(value="/makePayment/{registartionId}",produces = "application/json")
    public String makePayment(@RequestBody Order order,@PathVariable long registartionId) throws IOException {
        try {
            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), CANCEL,
                    SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {

                    String redirectUrl = UriComponentsBuilder.fromUriString(link.getHref())
                            .build()
                            .toUriString();
                    return  redirectUrl;
                }
            }
            } 
            catch (PayPalRESTException e) {

            e.printStackTrace();
        }                                                        
        return "sign";
    }
        

    @GetMapping("/viewAll")
    @ResponseStatus(HttpStatus.OK)
	public List<Admission> viewAll(){
        return admissionServiceImpl.viewAll();
    }
    
	
	
}
