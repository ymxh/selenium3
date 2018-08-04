package cn.selenium.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitMethod{

	private static Logger logger = Logger.getLogger(WaitMethod.class);
	/**
	 * 智能等待，元素不可见时，最多等待10S，10S内找到元素立即返回
	 * @param locator
	 * @return
	 */
	public static boolean visibilityOfElementLocated(By by) throws Exception{
		boolean waitResult = false;
		if(JSExecutorMethod.pageDOMLoadComplete()){
			/*根据源码介绍visibilityOfElementLocated方法，实际也是通过findElement()方法找到元素然后使用isDisplayed()返回true
			 * 与WebDriverWait等待配合使用,说明白点就是isDisplayed()返回ture跳出等待
			 */
			WebDriverWait wait = new WebDriverWait(WebDriverMethod.ThreadDriver.get(),10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			logger.info("元素位置"+by+"可见");
			waitResult =  true;
		}
		return waitResult;
	}
	
	
}
