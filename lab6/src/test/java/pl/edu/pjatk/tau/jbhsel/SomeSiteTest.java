package pl.edu.pjatk.tau.jbhsel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

// UWAGA -- przerobilem dla phantomjs -- powinno dzialac na pracowni PJATK
public class SomeSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"D:/Programs/PhantomJS/phantomjs-2.1.1-windows/bin/phantomjs.exe"
		);
		driver = new PhantomJSDriver(caps);
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}

	@Test
	public void loginToPage() throws IOException, InterruptedException {
		driver.get("http://localhost:3000");

		assertFalse(driver.getPageSource().contains("Sign in using your social accounts"));
		File screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/bss.1.png"));
		
		driver.findElement(By.className("navbar-toggle")).click();
		driver.findElement(By.xpath("//*[@id='sign-in']")).click();
		
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/bss.2.png"));
		
		
		driver.findElement(By.name("usernameOrEmail")).sendKeys("whooles");
		driver.findElement(By.name("password")).sendKeys("Vfrcbv-2008");
		
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/bss.3.png"));
		
		driver.findElement(By.className("btn-primary")).click();
		
	
		assertTrue(driver.getPageSource().contains("Signout"));
		Thread.sleep(250);
		screenshot =
				((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/bss.4.png"));
	
			
	}
	
}
