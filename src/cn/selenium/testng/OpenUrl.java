package cn.selenium.testng;


import org.testng.annotations.Test;

import cn.selenium.method.WebDriverMethod;


public class OpenUrl extends TestCaseBeforeAfterMethod{
	
	@Test
	public void openBaidu(){
		WebDriverMethod.ThreadDriver.get().get("http://www.baidu.com");
	}
	
//	@Test
//	public void openSina(){
//		WebDriverMethod.ThreadDriver.get().get("http://www.sina.com.cn");
//	}
//
//	@Test
//	public void open163(){
//		WebDriverMethod.ThreadDriver.get().get("http://www.163.com");
//	}
}
