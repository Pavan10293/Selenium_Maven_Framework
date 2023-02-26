package main.java.pageEvents;

import main.java.pageObjects.LoginPageElements;
import main.java.utils.ElementFetch;
import org.testng.Assert;
import test.java.BaseTest;

public class LoginPageEvents {

    public void verifyLoginPageIsOpened(){
        BaseTest.logger.info("Verifying that the login page is opened or not.");
        ElementFetch elementFetch = new ElementFetch();
        Assert.assertTrue(elementFetch.getListOfWebElements("XPATH", LoginPageElements.loginText).size()>0, "Login Page did not open");
    }

    public void enterEmailAddress() {
        BaseTest.logger.info("Entering email address");
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getWebElement("ID", LoginPageElements.emailAddress).sendKeys("abcd@efgh.com");
    }

}
