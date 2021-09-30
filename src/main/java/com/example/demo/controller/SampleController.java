package com.example.demo.controller;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.NoSuchElementException;

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
	
	@GetMapping("/clear")
    String clear() {
		SequenceEntity sequenceEntity = new SequenceEntity();
		sequenceEntity.setName("my_seq");
		sequenceEntity.setNext_value(1);
		service.update(sequenceEntity);
        return "Success";
    }
	
	@GetMapping("/test1")
    String test1() {
		try {
			return service.test1();
		} catch (SQLTimeoutException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/test2")
    String test2() {
		try {
			return service.test2();
		} catch (NoSuchElementException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/test3")
    String test3() {
		try {
			return service.test3();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/test4")
    String test4() {
		return service.test4();
    }
	
	@GetMapping("/test5")
    String test5() {
		return service.test5();
    }
	
	@GetMapping("/test6")
    String test6() {
		return service.test6();
    }
	
	@GetMapping("/test7")
    String test7() {
		return service.test7();
    }
	
	@GetMapping("/show")
    String show() {
		try {
			return service.show();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/show2")
    String show2() {
		try {
			return service.show2();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/show3")
    String show3() {
		try {
			return service.show3();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/set")
    String set() {
		try {
			return service.set();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/sync")
    String sync() {
		return service.sync();
    }
	
	@GetMapping("/outer")
    String outer() {
		return service.outer();
    }
}
