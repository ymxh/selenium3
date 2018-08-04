package cn.selenium.method;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

public class JSExecutorMethod{
	
	private static Logger logger = Logger.getLogger(JSExecutorMethod.class);
	
	/**
	 * 检查页面元素加载完成，dom加载完成可以查找元素
	 */
	public static boolean pageDOMLoadComplete(){
		boolean complete = false;
		JavascriptExecutor JS=(JavascriptExecutor) WebDriverMethod.ThreadDriver.get();
		try {
			while(!"complete".equals(JS.executeScript("return document.readyState"))){
				Thread.sleep(1000);
			}
			logger.info("页面元素加载完成");
			complete = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("判断页面元素加载异常");
		}
		return complete;
	}
	
	/**
	 * 检查浏览器内容加载完成，如执行浏览器的刷新、返回、前进、取url，就必须页面加载完成
	 */
	public static boolean pageBOMLoadComplete(){
		boolean complete = false;
		JavascriptExecutor JS=(JavascriptExecutor) WebDriverMethod.ThreadDriver.get();
		try {
			while(!"页面加载完成".equals(JS.executeScript("window.onload=function(){return '页面加载完成';};return window.onload();"))){
				Thread.sleep(1000);
			}
			logger.info("页面加载完成");
			complete = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("判断页面加载异常");
		}
		return complete;
	}
}
