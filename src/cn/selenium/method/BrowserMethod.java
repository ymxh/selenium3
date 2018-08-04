package cn.selenium.method;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.log4j.Logger;

public class BrowserMethod {
	
	private static Logger logger = Logger.getLogger(BrowserMethod.class);
	
	/**
	 * 封装get方法，改名为open，加了页面加载完成的判断，使操作更强壮
	 * @param url
	 */
	public static void open(String url){
		WebDriverMethod.ThreadDriver.get().get(url);
		logger.info("打开url地址："+url);
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		return;
	}
	
	/**
	 * 返回当前页面title
	 * @return
	 */
	public static String getTitle(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		String title = WebDriverMethod.ThreadDriver.get().getTitle();
		logger.info("得到页面title值："+title);
		return title;
	}
	
	/**
	 * 返回当前页面url
	 * @return
	 */
	public static String getCurrentUrl(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		String url = WebDriverMethod.ThreadDriver.get().getCurrentUrl();
		logger.info("得到页面url值："+url);
		return url;
	}
	
	
	/**
	 * 浏览器返回操作
	 */
	public static void browserBack(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		WebDriverMethod.ThreadDriver.get().navigate().back();
		logger.info("浏览器返回操作");
	}
	
	/**
	 * 浏览器前进操作
	 */
	public static void browserForward(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		WebDriverMethod.ThreadDriver.get().navigate().forward();
		logger.info("浏览器前进操作");
	}
	
	/**
	 * 浏览器刷新操作
	 */
	public static void browserRefresh(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		WebDriverMethod.ThreadDriver.get().navigate().refresh();
		logger.info("浏览器刷新操作");
	}
	
	/**
	 * 对话框点击确认
	 */
	public static void alertAccept(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		Alert alertbox = WebDriverMethod.ThreadDriver.get().switchTo().alert();
		alertbox.accept();//点击确认
		logger.info("点击对话框的确认按钮");
	}
	
	/**
	 * 对话框点击取消
	 */
	public static void alertDismiss(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		Alert alertbox = WebDriverMethod.ThreadDriver.get().switchTo().alert();
		alertbox.dismiss();//点击取消
		logger.info("点击对话框的取消按钮");
	}
	
	
	/**
	 * 取出对话框内容
	 */
	public static String alertText(){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		Alert alertbox = WebDriverMethod.ThreadDriver.get().switchTo().alert();
		return alertbox.getText();
	}
	
	/**
	 * 返回当前页句柄
	 * @return
	 */
	public static String getWindowHandle(){
		logger.info("得到当前页句柄");
		return WebDriverMethod.ThreadDriver.get().getWindowHandle();
	}
	

	/**
	 * 切换到新窗口，传入当前窗口句柄
	 * @param currentWindow
	 */
	public static void switchWindow(String currentWindow){  
		Set<String> handles = WebDriverMethod.ThreadDriver.get().getWindowHandles();// 获取所有窗口句柄   
		logger.info("得到所有窗口句柄："+handles);
		Iterator<String> it = handles.iterator();    
		while (it.hasNext()) {        
			if (currentWindow == it.next()) {              
				continue;   
			}         
			try {        	  
				WebDriverMethod.ThreadDriver.get().close();// 关闭旧窗口 
				logger.info("关闭旧窗口");
				WebDriverMethod.ThreadDriver.get().switchTo().window(it.next());// 切换到新窗口
				logger.info("切换到新窗口");
			} catch (Exception e) {    
				e.printStackTrace();  
				logger.error("切换窗口异常："+e.getMessage());
			}      
		}    
	}
	
	
	/**
	 * 使用js操作滚动条，滚动位置根据hight传值来定
	 * @param hight
	 */
	public void scroll(String hight){
		if(JSExecutorMethod.pageBOMLoadComplete());
		logger.info("页面加载完成");
		//滚动条滚动
		String high="scroll(0,"+hight+");";//滚动到Y值10000像素的位置，一般10000就到页面的底部了，可以根据自己需要调试
		((JavascriptExecutor) WebDriverMethod.ThreadDriver.get()).executeScript(high);
		logger.info("操作滚动条，滚动位置："+hight);
	}
}
