package com.study.ms.limitservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.ms.limitservices.beans.LimitConfiguation;

@RestController
public class LimitServiceController {
	
	@Autowired
	private LimitConfiguation configuration;
	
	@GetMapping("/limits")
	public LimitConfiguation getLimits() {
		return configuration;
		
	}

}
