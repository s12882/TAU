package pl.edu.pjatk.tau.lab5;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class MySiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		ChromeOptions chromeOptions= new ChromeOptions();
		chromeOptions.setBinary("D:\\Programs/Google/Chrome/Application/chrome.exe");
		System.setProperty("webdriver.chrome.driver", "/Users/Андрей/Downloads/chromedriver/chromedriver.exe");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage() throws InterruptedException{
		driver.get("http://localhost/pro/WebAdverts/test.php");
		Thread.sleep(2500);
		
		element = driver.findElement(By.id("logo"));
		assertNotNull(element);
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);
		
		try {
			FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/MainPage.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void loginPage() throws InterruptedException{
		driver.get("http://localhost/pro/WebAdverts/login.php");
		Thread.sleep(2500);
		
		assertTrue(driver.getPageSource().contains("Please, type your IDs to log:"));
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/LoginPage.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	

	@Test
	public void Wronglogin() throws InterruptedException{
		driver.get("http://localhost/pro/WebAdverts/login.php");
		Thread.sleep(2000);
		
		WebElement id = driver.findElement(By.name("username"));
		WebElement pass = driver.findElement(By.name("password"));
		WebElement login = driver.findElement(By.id("log_but"));
		
		id.sendKeys("wrongLogin");
	    pass.sendKeys("wrongPassword");
	    login.click();
	        
	    assertTrue(driver.getPageSource().contains("The username or password you entered are not good."));
	    Thread.sleep(2500);
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/WrongLogin.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void logOutTest() throws InterruptedException{
		driver.get("http://localhost/pro/WebAdverts/test.php");
		Thread.sleep(1000);
		
		WebElement logout = driver.findElement(By.id("Logout"));
		logout.click();
		
	    assertTrue(driver.getPageSource().contains("You have successfully been logged out."));
	    Thread.sleep(1000);
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/Logout.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void sessionTest() throws InterruptedException{
		driver.get("http://localhost/pro/WebAdverts/login.php");
		Thread.sleep(1000);
		
		WebElement id = driver.findElement(By.name("username"));
		WebElement pass = driver.findElement(By.name("password"));
		WebElement login = driver.findElement(By.id("log_but"));
		
		id.sendKeys("admin");
	    pass.sendKeys("vfrcbv");
	    login.click();
	        
	    WebElement toMain = driver.findElement(By.linkText("Main Page"));
	    Thread.sleep(1000);
	    
	    toMain.click();
	    
	    assertTrue(driver.getPageSource().contains("Logged as: admin"));
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);

		try {
			FileUtils.copyFile(screenshot, new File("/Users/Андрей/Tmp/LoggedIn.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
