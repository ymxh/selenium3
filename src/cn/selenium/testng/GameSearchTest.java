package cn.selenium.testng;

import org.testng.annotations.Test;

import cn.selenium.page.HomePage;
import cn.selenium.util.XmlReader;
import org.testng.annotations.DataProvider;
import org.testng.Assert;


public class GameSearchTest extends TestCaseBeforeAfterMethod{
	
	@DataProvider(name="testProvider",parallel=true)
	public Object[][] testProvider(){
		return XmlReader.readerCase("testcase.xml","GameSearch");
	}
	
	
	@Test(dataProvider="testProvider")
	public void f(String param,String exp) {
		System.out.println(Thread.currentThread().getId());
		Assert.assertEquals(new HomePage().openPageRequestUrl().gameListLink().gameSearch(param),exp);	
	}
}
