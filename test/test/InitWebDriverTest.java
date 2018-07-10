package test;

import java.util.ArrayList;
import java.util.List;




import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import tool.ExcelUtils;

public class InitWebDriverTest {

    public static void main(String[] args) {
        
//        WebDriver myBrowser = InitWebDriver.myDriver("firefox");
//        myBrowser.navigate().to("http://www.baidu.com");
////        
////        WebDriver myBrowser2 = InitWebDriver.myDriver("ie");
////        myBrowser2.navigate().to("http://www.baidu.com");
//        
//        WebDriver myBrowser3 = InitWebDriver.myDriver("chrome");
//        myBrowser3.navigate().to("http://www.baidu.com");
        Workbook wb = ExcelUtils.checkFile("drivers/hh.xlsx");
    	List<List> list = new ArrayList<List>();
//    	list= ExcelUtils.getAllData("drivers/hh.xlsx", "込込");
    	list = ExcelUtils.getDatas(wb, "込込", 1, 10);
    	System.err.println("data"+ExcelUtils.getData(wb, "込込", 4, 7));
    	System.err.println(ExcelUtils.getCellRow(wb, "込込", 4, 7));
    	List<String> lStrings = new ArrayList<String>();
    	lStrings = ExcelUtils.getDatas(wb, "込込", 4, 8, ExcelUtils.getCellRow(wb, "込込", 4, 7));
    	for (String string : lStrings) {
			System.out.println(string);
		}
//    	for (List list2 : list) {
//    		System.err.println("---------");
//			for (Object object : list2) {
//				System.out.println(object.toString());
//			}
//		}
    	
    }

}