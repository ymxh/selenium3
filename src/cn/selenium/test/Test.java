package cn.selenium.test;


import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cn.selenium.bean.InsertRunResultService;
import cn.selenium.method.WebDriverMethod;

public class Test {
	
	public static void main(String args[]){
		
//		WebDriverMethod.driverStart("chrome", "65.0.3325.181");
//		
//		WebDriver driver = WebDriverMethod.ThreadDriver.get();
		WebDriver driver = new ChromeDriver();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			
			driver.manage().window().maximize();
			driver.get("http://m.ctrip.com/webapp/vacations/pguider/homepage?from%3Dhttp%3A%2F%2Fm.ctrip.com%2Fhtml5%2F");
			
			Thread.sleep(2000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='main-nav-inner-text']/div[2]")));
			driver.findElement(By.xpath("//div[@class='item current']/i")).click();
			driver.findElement(By.xpath("//div[@class='item current']/span")).click();
			driver.findElement(By.xpath("//span[text()='优选向导']")).click();
			driver.findElement(By.xpath("//div[@class='main-nav-inner-text']/div[2]")).click();
			
			int high=10000;//滚动到Y值10000像素的位置，一般10000就到页面的底部了，可以根据自己需要调试
			while(driver.findElements(By.xpath("//div[text()='没有更多结果了']")).isEmpty()){
				//滚动条滚动
				((JavascriptExecutor) driver).executeScript("scroll(0,"+high+")");
				Thread.sleep(3000);
				high = high*2;
			}
			
			List<WebElement> ulList = driver.findElements(By.xpath("//div[@id='guidelist']/ul/li"));
			for(int i=1;i<=ulList.size();i++){
				WebElement element = driver.findElement(By.xpath("//div[@id='guidelist']/ul/li["+i+"]"));
				String pic = element.findElement(By.xpath(".//div[@class='pic']/img")).getAttribute("src");
				String name = element.findElement(By.xpath(".//h3[@class='name']")).getText();
				String intro = element.findElement(By.xpath(".//p[@class='intro line1']")).getText();
				System.out.println("姓名："+name+"||简介："+intro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
//		try {
//			WebDriverWait wait = new WebDriverWait(driver,10);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("title")));
//		} catch (Exception e) {
//			
//		}
//		System.out.println(driver.findElement(By.name("keywords")).isDisplayed());
//		System.out.println(driver.findElement(By.name("keywords")).isEnabled());
//		String text = driver.findElement(By.name("keywords")).getAttribute("content");
//		System.out.println(text);
////		long a = 1;
////		long b = 4;
////		System.out.println((long)(((float)a/b)*100));
		driver.quit();
		
		
		
	}
}
