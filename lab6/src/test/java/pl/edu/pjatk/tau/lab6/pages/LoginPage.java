package pl.edu.pjatk.tau.jbhsel.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by tp on 03.04.17.
 */
public class LoginPage extends WebDriverPage {

    public LoginPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
    	manage().deleteAllCookies();
        get("http://localhost:3000/authentication/signin");     
    }

    public void click(String linkText) {
        WebElement e = findElement(By.className(linkText));
        e.click();
        manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    
    public void insert(String linkText, String toInsert) {
        WebElement e = findElement(By.name(linkText));
        e.sendKeys(toInsert);
    }

    public String getResult() {
        String e = getPageSource();
        return e;
    }
}
