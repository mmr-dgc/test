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
	
	@GetMapping("/insert")
    String insert() {
		SequenceEntity sequenceEntity = new SequenceEntity();
		sequenceEntity.setName("temp");
		sequenceEntity.setNext_value(1);
		service.insert(sequenceEntity);
        return "Success";
    }
	
	@GetMapping("/insert2")
    String insert2() {
		SequenceEntity sequenceEntity = new SequenceEntity();
		sequenceEntity.setName("temp2");
		sequenceEntity.setNext_value(1);
		service.insert2(sequenceEntity);
        return "Success";
    }
	
	@GetMapping("/insert3")
    String insert3() {
		SequenceEntity sequenceEntity = new SequenceEntity();
		sequenceEntity.setName("temp2");
		sequenceEntity.setNext_value(1);
		service.insert3(sequenceEntity);
        return "Success";
    }
	
	@GetMapping("/insert4")
    String insert4() {
		SequenceEntity sequenceEntity = new SequenceEntity();
		sequenceEntity.setName("temp2");
		sequenceEntity.setNext_value(1);
		service.insert4(sequenceEntity);
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
			return service.spannerQUeryTimeout();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("SQLState : "+e.getSQLState());
			System.out.println("ErrorCode : "+e.getErrorCode());
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
	
	@GetMapping("/show4")
    String show4() {
		try {
			return service.show4();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/show5")
    String show5() {
		try {
			return service.show5();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/show6")
    String show6() {
		try {
			return service.show6();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/show7")
    String show7() {
		try {
			return service.show7();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/show8")
    String show8() {
		try {
			return service.show8();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return "success";
    }
	
	@GetMapping("/show9")
    String show9() {
		try {
			return service.show9();
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
