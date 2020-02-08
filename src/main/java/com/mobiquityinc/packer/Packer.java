package com.mobiquityinc.packer;

import com.mobiquityinc.factory.ApplicationFactory;
import com.mobiquityinc.service.IPackagingService;
/**
 * 
 */
public class Packer {
	
	
	/**
	 * 
	 * @param absPath
	 *            {@link String} &nbsp; [ path to data file.
	 *                                   contain data to be parsed]
	 * @return {@link String} &nbsp; [the output]
	 */
	public static String pack(String absPath) throws Exception {
		IPackagingService packagingService = ApplicationFactory.getObject(IPackagingService.class);
		return packagingService.pack(absPath);
	}
}


