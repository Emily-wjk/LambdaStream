package com.rit.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.rit.demo.pojo.Property;
import com.rit.demo.service.PropertyService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

@RestController
@RequestMapping("/propertyController/")
public class PropertyController {

	@Autowired
	PropertyService service;
	
	/**
	 * 距离我最近的店铺
	* @Title: queryName
	* @author Emily1_Zhang
	* @return: String
	* @throws
	 */
	@RequestMapping("queryName")
	public String queryName() {
		List<Property> list=service.queryAll();
		String name=list.stream()
				.sorted(Comparator.comparingInt(x -> x.getDistance()))
				.findFirst()
				.get().getName();
		return name;
	}
	/**
	 * 計算出銷量大於1000店鋪的個數
	* @Title: queryCount
	* @author Emily1_Zhang
	* @return: long
	* @throws
	 */
	@RequestMapping("queryCount")
	public long queryCount() {
		List<Property> list=service.queryAll();
		long count =list.stream()
				.filter(p -> p.getSales()>1000)
				.count();
		return count;
	}
	/**
	 * 筛选出距离我在 distance 米内的店铺
	* @Title: queryByDistance
	* @author Emily1_Zhang
	* @param: @param distance
	* @return: List<Property>
	* @throws
	 */
	@GetMapping("queryByDistance")
	@ResponseBody
	public List<Property> queryByDistance(Integer distance){
		List<Property> list=service.queryAll();
		List<Property> pros=list.stream()
				.filter(p -> p.getDistance() < distance)
				.collect(Collectors.toList());
		return pros;
	}
	
	/**
	 * 筛选出店鋪名稱長度大於nameLength的店铺
	* @Title: queryByName
	* @author Emily1_Zhang
	* @Description: TODO
	* @param: @param nameLength
	* @param: @return
	* @return: List<Property>
	* @throws
	 */
	@ResponseBody
	@GetMapping("queryByName")
	public List<Property> queryByName(Integer nameLength){
		List<Property> list=service.queryAll();
		List<Property> pros=list.stream()
				.filter(p -> p.getName().length() > nameLength)
				.collect(Collectors.toList());
		return pros;
	}
	
	/**
	 * 獲取店鋪名稱
	* @Title: queryNames
	* @author Emily1_Zhang
	* @Description: TODO
	* @param: @return
	* @return: List<String>
	* @throws
	 */
	@ResponseBody
	@RequestMapping("queryNames")
	public List<String> queryNames() {
		List<Property> list=service.queryAll();
		List<String> names=list.stream()
				.map(p -> p.getName())
				.collect(Collectors.toList());
		String name=list.stream()
				.map(p -> p.getName())
				.findFirst()
				.get();
		System.out.println(name);
		return names;
	}
	/**
	 * 獲取離我最近的兩個店鋪
	* @Title: queryNearPro
	* @author Emily1_Zhang
	* @Description: TODO
	* @param: @return
	* @return: List<Property>
	* @throws
	 */
	@RequestMapping("queryNearPro")
	@ResponseBody
	public List<Property> queryNearPro(){
		List<Property> list=service.queryAll();
		List<Property> pros =list.stream()
				.sorted(Comparator.comparingInt(x -> x.getDistance()))
				.limit(2)
				.collect(Collectors.toList());
		return pros;
	}
	
	/**
	 * 获取每个店铺的价格等级
	* @Title: queryLevel
	* @author Emily1_Zhang
	* @return: Map<String,Integer>
	* @throws
	 */
	@RequestMapping("queryLevel")
	@ResponseBody
	public Map<String, Integer> queryLevel(){
		List<Property> list=service.queryAll();
		Map<String, Integer> map=list.stream()
				.collect(Collectors.toMap(Property::getName, Property::getPriceLevel));
		return map;
	}
	/**
	 * 所有价格等级的店铺列表
	* @Title: queryByLevel
	* @author Emily1_Zhang
	* @return: Map<String,List<Property>>
	* @throws
	 */
	@ResponseBody
	@RequestMapping("queryGroupLevel")
	public Map<Integer, List<Property>> queryGroupLevel(){
		List<Property> list=service.queryAll();
		Map<Integer, List<Property>> map=list.stream()
				.collect(Collectors.groupingBy(Property::getPriceLevel));
		return map;
		
	}
	
	/**
	 * 获取长度大于2的单词个数
	* @Title: queryWordCount
	* @author Emily1_Zhang
	* @Description: TODO
	* @param: @return
	* @return: long
	* @throws
	 */
	@RequestMapping("queryWordCount")
	public long queryWordCount() {
		List<List<String>> lists = new ArrayList<>();
		lists.add(Arrays.asList("apple", "click"));
		lists.add(Arrays.asList("boss", "dig", "qq", "vivo"));
		lists.add(Arrays.asList("c#", "biezhi"));
		long count=lists.stream()
				.flatMap(Collection :: stream)
				.filter(str -> str.length() >2)
				.count();
		return count;
	}
	
	/**
	 * 筛选出价格最低的店铺
	* @Title: queryMin
	* @author Emily1_Zhang
	* @return: Property
	* @throws
	 */
	@RequestMapping("queryMin")
	public Property queryMin() {
		List<Property> list=service.queryAll();
		Property property=list.stream()
				.min(Comparator.comparingInt(p ->p.getDistance()))
				.get();
		return property;
	}
	
	@RequestMapping("test")
	@ResponseBody
	public List<String> test(){
		TimeInterval timer = DateUtil.timer();
		System.out.println(DateUtil.now());
		List<Property> list=service.queryAll();
		List<String> pros=list.stream()
				.filter(p -> p.getPriceLevel() < 4)
				.sorted(Comparator.comparingInt(Property::getDistance))
				.map(Property::getName)
				.limit(2)
				.collect(Collectors.toList());
		System.out.println(timer.interval());
		return pros;
	}
	
	@RequestMapping("test1")
	@ResponseBody
	public List<String> test1(){
		TimeInterval timer = DateUtil.timer();
		System.out.println(DateUtil.now());
		List<Property> list=service.queryAll();
		List<String> pros=list.parallelStream()
				.filter(p -> p.getPriceLevel() < 4)
				.sorted(Comparator.comparingInt(Property::getDistance))
				.map(Property::getName)
				.limit(2)
				.collect(Collectors.toList());
		System.out.println(timer.interval());
		return pros;
	}
}
