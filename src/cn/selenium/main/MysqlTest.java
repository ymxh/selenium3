package cn.selenium.main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MysqlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dburl = "jdbc:mysql://127.0.0.1:3306/mydb";
		String name = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "";
		Connection conn = null;
		PreparedStatement pStatement = null;
		//构造函数
		try{
			Class.forName(name);
			conn = DriverManager.getConnection(dburl,user,password);
			String sql = "update login set fname=? where uid=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, "软件测试");
			pStatement.setInt(2, 3);
			int result = pStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				//先开的后关
				pStatement.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
