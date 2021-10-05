package com.example.demo.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SequenceEntity;
import com.example.demo.repository.SampleRepository;

@Component
public class TempService {
	
	@Autowired
	SampleRepository repository;
	
	String SEQUENCE_NAME_COLUMN = "my_seq";
	long INCREMENT_BY = 1;

	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String inner() {
		//update(123);
		SequenceEntity sequenceEntity = repository.select(SEQUENCE_NAME_COLUMN);
		if(sequenceEntity == null) {
			throw new NoSuchElementException();
		}
		long value = sequenceEntity.getNext_value();
		sequenceEntity.setNext_value(value + INCREMENT_BY);
		repository.update(sequenceEntity);
		return "";
		//throw new NoSuchElementException();
	}
}
