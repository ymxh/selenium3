package cn.selenium.page;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{
	
	private static Logger logger = Logger.getLogger(LoginPage.class);
	
	public By usernameTextBox = By.id("username");
	public By passwordTextBox = By.id("userpwd");
	public By loginButton = By.name("button");
	public By error_Warn = By.cssSelector(".content");
	
	/**
	 * 登录失败时的提示语
	 * @return
	 */
	public String error_Warn(){
		String errorWarn = findElementGetText(error_Warn);
		logger.info("得到登录失败提示语:"+errorWarn);
		return errorWarn;
	}
	
	
	/**
	 * 登录操作
	 * @param list 入参参数
	 */
	public void login(List<String> list){
		findElementSendKeys(usernameTextBox, list.get(0));
		logger.info("登录用户名输入："+list.get(0));
		findElementSendKeys(passwordTextBox,list.get(1));
		logger.info("登录密码输入："+list.get(1));
		findElementClick(loginButton);
		logger.info("点击登录按钮");
	}
}
