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
//        	Ĭ��·����ɾ
        	System.setProperty("webdriver.firefox.bin","F:/Program Files (x86)/Mozilla Firefox/firefox.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            
        }else if ("ie".equals(browser.toLowerCase())) {
//             ʹ��ǰ����һ�£�����
            //�رձ���ģʽ
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);    
//            capability.setCapability("ignoreProtectedModeSettings",true);
            //ָ������λ�ã�����������
            System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
            driver = new InternetExplorerDriver(capability);
            driver.manage().window().maximize();
            
        } else if ("chrome".equals(browser.toLowerCase())) {
            
            //ָ������λ�ã�����������
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            
        }else{
            
            System.out.println("�����ָ�����󣡣���");
        }
        return driver;
        
    } 

}