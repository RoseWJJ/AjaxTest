package com.et.dao;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyFoodDaoImpl {
	@Autowired 
	JdbcTemplate jdbcTemplate;
	public  List<Map<String,Object>>  queryFood(String foodnaem) {
		String sql="SELECT * FROM food WHERE foodname LIKE '%"+foodnaem+"%'";
		return jdbcTemplate.queryForList(sql);
		
	}
	public void deleteFood(String foodid) {
		String sql="delete  FROM food WHERE foodid = "+foodid;
		jdbcTemplate.execute(sql);
		
	}
	public void addFood(String foodname,String price) {
		String sql=" INSERT INTO food (foodname, price) VALUES ('"+foodname+"','"+price+"')";
		jdbcTemplate.execute(sql);
		
	}
	public void updateFood(String foodid,String foodname,String price) {
		String sql="UPDATE food SET foodname = '"+foodname+"' , price = '"+price+"' WHERE foodid = "+foodid;
		jdbcTemplate.execute(sql);
		
	}
}
