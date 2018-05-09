package report;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseTestcase {
    public WebDriver driver;
    
    public void setdriver(WebDriver driver){
        this.driver=driver;
    }

    public void takescreen(String filename){
       ScreenScr.getScreen((TakesScreenshot)driver, filename);
    }
    }
