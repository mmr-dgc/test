package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.example.demo.entity.SequenceEntity;

@Mapper
@Component
public interface SampleMapper {
	
	@Select("SELECT name, next_value FROM sequences WHERE name = #{sequenceName}")
    SequenceEntity select(String sequenceName);
	
	@Update("Update sequences SET next_value = #{next_value} WHERE name = #{name}")
    void update(SequenceEntity sequenceEntity);
	
	@Insert("Insert into sequences (name, next_value) values (#{name}, #{next_value})")
    void insert(SequenceEntity sequenceEntity);

}
