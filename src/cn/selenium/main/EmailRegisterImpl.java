package cn.selenium.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRegisterImpl implements RegisterInter{

	@Override
	public void register(String username, String password) {
		// TODO Auto-generated method stub
		//邮箱正则表达式
		String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
		// 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher matcher = pattern.matcher(username);
		if(matcher.find()){
			System.out.println("注册用户名正确");
		}else{
			System.out.println("用户名不符合规范，请重新输入");
		}
	}
	
	public String emailCode(){
		return "testEmail";
	}
}
