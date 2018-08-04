package cn.selenium.page;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cn.selenium.method.WaitMethod;
import cn.selenium.method.WebDriverMethod;

public class BasePage {
	
	private static Logger logger = Logger.getLogger(BasePage.class);
	
	public WebDriver driver;
	
	public BasePage(){
		this.driver = WebDriverMethod.ThreadDriver.get();
		logger.info("使用BasePage类初始化driver对象");
	}
	
	/**
	 * 找到元素并点击操作
	 * @param elment 传参为By元素by
	 */
	public void findElementClick(By by){
		try {
			if(WaitMethod.visibilityOfElementLocated(by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					element.click();
					logger.info("元素点击操作，位置"+by);
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("元素点击操作异常"+e.getMessage());
		}
	}
	
	/**
	 * 找到元素并输入内容
	 * @param elment 传参为By元素by
	 * @param text  传参输入的内容
	 */
	public void findElementSendKeys(By by,String text){
		try {
			if(WaitMethod.visibilityOfElementLocated(by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					element.clear();
					element.sendKeys(text);
					logger.info("元素输入内容"+text);
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("元素输入操作异常"+e.getMessage());
		}
	}
	
	

	/**
	 *找到元素并取出元素文本值返回
	 * @param elment  传参为By元素by
	 * @return  返回文本值，没有文本值返回null
	 */
	public String findElementGetText(By by){
		String result = null;
		try {
			if(WaitMethod.visibilityOfElementLocated(by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					result = element.getText();
					logger.info("获取元素文本值:"+result);		
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("获取元素文本值操作异常"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 找到元素集并取出每个元素的文本值存入List返回
	 * @param by
	 * @return
	 */
	public List<String> findElemensGetText(By by){
		List<String> result=  new ArrayList<>();
		try {
			if(WaitMethod.visibilityOfElementLocated(by)){
				List<WebElement> elements = driver.findElements(by);
				if(elements!=null){
					for(WebElement e:elements){
						if(e.isEnabled()){
							result.add(e.getText());		
						}
					}
					logger.info("获取元素集合文本值:"+result);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("获取元素集合文本值操作异常"+e.getMessage());
		}
		return result;
	}
	
	/**
	 *找到元素并根据param传入的属性名取出属性值
	 * @param elment  传参为By元素by
	 * @param attributeName  传参为属性名
	 * @return  返回属性值值，没有值返回null
	 */
	public String findElementGetAttribute(By by,String attributeName){
		String result = null;
		try {
			if(WaitMethod.visibilityOfElementLocated( by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					result = element.getAttribute(attributeName);
					logger.info("获取元素的"+attributeName+"属性值="+result);
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("获取元素属性值异常"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 找到元素集并取出每个元素的属性值存入List返回
	 * @param by
	 * @return
	 */
	public List<String> findElementsGetAttribute(By by,String attributeName){
		List<String> result=  new ArrayList<>();
		try {
			if(WaitMethod.visibilityOfElementLocated(by)){
				List<WebElement> elements = driver.findElements(by);
				if(elements!=null){
					for(WebElement e:elements){
						if(e.isEnabled()){
							result.add(e.getAttribute(attributeName));		
						}
					}
					logger.info("获取元素集合的所有"+attributeName+"属性值="+result);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("获取元素集合属性值操作异常"+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 下拉框根据文本值选择
	 * @param by
	 * @param text
	 */
	public void selectByText(By by,String text){
		try {
			if(WaitMethod.visibilityOfElementLocated( by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					Select select = new Select(element);
					select.selectByVisibleText(text);
					logger.info("根据下拉框文本值选择="+text);
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("选择下拉框值异常"+e.getMessage());
		}
		
	}
	
	/**
	 * 下拉框根据index位置选择
	 * @param by
	 * @param index
	 */
	public void selectByIndex(By by,int index){
		try {
			if(WaitMethod.visibilityOfElementLocated( by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					Select select = new Select(element);
					select.selectByIndex(index);
					logger.info("根据下拉框下标选择="+index);
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("选择下拉框值异常"+e.getMessage());
		}
		
	}
	
	/**
	 * 下拉框根据value值选择
	 * @param by
	 * @param text
	 */
	public void selectByValue(By by,String text){
		try {
			if(WaitMethod.visibilityOfElementLocated( by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					Select select = new Select(element);
					select.selectByValue(text);
					logger.info("根据下拉框值选择="+text);
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("选择下拉框值异常"+e.getMessage());
		}
		
	}
	
	/**
	 * 取出所有下拉框的对象，然后循环对象取出text文本值放入到List返回
	 * @param by
	 * @param text
	 * @return
	 */
	public List<String> selectGetOptions(By by){
		List<String> selectValue = new ArrayList<>();
		try {
			if(WaitMethod.visibilityOfElementLocated( by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					Select select = new Select(element);
					for(int i=0;i<select.getOptions().size();i++){
						selectValue.add(select.getOptions().get(i).getText());
					}
					logger.info("获取下拉框全部值");
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("获取下拉框全部值异常"+e.getMessage());
		}
        return selectValue;
	}
	
	/**
	 * 取table的行列，取行定位到tr,取列定位到td
	 * @param by
	 * @return
	 */
	public int tableRowsORCols(By by){
		int size = 0;
		try {
			if(WaitMethod.visibilityOfElementLocated(by)){
				size = driver.findElements(by).size();
				logger.info("获取table行或列数");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("获取table行或列数异常"+e.getMessage());
		}
		return size;
	}
	
	/**
	 * 切换到frame框架
	 * @param by
	 */
	public void selectFrame(By by){
		try {
			if(WaitMethod.visibilityOfElementLocated(by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					driver.switchTo().frame(element);
					logger.info("切换到frame框架");
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("切换到frame框架异常"+e.getMessage());
		}
	}
	
	/**
	 * 离开frame框架
	 */
	public void quitFrame(){
		driver.switchTo().defaultContent();
		logger.info("离开frame框架");
	}
	
	/**
	 * 返回上一层frame框架，适合在多frame框架使用
	 */
	public void parentFrame(){
		driver.switchTo().parentFrame();
		logger.info("离开frame框架");
	}
	
	/**
	 * 输入存放路径，截图名字，固定以png格式图片
	 * @param path
	 * @param pictureName
	 */
	public void printScreen(String path,String pictureName){
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(path+pictureName+".png"));
			logger.info("保存截图");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("保存截图异常"+e.getMessage());
		}
	}
	
	
	/**
	 * 鼠标操作之一，滚动到元素位置
	 * @param by
	 */
	public void moveMenu(By by){
		try {
			//滚动到素材管理菜单
			Actions actions = new Actions(driver);
			if(WaitMethod.visibilityOfElementLocated(by)){
				WebElement element = driver.findElement(by);
				if(element.isEnabled()){
					actions.moveToElement(element).perform();
					logger.info("滚动到元素位置");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("滚动到元素位置异常"+e.getMessage());
		}	
	}
}
