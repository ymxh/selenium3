package cn.selenium.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class GameJiaoYiPage extends BasePage{
	
	private static Logger logger = Logger.getLogger(GameJiaoYiPage.class);
	
	public By gameSearchTextBox = By.name("key");
	public By gameSearchButton = By.id("hd-searchBtn");
	public By pageH1 = By.tagName("h1");	//页面h1标签的元素
	
	/**
	 * 输入游戏名查询，页面h1标签，显示“游戏名”交易平台
	 * @param gameName
	 * @return
	 */
	public String gameSearch(String gameName){
		findElementSendKeys(gameSearchTextBox, gameName);
		logger.info("输入游戏名："+gameName);
		findElementClick(By.id("hd-searchBtn"));
		logger.info("点击查询按钮");
		logger.info("返回搜索结果");
		return findElementGetText(pageH1);
	}
}
