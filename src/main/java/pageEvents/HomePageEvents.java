package main.java.pageEvents;

import main.java.pageObjects.HomePageElements;
import main.java.utils.ElementFetch;
import org.openqa.selenium.interactions.Actions;
import test.java.BaseTest;

public class HomePageEvents {

    public void clickOnSignInButton() {
        BaseTest.logger.info("Clicking on sign in button");
        ElementFetch elementFetch = new ElementFetch();
        Actions action = new Actions(BaseTest.driver);
        action.moveToElement(elementFetch.getWebElement("XPATH", HomePageElements.helloSignInText)).perform();
        elementFetch.getWebElement("XPATH", HomePageElements.singInButton).click();
    }

}
