package cn.selenium.test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import cn.selenium.method.WebDriverMethod;

public class LoginPage1 extends Page{
	
	@FindBy(name="username")WebElement username;
	
	@FindBy(id="userpwd")WebElement password;
	
	@FindBy(xpath="//button[text()='登录']")WebElement loginButton;
	
	@FindBy(css=".form-tip.warning")WebElement error_Warn;
	
	@FindBy(xpath="//a[text()='密码登录']")WebElement pw_login_tab;
	
	public HomePage1 homePage = new HomePage1();
	
	public String Login(String uname,String pw){
		String act = null;
		pw_login_tab.click();
		String startUrl = WebDriverMethod.ThreadDriver.get().getCurrentUrl();
		username.sendKeys(uname);
		password.sendKeys(pw);
		loginButton.click();
		String stopUrl = WebDriverMethod.ThreadDriver.get().getCurrentUrl();
		if(startUrl.equals(stopUrl)){
			act = error_Warn.getText();
			
		}else{
			if(homePage.userCenter.isDisplayed()){
				act = "登录成功";
			}
			
		}
		return act;
		
	}
}
