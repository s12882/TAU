package pl.edu.pjatk.tau.lab6;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on loginpage page")
    public void userOnHelpdeskPage(){
        pages.loginpage().open();
    }

    @When("user inserts in $loginField a $login and inserts in $passwordField a $password and")
    public void userInsertLogin(String loginField, String login, String passwordField, String password) {
        pages.loginpage().insert(loginField, login);
        pages.loginpage().insert(passwordField, password);
    }
    
    @When("clicks $login button")
    public void userClicksLoginButton(String Link) {
        pages.loginpage().click(Link);
    }

    @Then("user see $option option")
    public void userGotLoggedIn(String option) {
        assertTrue(pages.loginpage().getResult().contains(option));
    }

    @Then("user still see $option option")
    public void userGotNotLoggedIn(String option) {
    	assertTrue(pages.loginpage().getResult().contains(option));
    }
}
