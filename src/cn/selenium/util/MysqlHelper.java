package cn.selenium.util;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MysqlHelper {
	
	//加载驱动
    private static String DRIVER = "";
    //定义数据库账号
    private static String USERNAME = "";
    //定义数据库密码
    private static String PASSWORD = "";
    //访问的地址
    private static String URL = "";
    //定义数据库的连接
    private Connection connection = null;
	//定义sql语句的执行对象，用PreparedStatement可以预防or 1=1，而Statement是存在注入漏洞的
    private PreparedStatement pStatement = null;
    //定义查询返回的结果集合
    private ResultSet resultset = null;
    //定义存储过程执行对象
    private CallableStatement clstatement = null;
    
    private static InputStream fis = null;
    
    
    static{
    	Properties props = new Properties();  
    	try {
			fis = MysqlHelper.class.getClassLoader().getResourceAsStream("test.properties");
			props.load(fis);
			DRIVER = props.getProperty("connection.driver_class");
			USERNAME = props.getProperty("connection.username");
			PASSWORD = props.getProperty("connection.password");
			URL = props.getProperty("connection.url");
			//只注册一次
			Class.forName(DRIVER);//注册驱动
			//System.out.println("我是静态取值的方法，等一下测试一下，是先执行这个static方法再执行构造函数");
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 System.out.println("读取配置文件异常");
		}
    }
    
    public MysqlHelper()
    {
        try {
        	//System.out.println("我是连接");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);//定义连接
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    
    /**
     * 完成对数据库的增删改操作
     * @param sql语句
     * @param 传入的占位符，List集合
     * @return SQL语句执行成功返回true,否则返回false
     * @throws SQLException
     */
    public boolean addDeleteModify(String sql,List<Object> params) throws SQLException
    {
    	int result = -1;//设置为
    	try {
            pStatement = connection.prepareStatement(sql);  //填充占位符
            int index = 1; //从第一个开始添加
            if(params != null && !params.isEmpty())
            {
                for(int i = 0;i<params.size();i++)
                {
                    pStatement.setObject(index++,params.get(i));//填充占位符
                }
            }
            
          //执行成功将返回大于0的数
          //这里有个缺陷就是如果执行多条，不知道执行成功了多少条
            result = pStatement.executeUpdate();
            
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 //抛出运行异常
			 throw new RuntimeException(e.getMessage());
		}finally{
			//关闭资源
			this.closeConnection(resultset,pStatement,connection);
		}
    	
    	return  result>0 ? true : false;
        
    }
    
    /**
     * 执行多条update、insert、delete的sql语句,并且可能存在事务关联
     * @param sql 多条sql语句组成list集
     * @param params 多条sql语句的参数用2维数组存
     */
    public void addsDeletesModifys(List<String> sql,String[][] params){
    	try {
            connection.setAutoCommit(false);
    		if(sql != null && !sql.isEmpty()){
    			
    			for(int i=0;i<sql.size();i++){
    				if(sql.get(i)!=null){
    					pStatement = connection.prepareStatement(sql.get(i));
    					if(params != null && params.length != 0){
    						for(int j=0;j<params[i].length;j++){
        						pStatement.setObject(j+1, params[i][j]);
        					}
    					}	
    					pStatement.executeUpdate();
    				}
    			}
    			
    		}
    		connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 try {
				 //如果执行异常，则回滚，主要是事务关联影响，因为多条sql语句关联，执行一条导致另一条失败，需要回滚
				 connection.rollback();		
				//抛出运行异常
				 throw new RuntimeException(e.getMessage());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 
		}finally{
			//关闭资源
			this.closeConnection(resultset,pStatement,connection);
		}
    }
   
    /**
     * 数据库查询操作，返回单个一字段值
     * @param sql语句
     * @param 传入的占位符
     * @return 返回Object类型，包含查询的结果
     * @throws SQLException
     */
     public Object returnResultValue(String sql,List<Object> params) throws SQLException
     {
         Object value = null;
         int index = 1;//从1开始设置占位符
         try {
         	 pStatement = connection.prepareStatement(sql);
              if(params != null && !params.isEmpty()) /*判断参数是否为空*/
              { 
                  for(int i = 0;i<params.size();i++) /*循环填充占位符*/
                  {
                      pStatement.setObject(index++, params.get(i));
                  }
              }
              resultset = pStatement.executeQuery();
              if(resultset!=null){
            	  while(resultset.next()){
            		  value = resultset.getObject(1);
                  }
              }            
 		} catch (Exception e) {
 			// TODO: handle exception
 			 e.printStackTrace();
 		}finally{
 			this.closeConnection(resultset,pStatement,connection);
 		}
        
         return value;
     }
    
    
   /**
    * 数据库查询操作，返回单条记录
    * @param sql语句
    * @param 传入的占位符
    * @return 返回Map集合类型，包含查询的结果
    * @throws SQLException
    */
    public Map<String,Object> returnSimpleResult(String sql,List<Object> params) throws SQLException
    {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;//从1开始设置占位符
        try {
        	 pStatement = connection.prepareStatement(sql);
             if(params != null && !params.isEmpty()) /*判断参数是否为空*/
             { 
                 for(int i = 0;i<params.size();i++) /*循环填充占位符*/
                 {
                     pStatement.setObject(index++, params.get(i));
                 }
             }
             resultset = pStatement.executeQuery();
             /*  将查询结果封装到map集合*/
             ResultSetMetaData metaDate = resultset.getMetaData();//获取resultSet列的信息
             int columnLength = metaDate.getColumnCount();//获得列的长度
             while(resultset.next())
             {
                 for(int i = 0;i<columnLength;i++)
                 {
                     String metaDateKey = metaDate.getColumnName(i+1);//获得列名
                     Object resultsetValue = resultset.getObject(metaDateKey);//通过列名获得值
                     if(resultsetValue == null)
                     {
                         resultsetValue = "";//转成String类型
                     }
                     map.put(metaDateKey, resultsetValue);//添加到map集合（以上代码是为了将从数据库返回的值转换成map的key和value）
                 }
             }
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}finally{
			this.closeConnection(resultset,pStatement,connection);
		}
       
        return map;
    }
    /**
     * 查询数据库，返回多条记录
     * @param sql语句
     * @param 占位符
     * @return list集合，包含查询的结果
     * @throws SQLException 
     */
    public List<Map<String,Object>> returnMultipleResult(String sql,List<Object>params) throws SQLException
    {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        //填充占位符
        int index = 1;
        
        try {
        	pStatement = connection.prepareStatement(sql);
            if(params != null && !params.isEmpty())
            {
                for(int i = 0;i<params.size();i++)
                {
                    pStatement.setObject(index++, params.get(i));
                }
            }
            //执行SQL语句
            resultset = pStatement.executeQuery();
            //封装resultset成map类型
            ResultSetMetaData metaDate = resultset.getMetaData();//获取列信息,交给metaDate
            //System.out.println(metaDate);
            int columnlength = metaDate.getColumnCount();
            while(resultset.next())
            {
                Map<String, Object> map = new HashMap<String, Object>();
                for(int i = 0;i<columnlength;i++)
                {
                    String metaDateKey = metaDate.getColumnName(i+1);//获取列名
                    Object resultsetValue = resultset.getObject(metaDateKey);
                    if(resultsetValue == null)
                    {
                        resultsetValue = "";
                    }
                    map.put(metaDateKey, resultsetValue);
                }
                list.add(map);
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			this.closeConnection(resultset,pStatement,connection);
		}
        
        return list;
    }
    
     
    /**
     * 调用存储过程，没有返回值
     * @param sql  调用存储过程的sql，以{call 存储过程名(?,?)}
     * @param params  数组存放参数
     */
    public void callProcedure(String sql,String[] params){
    	
    	try {
    		clstatement = connection.prepareCall(sql);
    		if(params != null){
    			for(int i=0;i<params.length;i++){
    				clstatement.setObject(i+1, params[i]);
    			}
    		}
    		clstatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//抛出运行异常
			throw new RuntimeException(e.getMessage());	
		}finally{
			this.closeConnection(resultset, clstatement, connection);
		}
    }
    
    
    /**
     * 执行存储过程，返回值
     * @param sql  执行的存储过程sql
     * @param inparams   输入的参数
     * @param outparams  输出的参数
     * @return   返回CallableStatement
     */
    public CallableStatement callProcedureReturnCursor(String sql,String[] inparams,int[] outparams){
    	
    	try {
    		clstatement = connection.prepareCall(sql);
    		if(inparams != null){
    			for(int i=0;i<inparams.length;i++){
    				clstatement.setObject(i+1, inparams[i]);
    			}
    		}
    		
    		if(outparams != null){
    			for(int i=0;i<outparams.length;i++){
    				//注册的位置，则是输入参数的大小+1再加i，因为i可能是多个值
    				clstatement.registerOutParameter(inparams.length+1+i, outparams[i]);
    			}
    		}
    		clstatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//抛出运行异常
			throw new RuntimeException(e.getMessage());	
		}finally{
			//不需要关闭
		}
    	
    	return clstatement;	
    }
    
    /**
     * 注意在finally里面执行以下方法，关闭连接
     */
    public void closeConnection(ResultSet resultset,Statement pStatement,Connection connection)
    {
        if(resultset != null)
        {
            try{
                resultset.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(pStatement != null)
        {
            try {
                pStatement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(connection != null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    //提供get方法，便于调用工具类的资源关闭
    public Connection getConnection() {
		return connection;
	}
	public PreparedStatement getpStatement() {
		return pStatement;
	}
	public ResultSet getResultset() {
		return resultset;
	}
	public CallableStatement getClstatement() {
		return clstatement;
	}
}
