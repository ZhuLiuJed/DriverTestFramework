package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InitWebDriver {

    public static WebDriver myDriver(String browser) {
        
        WebDriver driver = null;
        
        if ("firefox".equals(browser.toLowerCase())) {
//        	默认路径可删
        	System.setProperty("webdriver.firefox.bin","F:/Program Files (x86)/Mozilla Firefox/firefox.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            
        }else if ("ie".equals(browser.toLowerCase())) {
//             使用前调试一下，看看
            //关闭保护模式
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);    
//            capability.setCapability("ignoreProtectedModeSettings",true);
            //指定驱动位置，并加载驱动
            System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
            driver = new InternetExplorerDriver(capability);
            driver.manage().window().maximize();
            
        } else if ("chrome".equals(browser.toLowerCase())) {
            
            //指定驱动位置，并加载驱动
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            
        }else{
            
            System.out.println("浏览器指定错误！！！");
        }
        return driver;
        
    } 

}