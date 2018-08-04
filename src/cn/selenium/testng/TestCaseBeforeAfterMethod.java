package cn.selenium.testng;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import cn.selenium.bean.InsertRunResultService;
import cn.selenium.method.WebDriverMethod;

/**
 * 所有testng测试类继承
 * @author Administrator
 *
 */
public class TestCaseBeforeAfterMethod{
	
	private Logger logger = Logger.getLogger(TestCaseBeforeAfterMethod.class);
	
	/**
	 * 清空TestNg数据表
	 */
	@BeforeSuite(alwaysRun=true)
	public void TestDeleteTableData(){
		new InsertRunResultService().deleteData();
		logger.info("清空TestNg数据表");
	}
	
	/**
	 * 所有测试测试方法执行前执行，alwaysRun属性表示方法必须执行
	 * @param browserName
	 * @param browserVersion
	 */
	@BeforeMethod(alwaysRun=true)
	@Parameters({"browserName","browserVersion"})
	//String browserName,String browserVersion
	public void TestBeforeMethod(String browserName,String browserVersion){
		//String browserName = "chrome",browserVersion = "55.0.2883.87";
		logger.info("*******************测试开始*******************");
		logger.info("浏览器名："+browserName+"||浏览器版本："+browserVersion);
		WebDriverMethod.driverStart(browserName, browserVersion);
	}
	
	
	/**
	 * 所有测试方法执行完后执行,下面所有层级都关闭一次
	 */
	@AfterMethod(alwaysRun=true)
	public void TestAfterMethod(){
		WebDriverMethod.driverQuit();
		logger.info("*******************测试结束*******************");
	}
	
}
