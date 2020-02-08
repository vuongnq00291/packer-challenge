package com.mobiquityinc.factory;

import java.util.HashMap;
import java.util.Map;

import com.mobiquityinc.config.Configs;
import com.mobiquityinc.config.IConfigs;
import com.mobiquityinc.repository.IPackagingRepository;
import com.mobiquityinc.repository.PackagingRepository;
import com.mobiquityinc.service.IPackagingService;
import com.mobiquityinc.service.PackagingService;
import com.mobiquityinc.validator.IPackagingValidator;
import com.mobiquityinc.validator.PackagingValidator;

public class ApplicationFactory {
	private static Map<String,Object> objects = new HashMap<String,Object>();
	
	
	@SuppressWarnings("unchecked")
	public static <K> K getObject(Class<K> clazz){
		return (K) objects.get(clazz.getCanonicalName());
	}
	
	public static <K> void ObjectRegister(Class<K> clazz,Object obj){
		objects.put(clazz.getCanonicalName(), obj);
	}
	
	static{
		beanRegister();
	}
	
    public static void beanRegister(){
		IConfigs config = Configs.getInstance();
		ObjectRegister(IConfigs.class,config);
		
		IPackagingValidator validator = PackagingValidator.getInstance();
		validator.setConfig(config);
		ObjectRegister(IPackagingValidator.class,validator);
		
		IPackagingRepository repo = PackagingRepository.getInstance();
		repo.setValidator(validator);
		ObjectRegister(IPackagingRepository.class,repo);
		
		IPackagingService packagingService = PackagingService.getInstance();
		packagingService.setRepository(repo);
		packagingService.setValidator(validator);
		ObjectRegister(IPackagingService.class,packagingService);
		
	}
}
