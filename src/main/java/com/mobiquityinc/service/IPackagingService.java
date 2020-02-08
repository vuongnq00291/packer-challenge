package com.mobiquityinc.service;

import java.util.List;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.repository.IPackagingRepository;
import com.mobiquityinc.validator.IPackagingValidator;

public interface IPackagingService {

	String pack(String absPath) throws Exception;

	void setRepository(IPackagingRepository repository);
	
	List<List<Item>> createPossibleGrouping(List<Item> items);
	
	List<Item> selectOptPackage(List<List<Item>> groups,double limit);

	void setValidator(IPackagingValidator validator);

}