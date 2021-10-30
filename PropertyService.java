package com.rit.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rit.demo.dao.PropertyMapper;
import com.rit.demo.pojo.Property;

@Service
public class PropertyService {
	
	@Autowired
	PropertyMapper mapper;

	public List<Property> queryAll(){
		return mapper.queryAll();
	}
}
