package cn.selenium.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import cn.selenium.method.BrowserMethod;

public class HomePage extends BasePage {
	
	private static Logger logger = Logger.getLogger(HomePage.class);
	
	/**
	 * 主站首页，也就是唯一的测试入口，所以使用了static final修饰，后面代码不可修改
	 */
	public static final String startUrl = "http://www.8868.cn/";
	
	
	public By loginLink = By.xpath("//div[@class='hd-barLogin']/a[2]");	//登录链接
	public By userCenterText = By.xpath("//div[@class='user']/div/div[1]");	//用户中心文案
	public By gameListLink = By.xpath("//ul[@class='hd-navList']//a[text()='游戏列表']");   //导航栏游戏列表
	
	/*首页seo要求标签*/
	public By gameTradePlatform = By.xpath("//H1[text()='8868手游交易平台']");  //手游交易平台
	public By bwGame = By.xpath("//H3[text()='必玩游戏']");	//必玩游戏
	public By newGameHotList = By.xpath("//H3[text()='新游热榜']");  //新游热榜
	public By todayInformation = By.xpath("//H2[text()='今日资讯']");	//今日资讯
	public By todayInformationOneTitle = By.xpath("//div[@class='right']/div[2]/div/a/strong");   //今日资讯标题1
	public By todayInformationTwoTitle = By.xpath("//div[@class='right']/div[3]/div/a/strong");   //今日资讯标题2
	public By hotCommodity = By.xpath("//H2[text()='热门商品']");	//热门商品
	public By newCommodity = By.xpath("//H2[text()='最新商品']");	//最新商品
	public By tradeArea = By.xpath("//H2[text()='交易专区']");	//交易专区
	public By downloadArea = By.xpath("//H2[text()='下载专区']");	//下载专区
	public By hotGameRankingList  = By.xpath("//H3[text()='热门游戏排行榜']");	//热门游戏排行榜
	public By moreHotGame = By.xpath("//H3/a[text()='更多热门游戏推荐']");	//更多热门游戏推荐
	
	
	/**
	 * 测试地址入口，首页地址;打开地址后使用this返回本对象，继续操作
	 * @return
	 */
	public HomePage openPageRequestUrl(){
		BrowserMethod.open(startUrl);
		logger.info("打开首页:"+startUrl);
		return this;
	}
	
	
	/**
	 * 登录入口链接,返回LoginPage对象
	 */
	public LoginPage loginLink(){
		findElementClick(loginLink);
		logger.info("点击首页登录链接");
		return new LoginPage();
	}
	
	/**
	 * 登录成功，个人中心入口模块文字内容，返回"8868手游交易平台个人中心"
	 * @return
	 */
	public String userCenterText(){
		logger.info("登录成功返回首页显示：8868手游交易平台个人中心");
		return findElementGetText(userCenterText);
	}
	
	
	/**
	 * 点击导航栏"游戏列表"，返回到GameJiaoYiPage类
	 * @return
	 */
	public GameJiaoYiPage gameListLink(){
		findElementClick(gameListLink);
		logger.info("点击导航栏游戏列表");
		return new GameJiaoYiPage();
	}
}
