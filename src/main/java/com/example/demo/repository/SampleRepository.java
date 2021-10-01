package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SequenceEntity;
import com.example.demo.mapper.SampleMapper;

@Repository
public class SampleRepository {
	
	@Autowired
	private SampleMapper mapper;
    
    public SequenceEntity select(String sequenceName) {
    	return mapper.select(sequenceName);
    }
    
    public void update(SequenceEntity sequenceEntity) {
    	mapper.update(sequenceEntity);
    }
    
    public void insert(SequenceEntity sequenceEntity) {
    	mapper.insert(sequenceEntity);
    }

}
