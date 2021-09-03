package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SequenceEntity;
import com.example.demo.service.SampleService;

@RestController
public class SampleController {

	@Autowired
	SampleService service;
	
	@GetMapping("/select")
    SequenceEntity select() {
        return service.select("my_seq");
    }
	
	@GetMapping("/update")
    String update() {
		SequenceEntity sequenceEntity = new SequenceEntity();
		sequenceEntity.setName("my_seq");
		sequenceEntity.setNext_value(20);
		service.update(sequenceEntity);
        return "Success";
    }
	
	@GetMapping("/sync")
    String sync() {
		return service.sync();
    }
}
