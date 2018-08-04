package cn.selenium.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneRegister {

	public void register(String username, String password) {
		//手机正则表达式
		String regEx = "^1[3|5|8|7|][0-9]\\d{8}$";
		// 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher matcher = pattern.matcher(username);
		if(matcher.find()){
			System.out.println("注册用户名正确");
		}else{
			System.out.println("用户名不符合规范，请重新输入");
		}
	}
}
