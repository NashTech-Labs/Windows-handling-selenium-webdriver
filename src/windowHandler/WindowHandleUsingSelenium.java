package windowHandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import myLib.TakeScreenshot;

public class WindowHandleUsingSelenium {
	static String ic;
	static WebDriver driver;
	public static void main(String[] args) throws IOException {
		
		windowHandle("/home/knoldus/Downloads/Automation/chromedriver_linux64/chromedriver","http://demo.automationtesting.in/Windows.html"
				,driver,"//*[@target='_blank']");
		
	}
	
	public static void windowHandle(String driverpath, String url , WebDriver driver, String xpath)
	{
		//System.setProperty("webdriver.chrome.driver", "/home/knoldus/Downloads/Automation/chromedriver_linux64/chromedriver");
		System.setProperty("webdriver.chrome.driver", driverpath);
		driver = new ChromeDriver();
		driver.get(url);
		String baseWindow = driver.getWindowHandle();
		driver.findElement(By.xpath(xpath)).click();
		Set<String> tabs = driver.getWindowHandles();
		Iterator it = tabs.iterator();
		String[] ab = null;
		driver.switchTo().window(baseWindow);
		System.out.println("Switched to base window");
		while (it.hasNext() == true) {
			if (it.next() != baseWindow) {
				driver.switchTo().window(it.next().toString());
				System.out.println("Switched to new window");
			}
		}
		System.out.println(baseWindow);
		driver.switchTo().window(baseWindow);
		System.out.println("Switched to base window");
		driver.findElement(By.xpath("//*[contains(text(),'Open New Seperate')]")).click();
		driver.findElement(By.xpath("//*[@onclick='newwindow()']")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator windowIterator = tabs.iterator();
		//TakeScreenshot.takeSnapshot(driver, "TestWindowHandle");
		while (windowIterator.hasNext()) {
			System.out.println(windowIterator.next());
		}
		driver.switchTo().window(baseWindow);
        driver.quit();
		
	}
}