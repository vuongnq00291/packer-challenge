package com.mobiquityinc.repository;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mobiquityinc.Entity.Package;
import com.mobiquityinc.base.BaseTest;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.factory.ApplicationFactory;
import com.mobiquityinc.repository.PackagingRepository;

public class PackagingRepositoryTest extends BaseTest{
	@Before
	public void init(){
		ApplicationFactory.beanRegister();
	}
	@Test
	public void loadTest() throws Exception{
		File file = new File("src/main/resources/sample.txt");
		String absPath = file.getCanonicalPath();
		Package record = PackagingRepository.getInstance().load(absPath).get(0);
		Assert.assertEquals(record.getMaxWeight(), 81,0);
		Assert.assertEquals(record.getItems().size(),6);
	}
	
	@Test
	public void extractTest() throws Exception{
		String value = "56 : (1,90.72,$13) (2,33.80,$40) (3,43.15,$10) (4,37.97,$16)";
		Package record = PackagingRepository.getInstance().parse(value);
		Assert.assertEquals(record.getMaxWeight(), 56,0);
		Assert.assertEquals(record.getItems().size(),4);
		
	}
	
	@Test(expected = APIException.class)
	public void extractFailTest() throws Exception{
		String value = "56  (1,90.72,$13) (2,33.80,$40) (3,43.15,$10) (4,37.97,$16)";
		Package record = PackagingRepository.getInstance().parse(value);
		Assert.assertEquals(record.getMaxWeight(), 56,0);
		Assert.assertEquals(record.getItems().size(),4);
		
	}
	

}
