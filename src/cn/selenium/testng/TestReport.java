package cn.selenium.testng;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestReport extends TestListenerAdapter{  
	  
    private String reportPath;  
       
    @Override  
    public void onStart(ITestContext context) {  
        File htmlReportDir = new File("test-output/custom_report");    
        if (!htmlReportDir.exists()) {    
            htmlReportDir.mkdirs();    
        }    
        String reportName = formateDate()+".html";    
        reportPath = htmlReportDir+"/"+reportName;    
        File report = new File(htmlReportDir,reportName);    
        if(report.exists()){    
            try {    
                report.createNewFile();    
            } catch (IOException e) {    
                e.printStackTrace();    
            }    
        }    
        StringBuilder sb = new StringBuilder("<html><head><title>测试报告</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
        		+ "<style type=\"text/css\">"
        		+ ".all{margin:0 10px 0 10px;}"
        		+ ".bugCount{padding-top:10px;}"
        		+ "input{border:0;background-color:white;}"
        		+ ".failed{color:red;}.success{color:green;}"
        		+ "td{border:solid #add9c0; border-width:0px 1px 1px 0px; }"
        		+ "table{border:solid #add9c0; border-width:1px 0px 0px 1px;table-layout:fixed;word-break:break-all;width:100%;}.title{background-color:#7FFFD4;}"
        		+ "tr td{width: 35%;box-sizing: border-box;}tr td:nth-of-type(2),tr td:nth-of-type(3){width: 15%;}</style></head>"
        		+"<body><div class=\"all\"><div><div><h2>Selenium自动化测试报告</h2></div><div><div><strong>执行结果总计</strong></div><div class=\"bugCount\">"
        		+ "<b>总用例:</b><input value=\"0\" disabled=\"disabled\" id=\"case_count\"/>"
        		+ "<b>通过:</b><input value=\"0\" disabled=\"disabled\" id=\"success\" class=\"success\"/>"
        		+ "<b>失败:</b><input value=\"0\" disabled=\"disabled\" id=\"failed\" class=\"failed\"/>"
        		+ "<b>通过率:</b><input value=\"0\" disabled=\"disabled\" id=\"pass_rate\" />"
        		+ "<b>总耗时:</b><input value=\"0\" disabled=\"disabled\" id=\"times\"/></div></div></div><hr /><div><table>"
        		+ "<tr class=\"title\"><td>测试类</td><td>测试方法</td><td>执行时间</td><td>测试结果</td></tr>");  
        String res = sb.toString();  
        try {
        	Files.write((Paths.get(reportPath)),res.getBytes("utf-8"));    
        } catch (IOException e) {    
            e.printStackTrace();    
        }   
    }  
      
    @Override  
    public void onTestSuccess(ITestResult result) {  
        StringBuilder sb = new StringBuilder("<tr><td>");
        sb.append(result.getTestClass().getRealClass().getName());
        sb.append("</td><td>");
        sb.append(result.getMethod().getMethodName());
        sb.append(result.getParameters()[0]);
        sb.append(result.getParameters()[1]);
        sb.append("</td><td>");
        sb.append(result.getEndMillis()-result.getStartMillis());
        sb.append("</td><td>");
        sb.append("<font color=\"green\">Passed</font>");
        sb.append("</td></tr>");
        String res = sb.toString();  
        try {  
        	synchronized ("12") {
        		Files.write((Paths.get(reportPath)),res.getBytes("utf-8"),StandardOpenOption.APPEND);  
			}
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
    }  
  
    @Override  
    public void onTestSkipped(ITestResult result) {    	
    	 StringBuilder sb = new StringBuilder("<tr><td>");
         sb.append(result.getTestClass().getRealClass().getName());
         sb.append("</td><td>");
         sb.append(result.getMethod().getMethodName());
         sb.append("</td><td>");
         sb.append(result.getEndMillis()-result.getStartMillis());
         sb.append("</td><td>");
         sb.append("<font color=\"yellow\">Skipped</font>");
         sb.append("<p align=\"left\">测试用例<font color=\"red\">跳过</font>，原因：<br>");  
         sb.append("<br><a style=\"background-color:#CCCCCC;\">");  
         Throwable throwable = result.getThrowable();   
                 sb.append(throwable.getMessage());   
                 sb.append("</a></p>");
        sb.append("</td></tr>");  
        String res = sb.toString();  
        try {  
        	synchronized ("2") {
        		Files.write((Paths.get(reportPath)),res.getBytes("utf-8"),StandardOpenOption.APPEND);  
        	}
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        
        
    }  
      
    @Override  
    public void onTestFailure(ITestResult result) {  
    	
    	 StringBuilder sb = new StringBuilder("<tr><td>");
         sb.append(result.getTestClass().getRealClass().getName());
         sb.append("</td><td>");
         sb.append(result.getMethod().getMethodName());
         sb.append(result.getParameters());
         sb.append("</td><td>");
         sb.append(result.getEndMillis()-result.getStartMillis());
         sb.append("</td><td>");
         sb.append("<font color=\"red\">Failed</font>");
         sb.append("<p align=\"left\">测试用例执行<font color=\"red\">失败</font>，原因：<br>");  
         sb.append("<br><a style=\"background-color:#CCCCCC;\">");  
         Throwable throwable = result.getThrowable();   
                 sb.append(throwable.getMessage());   
                 sb.append("</a></p>");  
        sb.append("</td></tr>"); 
        String res = sb.toString();    
        try {    
        	synchronized ("3") {
        		Files.write((Paths.get(reportPath)),res.getBytes("utf-8"),StandardOpenOption.APPEND);    
        	}
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
    }  
  
    
    @Override  
    public void onFinish(ITestContext testContext) {      
    	int caseCount = testContext.getFailedTests().size()+testContext.getPassedTests().size()+testContext.getSkippedTests().size();
        StringBuilder sb = new StringBuilder("</table></div></div>");  
        sb.append("<script type=\"text/javascript\">");
        //总用例数
        sb.append("window.onload = function(){document.getElementById('case_count').value='");
        sb.append(caseCount);
        sb.append("';");
        //成功用例数
        sb.append("document.getElementById('success').value='");
        sb.append(testContext.getPassedTests().size());
        sb.append("';");
        //失败用例数
        sb.append("document.getElementById('failed').value='");
        sb.append(testContext.getFailedTests().size());
        sb.append("';");
        //通过率
        sb.append("document.getElementById('pass_rate').value='");
        sb.append(((float)testContext.getPassedTests().size()/caseCount)*100+"%");
        sb.append("';");
        //总测试时间
        sb.append("document.getElementById('times').value='");
        sb.append(testContext.getEndDate().getTime()-testContext.getStartDate().getTime());
        sb.append("';}");
        sb.append("</script></body></html>");  
        String msg = sb.toString();  
        try {  
            Files.write((Paths.get(reportPath)),msg.getBytes("utf-8"),StandardOpenOption.APPEND);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
    
    public static String formateDate(){  
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
        Calendar cal = Calendar.getInstance();  
        Date date = cal.getTime();  
        return sf.format(date);  
    }  
    
}