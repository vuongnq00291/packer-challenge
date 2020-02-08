package com.mobiquityinc.repository;

import java.util.List;

import com.mobiquityinc.Entity.Package;
import com.mobiquityinc.validator.IPackagingValidator;

public interface IPackagingRepository {

	List<Package> load(String absPath) throws Exception;

	/**
	 * 
	 * @param line
	 *            {@link String} &nbsp;[Single Package to parse]
	 * @return {@link Package} &nbsp; [parsed package into items and total
	 *         weight]
	 * @throws Exception
	 */
	Package parse(String line) throws Exception;

	void setValidator(IPackagingValidator validator);

}