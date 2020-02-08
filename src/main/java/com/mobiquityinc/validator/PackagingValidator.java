package com.mobiquityinc.validator;

import java.io.File;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.config.Configs;
import com.mobiquityinc.config.IConfigs;
import com.mobiquityinc.exception.APIException;

public class PackagingValidator implements IPackagingValidator {
	
    private static IPackagingValidator instance;
	private IConfigs config;
	public static IPackagingValidator getInstance(){
		if(instance==null){
			instance = new PackagingValidator();
		}
		return instance;
	}
    private PackagingValidator(){};
    
    
	public  void validateFilePath(String absPath){
		File file = new File(absPath);
		if(!file.exists()){
			throw new APIException(config.getProprtyValue(Configs.FILE_NOT_FOUND, absPath));
		}
	}
	
	public  void validateRow(String s){
		   if( s==null || s.length()==0){
			   throw new APIException(config.getProprtyValue(Configs.EMPTY_ROW));
		   }
		   if(!s.contains(":") || !s.contains(" ") || !s.contains("(") || !s.contains(")") || !s.contains(",")){
			   throw new APIException(config.getProprtyValue(Configs.INVALID_ROW_FORMAT,s));
		   }
	}
	
	public  void validateItem(String part, Item item) {
		if(item.getWeight()>Configs.MAX_WEIGHT_OF_ITEM){
			  throw new APIException(config.getProprtyValue(Configs.EXCEED_WEIGHT_LIMIT,Configs.MAX_WEIGHT_OF_ITEM,part));
		  }
		  if(item.getCost()>Configs.MAX_COST_OF_ITEM){
			  throw new APIException(config.getProprtyValue(Configs.EXCEED_COST_LIMIT,Configs.MAX_COST_OF_ITEM,part));
		  }
	}
	
	public  void validateMaxWeight(String value, double maxWeight) {
		if(maxWeight>Configs.MAX_WEIGHT_OF_PACKAGE){
			  throw new APIException(config.getProprtyValue(Configs.MAX_WEIGHT_PACKAGE_LIMIT,Configs.MAX_WEIGHT_OF_PACKAGE,value));
		  }
	}
	public IConfigs getConfig() {
		return config;
	}
	
	public void setConfig(IConfigs config) {
		this.config = config;
	}
	
	
}
