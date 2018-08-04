package cn.selenium.method;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import cn.selenium.page.BasePage;
import cn.selenium.util.ListUtil;


public class AssertMethod extends Assert{
	
	private static Logger logger = Logger.getLogger(AssertMethod.class);
	
	
	/**
	 * 检查当前url与预期的url相等，入参预期url值
	 * @param exp_url
	 */
	public static void page_URL_Should_Equals(String exp_url){
		logger.info("检查当前url与预期的url相等");
		assertEquals(BrowserMethod.getCurrentUrl().equals(exp_url),true,"当前url与预期url不一致");
	}
	
	/**
	 * 检查当前url与预期的url不相等，入参预期url值
	 * @param exp_url
	 */
	public static void page_URL_Should_Not_Equals(String exp_url){
		logger.info("检查当前url与预期的url不相等");
		assertEquals(BrowserMethod.getCurrentUrl().equals(exp_url),false,"当前url与预期url一致");
	}
	
	/**
	 * 检查当前页title值与预期title相等,入参预期title值
	 * @param exp_title
	 */
	public static void page_Title_Should_Equals(String exp_title){
		logger.info("检查当前页title值与预期title相等");
		assertEquals(BrowserMethod.getTitle().equals(exp_title),true,"当前页title与预期title不一致");
	}
	
	/**
	 * 检查当前页title值与预期title不相等,入参预期title值
	 * @param exp_title
	 */
	public static void page_Title_Should_Not_Equals(String exp_title){
		logger.info("检查当前页title值与预期title不相等");
		assertEquals(BrowserMethod.getTitle().equals(exp_title),false,"当前页title与预期title不一致");
	}
	
	/**
	 * 检查当前页面内容包含预期内容，入参预期文本值
	 * @param exp_text
	 */
	public static void page_Text_Present(String exp_text){
		logger.info("检查页面内容包含预期内容");
		// 遍历body节点下的所有节点取其文本值并判断是否包含文本
		assertEquals(new BasePage().findElementGetText(By.tagName("body")).contains(exp_text),true,"页面中没有匹配的文字");
	}
	
	/**
	 * 检查当前页面内容不包含预期内容，入参预期文本值
	 * @param exp_text
	 */
	public static void page_Text_Not_Present(String exp_text){
		logger.info("检查页面内容不包含预期内容");
		assertEquals(new BasePage().findElementGetText(By.tagName("body")).contains(exp_text),false,"页面中有匹配的文字");
	}
	
	/**
	 * 检查当前页面元素内容包含预期内容，入参元素locator、预期文本值
	 * @param by
	 * @param exp_text
	 */
	public static void page_Element_Text_Equals(By by,String exp_text){
		logger.info("检查页面元素文本内容包含预期内容");
		assertEquals(new BasePage().findElementGetText(by).contains(exp_text),true,"页面中没有匹配文字的元素");	
	}
	
	/**
	 * 检查页面元素文本以预期内容开始，入参元素locator、预期文本值
	 * @param by
	 * @param exp_text
	 */
	public static void page_Element_Text_StartWith(By by,String exp_text){
		logger.info("检查页面元素文本以预期内容开始");
		assertEquals(new BasePage().findElementGetText(by).startsWith(exp_text),true,"页面中没有匹配预期文字开始的元素");
	}
	
	/**
	 * 检查页面元素文本以预期内容结束，入参元素locator、预期文本值
	 * @param by
	 * @param exp_text
	 */
	public static void page_Element_Text_EndWith(By by,String exp_text){
		logger.info("检查页面元素文本以预期内容结束");
		assertEquals(new BasePage().findElementGetText(by).endsWith(exp_text),true,"页面中没有匹配预期文字结束的元素");
	}
	
	
	/**
	 * 检查页面元素存在
	 * @param by
	 * @param isVisible
	 */
	public static void page_Element_Visible(By by){
		boolean waitResult = false;
		try {
			waitResult =	WaitMethod.visibilityOfElementLocated(by);
			logger.info("查找元素locator="+by);
		}catch (Exception e) {
			//异常就不抛出了
			waitResult =  false;
			logger.error("元素位置"+by+"不可见"+e.getMessage());	
		}
		assertEquals(waitResult,true,"页面元素实际结果<不显示>，与预期<显示>不一致");
	}
	
	/**
	 * 检查页面元素不存在
	 * @param by
	 * @param isVisible
	 */
	public static void page_Element_Not_Visible(By by){
		boolean waitResult = false;
		try {
			waitResult =	WaitMethod.visibilityOfElementLocated(by);
			logger.info("查找元素locator="+by);
		}catch (Exception e) {
			//异常就不抛出了
			waitResult =  false;
			logger.error("元素位置"+by+"不可见"+e.getMessage());	
		}
		assertEquals(waitResult,false,"页面元素实际结果<显示>，与预期<不显示>不一致");
	}
	
	/**
	 * 检查下拉菜单选项内容
	 * @param by
	 * @param exp
	 */
	public static void page_selectElement_Options(By by,List<String> exp){
		logger.info("检查下拉菜单选项与预期结果");
		assertEquals(ListUtil.listCompare(new BasePage().selectGetOptions(by), exp),true,"下拉菜单选项与预期结果不一致");
	}
	
	/**
	 * 检查页面seo标准之一，在head中name=keywords的content值
	 * html中head标签内容的元素，使用isDisplayed()返回的false，所以不能使用有关isDisplayed()的方法来判断
	 * @param exp
	 */
	public static void page_Keywords_Should_Equals(String exp){
		String content = null;
		if(JSExecutorMethod.pageDOMLoadComplete()){
			content = WebDriverMethod.ThreadDriver.get().findElement(By.name("keywords")).getAttribute("content");
		}
		logger.info("检查页面的keywords值与预期值相等");
		assertEquals(content,exp,"页面的keywords值与预期不相等");
	}
	
	/**
	 * 检查页面seo标准之一，在head中name=description的content值
	 * @param exp
	 */
	public static void page_Description_Should_Equals(String exp){
		String content = null;
		if(JSExecutorMethod.pageDOMLoadComplete()){
			content = WebDriverMethod.ThreadDriver.get().findElement(By.name("description")).getAttribute("content");
		}			
		logger.info("检查页面的description值与预期值相等");
		assertEquals(content,exp,"页面的description值与预期不相等");
	}
}
