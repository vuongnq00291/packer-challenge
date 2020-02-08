package com.mobiquityinc.validator;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.config.IConfigs;

public interface IPackagingValidator {

	void validateFilePath(String absPath);

	void validateRow(String s);

	void validateItem(String part, Item item);

	void validateMaxWeight(String value, double maxWeight);

	void setConfig(IConfigs config);

}