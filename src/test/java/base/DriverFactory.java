package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	
	 public WebDriver initBrowser(String browserName){
	        WebDriver driver;
	        if(browserName.equalsIgnoreCase("chrome")){
	        	WebDriverManager.chromedriver().setup();
	            driver=new ChromeDriver();
	        }else if(browserName.equalsIgnoreCase("firefox")){
	        	WebDriverManager.firefoxdriver().setup();
	            driver=new FirefoxDriver();
	        }else{
	        	WebDriverManager.chromedriver().setup();
	            driver=new ChromeDriver();
	        }
	        tlDriver.set(driver);
	        return getDriverInstance();
	    }

	    public synchronized WebDriver getDriverInstance(){
	        return tlDriver.get();
	    }

}
