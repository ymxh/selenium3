package cn.selenium.test;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import cn.selenium.method.WebDriverMethod;

public class Page {

	public Page(){
		PageFactory.initElements(new AjaxElementLocatorFactory(WebDriverMethod.ThreadDriver.get(), 10) , this);
	}
}
