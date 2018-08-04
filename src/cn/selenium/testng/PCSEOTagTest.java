package cn.selenium.testng;

import org.testng.annotations.Test;

import cn.selenium.method.AssertMethod;
import cn.selenium.page.GameDownloadPage;
import cn.selenium.page.HomePage;

public class PCSEOTagTest extends TestCaseBeforeAfterMethod{
	
	/**
	 * 检查首页的SEO要求标签
	 */
	@Test
	public void checkHomePageTag(){
		HomePage hp = new HomePage();
		hp.openPageRequestUrl();
		AssertMethod.page_Element_Visible(hp.gameTradePlatform);
		AssertMethod.page_Element_Visible(hp.bwGame);
		AssertMethod.page_Element_Visible(hp.newGameHotList);
		AssertMethod.page_Element_Visible(hp.todayInformation);
		AssertMethod.page_Element_Visible(hp.todayInformationOneTitle);
		AssertMethod.page_Element_Visible(hp.todayInformationTwoTitle);
		AssertMethod.page_Element_Visible(hp.hotCommodity);
		AssertMethod.page_Element_Visible(hp.newCommodity);
		AssertMethod.page_Element_Visible(hp.tradeArea);
		AssertMethod.page_Element_Visible(hp.downloadArea);
		AssertMethod.page_Element_Visible(hp.hotGameRankingList);
		AssertMethod.page_Element_Visible(hp.moreHotGame);
	}
	
	
	@Test
	public void checkGameDownloadPageTag(){
		GameDownloadPage gdp = new GameDownloadPage();
		//点击热门tab选项
		gdp.openPageRequestUrl().clickHotTab();
		AssertMethod.page_Element_Visible(gdp.hotGameDownload);
		AssertMethod.page_Element_Visible(gdp.hotGameInformation);
		AssertMethod.page_Element_Visible(gdp.hotGameCommodity);
		//点击最新tab选项
		gdp.clickNewTab();
		AssertMethod.page_Element_Visible(gdp.newGameDownload);
		AssertMethod.page_Element_Visible(gdp.newGameInformation);
		AssertMethod.page_Element_Visible(gdp.newGameCommodity);
		AssertMethod.page_Element_Visible(gdp.guessYouLike);
	}
}
