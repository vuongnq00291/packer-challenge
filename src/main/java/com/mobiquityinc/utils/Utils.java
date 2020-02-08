package com.mobiquityinc.utils;

import java.util.List;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.exception.APIException;

public class Utils {
	
	public static int convertToInt(String s){
		try{
		int val =Integer.parseInt(s);
		return val;
		}catch(Exception e){
			throw new APIException("Value is not a number | " + s ); 
		}
	}
	public static double convertToDouble(String s){
		try{
		double val =Double.parseDouble(s);
		return val;
		}catch(Exception e){
			throw new APIException("Value is not a number | " + s ); 
		}
	}
	
	public static void output(List<Item> items){
		if(items ==null) return;
		for(Item item:items){
			System.out.print(item.getId()+ " ");
		}
		System.out.println();
	}
}
