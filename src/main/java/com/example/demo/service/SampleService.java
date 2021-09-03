package com.example.demo.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.SequenceEntity;
import com.example.demo.repository.SampleRepository;

@Service
public class SampleService {

	@Autowired
	SampleRepository repository;
	
	String SEQUENCE_NAME_COLUMN = "my_seq";
	long INCREMENT_BY = 1;
	
	public SequenceEntity select(String sequenceName) {
		return repository.select(sequenceName);
	}
	
	public void update(SequenceEntity sequenceEntity) {
		repository.update(sequenceEntity);
    }
	
	@Transactional
	public String sync() {
		System.out.println(getNext());
		System.out.println(getNext());
		System.out.println(getNext());
		System.out.println(getNext());
		//throw new NoSuchElementException();
		return "Success";
	}
	
	@Transactional
	public String async() {
		return "Success";
	}
	
	public long getNext() {
		SequenceEntity sequenceEntity = repository.select(SEQUENCE_NAME_COLUMN);
		if(sequenceEntity == null) {
			throw new NoSuchElementException();
		}
		long value = sequenceEntity.getNext_value();
		sequenceEntity.setNext_value(value + INCREMENT_BY);
		repository.update(sequenceEntity);
		return value;
	}
	
	@Transactional
	public long getAndIncrementNextValueInDB() {
		SequenceEntity sequenceEntity = repository.select(SEQUENCE_NAME_COLUMN);
		if(sequenceEntity == null) {
			throw new NoSuchElementException();
		}
		long value = sequenceEntity.getNext_value();
		sequenceEntity.setNext_value(value + INCREMENT_BY);
		repository.update(sequenceEntity);
		return value;
	}
}
