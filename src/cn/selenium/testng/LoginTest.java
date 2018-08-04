package cn.selenium.testng;

import cn.selenium.method.AssertMethod;
import cn.selenium.page.HomePage;
import cn.selenium.page.LoginPage;
import cn.selenium.util.ListUtil;
import cn.selenium.util.XmlReader;
import java.util.List;
import org.testng.annotations.Test;


public class LoginTest extends TestCaseBeforeAfterMethod{
	
	private HomePage hp = null;
	
	private LoginPage loginpage = null;
	
	public void login(String param){
		//初始化webdriver并打开首页
		hp = new HomePage().openPageRequestUrl();
		//点击登录，进入登录页
		loginpage = hp.loginLink();
		//登录操作
		loginpage.login(ListUtil.returnList(param,","));
	}
	
	@Test
	public void case1(){
		List<String> list = XmlReader.readerCase("testcase.xml","Login","case1");
		this.login(list.get(0));
		AssertMethod.assertEquals(hp.userCenterText(),list.get(1));
		AssertMethod.page_URL_Should_Equals("http://www.8868.cn/");
	}
	
	
	@Test
	public void case2(){
		List<String> list = XmlReader.readerCase("testcase.xml","Login","case2");
		this.login(list.get(0));
		AssertMethod.assertEquals(loginpage.error_Warn(),list.get(1));
		AssertMethod.page_URL_Should_Not_Equals("http://www.8868.cn/");
	}
}
