package cn.selenium.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import cn.selenium.method.BrowserMethod;

public class GameDownloadPage extends BasePage{
	
	private static Logger logger = Logger.getLogger(GameDownloadPage.class);
	
	/*游戏下载页访问地址*/
	public static final String startUrl = "http://www.8868.cn/game/";
	
	public By hotGameDownload = By.xpath("//H1[text()='热门手机游戏下载']"); //热门手机游戏下载
	public By hotGameInformation = By.xpath("//H2[text()='热门游戏资讯']"); //热门游戏资讯
	public By hotGameCommodity = By.xpath("//H2[text()='热门手游商品']"); //热门手游商品
	public By newGameDownload = By.xpath("//H1[text()='最新手机游戏下载']"); //最新手机游戏下载
	public By newGameInformation = By.xpath("//H2[text()='最新游戏资讯']"); //最新游戏资讯
	public By newGameCommodity = By.xpath("//H2[text()='最新手游商品']"); //最新手游商品
	public By guessYouLike = By.xpath("//H2[text()='猜你喜欢']"); //猜你喜欢
	
	public By hotTab = By.xpath("//a[text()='热门']");	//热门tab选项
	public By newTab = By.xpath("//a[text()='最新']");	//最新tab选项
	
	/**
	 * 打开页面地址;打开地址后使用this返回本对象，继续操作
	 * @return
	 */
	public GameDownloadPage openPageRequestUrl(){
		BrowserMethod.open(startUrl);
		logger.info("打开首页:"+startUrl);
		return this;
	}
	
	public void clickHotTab(){
		findElementClick(hotTab);
		logger.info("点击热门tab选项");
	}
	
	public void clickNewTab(){
		findElementClick(newTab);
		logger.info("点击最新tab选项");
	}
}
