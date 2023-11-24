package com.cms.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "admissionservice",url="http://localhost:9093")
public interface AdmissionProxy {
	
	@DeleteMapping("/admission/deactivate/{courseId}")
    public boolean deactivateAdmission(@RequestParam("courseId") String courseId);

}