package cn.selenium.test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage1 extends Page{
	
	/* 首页用户中心入口（登录状态下）*/
	@FindBy(xpath="//div[@class='actions']/a[2]/div[text()='我的']")WebElement userCenter;
	
}
