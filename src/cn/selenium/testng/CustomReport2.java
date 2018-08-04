package cn.selenium.testng;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import cn.selenium.bean.InsertRunResultService;
import cn.selenium.bean.SeleniumRunResultBean;

public class CustomReport2 implements IReporter{
    
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        List<ITestResult> list = null;
        //循环读取suites
        for (ISuite suite : suites){
        	//拿到suite的结果
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            //循环读取结果
            for (ISuiteResult suiteResult : suiteResults.values()) {
            	list = new ArrayList<ITestResult>();
                ITestContext testContext = suiteResult.getTestContext();            
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                //统计通过、失败、跳过的测试用例数
                list.addAll(this.listTestResult(passedTests));
                list.addAll(this.listTestResult(failedTests));
                list.addAll(this.listTestResult(skippedTests));
                this.sort(list);
                this.outputResult(list);
            }          
        }
    }
    
    /**
     * 转化为ArrayList返回
     * @param resultMap
     * @return
     */
    private ArrayList<ITestResult> listTestResult(IResultMap resultMap){
        Set<ITestResult> results = resultMap.getAllResults();    
        return new ArrayList<ITestResult>(results);
    }
    
    /**
     * 排序，根据运行时间排序，运行开始时间大的排在前面
     * @param list
     */
    private void sort(List<ITestResult> list){
        Collections.sort(list, new Comparator<ITestResult>() {
            @Override
            public int compare(ITestResult r1, ITestResult r2) {
                if(r1.getStartMillis()>r2.getStartMillis()){
                    return 1;
                }else{
                    return -1;
                }              
            }
        });
    }
   
    /**
     * 报告输出类
     * @param dlist
     * @param list
     * @param path
     */
    private void outputResult(List<ITestResult> list){
    	SeleniumRunResultBean result = null;
    	InsertRunResultService resultService = new InsertRunResultService();
        try {
        	for (ITestResult testresult : list) {
        		result = new SeleniumRunResultBean();
        		result.setTest_class(testresult.getTestClass().getRealClass().getName());
        		result.setTestcase_name(testresult.getMethod().getMethodName());
                if(testresult.getParameters().length>0){
                	result.setTestcase_param(testresult.getParameters().toString());
                }
                result.setTestcase_time(testresult.getEndMillis()-testresult.getStartMillis());

                if("SUCCESS".equals(getStatus(testresult.getStatus()))){
                	result.setTestcase_status(1);
                	result.setTestcase_result("用例通过");
                	ArrayList<ITestResult> listResult = this.listTestResult(testresult.getTestContext().getPassedConfigurations());
                	for(int i=0;i<listResult.size();i++){
                		ITestResult itresult = listResult.get(i);
                		if(itresult.getName().equals("TestBeforeMethod")){
                			result.setXmltest_name(itresult.getParameters()[0]+""+itresult.getParameters()[1]);
                		}
                	}
                }else if("SKIP".equals(getStatus(testresult.getStatus()))){
                	result.setTestcase_status(2);
                	ArrayList<ITestResult> listResult = this.listTestResult(testresult.getTestContext().getSkippedConfigurations());
                	for(int i=0;i<listResult.size();i++){
                		ITestResult itresult = listResult.get(i);
                		if(itresult.getName().equals("TestBeforeMethod")){
                			result.setXmltest_name(itresult.getParameters()[0]+""+itresult.getParameters()[1]);
                		}
                	}
                    Throwable throwable = testresult.getThrowable();   
                    result.setTestcase_result(throwable.getMessage());   
                }else{
                	result.setTestcase_status(3);
                	ArrayList<ITestResult> listResult = this.listTestResult(testresult.getTestContext().getFailedConfigurations());
                	for(int i=0;i<listResult.size();i++){
                		ITestResult itresult = listResult.get(i);
                		if(itresult.getName().equals("TestBeforeMethod")){
                			result.setXmltest_name(itresult.getParameters()[0]+""+itresult.getParameters()[1]);
                		}
                	}
                    Throwable throwable = testresult.getThrowable();   
                    result.setTestcase_result(throwable.getMessage());
                }
             result.setCreate_time(new Date());
             resultService.insertResult(result);
           }
        }catch (Exception e) {          
            e.printStackTrace();
        }
         
    }
    
    /**
     * TestNg状态码，1表示成功、2表示失败、3表示跳过
     * @param status
     * @return
     */
    private String getStatus(int status){
        String statusString = null;
        switch (status) {      
        case 1:
            statusString = "SUCCESS";
            break;
        case 2:
            statusString = "FAILURE";
            break;
        case 3:
            statusString = "SKIP";
            break;
        default:           
            break;
        }
        return statusString;
    }
}
