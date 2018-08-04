package cn.selenium.main;



import org.apache.log4j.Logger;
public class Test {
	public static Logger logger = Logger.getLogger(Test.class);
	public static void main(String[] args) {
		
		Application app = new Application();
		app.setRegisterInter(new PhoneRegisterImpl());
		app.register("13060633859", "123456");
		
		app.setRegisterInter(new EmailRegisterImpl());
		app.register("ymxh@163.com", "123456");

	}
}
