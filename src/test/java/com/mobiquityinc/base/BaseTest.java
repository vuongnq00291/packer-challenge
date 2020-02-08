package com.mobiquityinc.base;
import org.junit.Before;

import com.mobiquityinc.factory.ApplicationFactory;

public class BaseTest {
	@Before
	public void init(){
		ApplicationFactory.beanRegister();
	}
}
