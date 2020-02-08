package com.mobiquityinc.validator;

import org.junit.Before;
import org.junit.Test;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.base.BaseTest;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.factory.ApplicationFactory;

public class PackagingValidatorTest extends BaseTest {
	
	@Before
	public void init(){
		ApplicationFactory.beanRegister();
	}
	
	@Test(expected = APIException.class)
	public void validateFilePathTest() {
         String path = "not a path";
         PackagingValidator.getInstance().validateFilePath(path);
	}
	@Test(expected = APIException.class)
	public void validateRowTest() {
		String s = "";
        PackagingValidator.getInstance().validateRow(s);
	}

	@Test(expected = APIException.class)
	public void validateRowTest2() {
		String s = "something";
        PackagingValidator.getInstance().validateRow(s);
	}
	@Test(expected = APIException.class)
	public void validateItemTest() {
		 Item item = new Item(1,140,30);
		 PackagingValidator.getInstance().validateItem("", item);
	}

	@Test(expected = APIException.class)
	public void validateItemTest2() {
		 Item item = new Item(1,30,300);
		 PackagingValidator.getInstance().validateItem("", item);
	}
	@Test(expected = APIException.class)
	public void validateMaxWeightTest() {
			double maxweight = 200;
			PackagingValidator.getInstance().validateMaxWeight("", maxweight);
	}
}
