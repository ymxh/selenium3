package cn.selenium.bean;

import java.util.ArrayList;
import java.util.List;

import cn.selenium.util.MysqlHelper;

public class InsertRunResultService {
	
	public void insertResult(SeleniumRunResultBean result){
		String sql = "insert into autoselenium(`xmltest_name`,`test_class`,`testcase_name`,`testcase_param`,`testcase_status`,`testcase_result`,`testcase_time`,`create_time`) values(?,?,?,?,?,?,?,?)";
		try {
			MysqlHelper msh = new MysqlHelper();
			List<Object> params = new ArrayList<>();
			params.add(result.getXmltest_name());
			params.add(result.getTest_class());
			params.add(result.getTestcase_name());
			params.add(result.getTestcase_param());
			params.add(result.getTestcase_status());
			params.add(result.getTestcase_result());
			params.add(result.getTestcase_time());
			params.add(result.getCreate_time());
			msh.addDeleteModify(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void deleteData(){
		List<String> sql = new ArrayList<String>();
		String sql_one = "select count(*) from autoselenium;";
		String sql_two = "delete from autoselenium;";
		String sql_three = "alter table autoselenium AUTO_INCREMENT= 1;";
		try {
			MysqlHelper msh = new MysqlHelper();
			sql.add(sql_two);
			sql.add(sql_three);
			msh.addsDeletesModifys(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
