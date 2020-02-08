package com.mobiquityinc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mobiquityinc.Entity.Item;
import com.mobiquityinc.Entity.Package;
import com.mobiquityinc.base.BaseTest;
import com.mobiquityinc.factory.ApplicationFactory;

public class PackagingServiceTest extends BaseTest {
	
	@Before
	public void init(){
		ApplicationFactory.beanRegister();
	}
	
	@Test
	public void packTest() throws Exception{
		
		File file = new File("src/main/resources/sample.txt");

		String absPath = file.getCanonicalPath();

		String result = "4\n-\n2,7\n2\n1";
		
		
		Assert.assertEquals(result, PackagingService.getInstance().pack(absPath));
		

		
	}
	
	@Test
	public void findTest(){
		Package record = new Package();
		record.setMaxWeight(90);
		record.setItems(new ArrayList<Item>());
		record.getItems().add(new Item(1, 30, 40));
		record.getItems().add(new Item(2, 80, 77));
		record.getItems().add(new Item(3, 70, 50));
		record.getItems().add(new Item(4, 12, 40));
		record.getItems().add(new Item(5, 30, 40));
		Assert.assertEquals("1,3", PackagingService.getInstance().find(record));
	}
	@Test
	public void createPossibleGroupingTest(){
		Package record = new Package();
		record.setMaxWeight(90);
		record.setItems(new ArrayList<Item>());
		record.getItems().add(new Item(1, 30, 40));
		record.getItems().add(new Item(2, 80, 77));
		record.getItems().add(new Item(3, 70, 50));
		record.getItems().add(new Item(4, 12, 40));
		record.getItems().add(new Item(5, 30, 40));
		List<List<Item>> groups = PackagingService.getInstance().createPossibleGrouping(record.getItems());
		int numofgroup = (int) Math.pow(2,record.getItems().size())-1;
		Assert.assertEquals(numofgroup, groups.size());
	}
	@Test
	public void selectOptPackageTest(){
		Package record = new Package();
		record.setMaxWeight(90);
		record.setItems(new ArrayList<Item>());
		record.getItems().add(new Item(1, 30, 40));
		record.getItems().add(new Item(2, 80, 77));
		record.getItems().add(new Item(3, 70, 50));
		record.getItems().add(new Item(4, 12, 40));
		record.getItems().add(new Item(5, 30, 40));
		List<List<Item>> groups = PackagingService.getInstance().createPossibleGrouping(record.getItems());
		List<Item> selectedItems = PackagingService.getInstance().selectOptPackage(groups, 90);
		Assert.assertEquals(1,selectedItems.get(0).getId());
		Assert.assertEquals(3,selectedItems.get(1).getId());
	}
}
