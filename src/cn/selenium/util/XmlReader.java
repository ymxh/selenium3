package cn.selenium.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class XmlReader {
	
	private static Logger logger = Logger.getLogger(XmlReader.class);
	
	/**
	 * 读取xml文件，返回一个二维数组;在TestNg中配合数据源DataProvider使用
	 * @param fPath
	 * @param tagName
	 * @return
	 */
	public static Object[][] readerCase(String fPath,String tagName){
		Object object[][] = null;
		List<List<String>> list = new ArrayList<>(); 
		try {
			SAXReader sax = new SAXReader();
			Document document = sax.read(new File(fPath));
			//得到Root元素
			Element element = document.getRootElement();
			//取出Root下子元素名为tagName的元素
			Element son_element = element.element(tagName);
			//再取出tagName下的子元素集
			List<Element> grand_element = son_element.elements(); 
			//加强for循环读取case标签
			for(Element e:grand_element){
				List<String> listString = new ArrayList<>();
				//当param值为空，list增加""而不是null，所以在xml中单个参数为空时填写"空"
				if(e.attributeValue("param").equals("空")){
					listString.add("");
					logger.info(e.getName()+"标签的param属性值为空");
				}else{
					listString.add(e.attributeValue("param"));
					logger.info(e.getName()+"标签param属性值加入list");
				}
				listString.add(e.attributeValue("exp"));
				list.add(listString);
			}
			object = new Object[list.size()][];
			//list转为数组
			for(int i=0;i<list.size();i++){  
				object[i] = list.get(i).toArray();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("读取xml内容异常"+e.getMessage());
		}	
		return object;
	}
	
	
	/**
	 * 读取xml文件，返回list，通过测试用例标签得到标签的param与exp的属性值，在不使用数据源的情况下，单个测试执行读取数据时使用
	 * @param fPath
	 * @param tagName
	 * @param sonTagName
	 * @return
	 */
	public static List<String> readerCase(String fPath,String tagName,String sonTagName){
		List<String> listString = new ArrayList<>();
		try {
			SAXReader sax = new SAXReader();
			Document document = sax.read(new File(fPath));
			//得到Root元素
			Element element = document.getRootElement();
			//取出Root下子元素名为tagName的元素
			Element son_element = element.element(tagName);
			//再取出tagName下名为sonTagName的子元素
			Element grand_element = son_element.element(sonTagName); 
			//当param值为空，list增加""而不是null，所以在xml中单个参数为空时填写"空"
			if(grand_element.attributeValue("param").equals("空")){
				listString.add("");
				logger.info("遇到param为空");
			}else{
				listString.add(grand_element.attributeValue("param"));
				logger.info("标签param属性值加入list");
			}
			listString.add(grand_element.attributeValue("exp"));
			logger.info("单个case内容:"+listString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("读取xml内容异常"+e.getMessage());
		}
		return listString;
	}	
	
}
