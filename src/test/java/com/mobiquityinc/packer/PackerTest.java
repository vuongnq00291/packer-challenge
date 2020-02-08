package com.mobiquityinc.packer;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.mobiquityinc.base.BaseTest;
import com.mobiquityinc.exception.APIException;
public class PackerTest extends BaseTest {

	@Test
	public void packTest() throws Exception {
		
		File file = new File("src/main/resources/sample.txt");
		String absPath = file.getCanonicalPath();
		String result = "4\n-\n2,7\n2\n1";
		Assert.assertEquals("", Packer.pack(absPath));
	}
	@Test
	public void packTest2() throws Exception {
		
		File file = new File("src/main/resources/sample2.txt");
		String absPath = file.getCanonicalPath();
		String result = "1\n4\n2,7\n2\n1";
		Assert.assertEquals(result, Packer.pack(absPath));
	}
	@Test
	public void packTest3() throws Exception {
		
		File file = new File("src/main/resources/sample3.txt");
		String absPath = file.getCanonicalPath();
		String result = "4\n1\n2,3,7";
		Assert.assertEquals(result, Packer.pack(absPath));
	}
	
	@Test
	public void packTest4() throws Exception {
		
		File file = new File("src/main/resources/sample4.txt");
		String absPath = file.getCanonicalPath();
		String result = "4\n1\n2,3,7\n2,7\n2,7\n2,3,4";
		Assert.assertEquals(result, Packer.pack(absPath));
	}
	
	@Test(expected = APIException.class)
	public void packTest5() throws Exception {
		File file = new File("src/main/resources/sample5.txt");
		String absPath = file.getCanonicalPath();
		String result = "4\n1\n2,3,7\n2,7\n2,7\n2,3,4";
		Assert.assertEquals(result, Packer.pack(absPath));
	}
	@Test(expected = APIException.class)
	public void packFailTest() throws Exception{
		File file = new File("src/main/resources/sample112.txt");
		String absPath = file.getCanonicalPath();
		String result = "4\n1\n2,3,7\n2,7\n2,7\n2,3,4";
		Assert.assertEquals(result, Packer.pack(absPath));;
	}
}
