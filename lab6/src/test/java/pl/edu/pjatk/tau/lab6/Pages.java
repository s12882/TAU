package pl.edu.pjatk.tau.lab6;

import org.jbehave.web.selenium.WebDriverProvider;

import pl.edu.pjatk.tau.lab6.pages.LoginPage;


public class Pages {

    private WebDriverProvider driverProvider;

    //Pages -- moze byc ich kilka
    private LoginPage loginPage;

    public Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    public LoginPage loginpage() {
        if (loginPage == null) {
        	loginPage = new LoginPage(driverProvider);
        }
        return loginPage;
    }
}
