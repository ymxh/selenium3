package cn.selenium.main;

public class Application {
	
	private RegisterInter reg;
	
	public void setRegisterInter(RegisterInter reg){
		//根据接口引用对象来决定可以使用什么方式注册
		this.reg = reg;
	}
	
	public void register(String username,String password){
		reg.register(username, password);
	}
	
	public static void main(String[] args) {
		
		Application app = new Application();
		app.setRegisterInter(new PhoneRegisterImpl());
		app.register("13060633859", "123456");
		
		app.setRegisterInter(new EmailRegisterImpl());
		app.register("ymxh@163.com", "123456");

	}
}
