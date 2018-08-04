package org.apache.log4j;

import org.apache.log4j.helpers.PatternParser;

public class MyPatternLayout extends PatternLayout{
	public MyPatternLayout(String pattern) {  
		super(pattern);  
	}  
	public MyPatternLayout() {  
		super();  
	}  
	        
	/** 
	* 重写createPatternParser方法，返回PatternParser的子类 
	*/  
	@Override  
	protected PatternParser createPatternParser(String pattern) {  
		return new MyPatternParser(pattern);  
	}  
}
