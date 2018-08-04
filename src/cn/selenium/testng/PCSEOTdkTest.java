package cn.selenium.testng;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import cn.selenium.method.AssertMethod;
import cn.selenium.method.BrowserMethod;
import cn.selenium.util.ListUtil;
import cn.selenium.util.XmlReader;

public class PCSEOTdkTest extends TestCaseBeforeAfterMethod{
	
	private static final String hostUrl = "http://www.8868test.lo";
	
	@DataProvider(name="tdkProvider",parallel=true)
	public Object[][] tdkProvider(){
		return XmlReader.readerCase("casedata/pc_seo_tdk.xml","tdk");
	}

	/**
	 * 检查页面tdk内容
	 * @param url
	 * @param title
	 */
	@Test(dataProvider="tdkProvider")
	public void checkTDK(String url,String exp) {
		List<String> list = ListUtil.returnList(exp, "owin");
		BrowserMethod.open(hostUrl+url);
		AssertMethod.page_Title_Should_Equals(list.get(0));
		AssertMethod.page_Keywords_Should_Equals(list.get(1));
		AssertMethod.page_Description_Should_Equals(list.get(2));
	}
}
