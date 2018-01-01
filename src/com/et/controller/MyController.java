package com.et.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.et.dao.MyFoodDaoImpl;




@Controller
public class MyController{
	@Autowired 
	MyFoodDaoImpl myFoodDaoImpl;
	@RequestMapping(value="/showFood",method={RequestMethod.GET})
	public String queryFood(String foodname,OutputStream os) throws Exception{
		if(foodname==null){
			foodname="";
		}
		System.out.println(foodname);
		//将数据存到List集合中
		List<Map<String, Object>> list =myFoodDaoImpl.queryFood(foodname);
		//将数据存到json中
		JSONArray array =  JSONArray.fromObject(list);
		//由于javascript 只接受字符串 所以将json数据转换成字符串，然后输出到页面；
		String jsonString = array.toString();
		
		os.write(jsonString.getBytes("UTF-8"));
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/showFood1",method={RequestMethod.GET})
	public byte[] queryFood1(String foodname) throws Exception{
		if(foodname==null){
			foodname="";
		}
		//将数据存到List集合中
		List<Map<String, Object>> list =myFoodDaoImpl.queryFood(foodname);
		//将数据存到json中
		JSONArray array =  JSONArray.fromObject(list);
		//由于javascript 只接受字符串 所以将json数据转换成字符串，然后输出到页面；
		String jsonString = array.toString();
		
		return jsonString.getBytes("UTF-8");
	}
	
	@ResponseBody
	@RequestMapping(value="/showFoodList",method={RequestMethod.GET})
	public List<Map<String, Object>> queryFood3(String foodname) throws Exception{
		if(foodname==null){
			foodname="";
		}
		List<Map<String, Object>> list =myFoodDaoImpl.queryFood(foodname);
		return list;
	}
	
	@RequestMapping(value="/deleteFood/{foodid}",method={RequestMethod.DELETE})
	public String deleteFood(@PathVariable String foodid,OutputStream os) throws Exception {
		try {
			System.out.println(foodid);
			myFoodDaoImpl.deleteFood(foodid);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
	
	@RequestMapping(value="/saveFood",method={RequestMethod.POST})
	public String saveFood(String foodname,String price,OutputStream os) throws Exception {
		

		try {
			myFoodDaoImpl.addFood(foodname, price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			os.write("0".getBytes("UTF-8"));
		}
		
		return null;
	}
	
	@RequestMapping(value="/updateFood/{foodid}",method={RequestMethod.PUT})
	public String updateFood(@PathVariable String foodid,String foodname,String price,OutputStream os) throws Exception{
		try {
			System.out.println(foodid);
			myFoodDaoImpl.updateFood(foodid, foodname, price);
			os.write("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			os.write("0".getBytes("UTF-8"));
		}
		return null;
	}
}
