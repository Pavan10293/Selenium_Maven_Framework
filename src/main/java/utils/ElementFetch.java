package main.java.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;

import java.util.List;

public class ElementFetch {

    public WebElement getWebElement(LocatorType locatorType, String identifierValue) {
        switch(locatorType){
            case ID:
                return BaseTest.driver.findElement(By.id(identifierValue));
            case CSS_SELECTOR:
                return BaseTest.driver.findElement(By.cssSelector(identifierValue));
            case TAGNAME:
                return BaseTest.driver.findElement(By.tagName(identifierValue));
            case XPATH:
                return BaseTest.driver.findElement(By.xpath(identifierValue));
            default :
                return null;
        }
    }

    public List<WebElement> getListOfWebElements(LocatorType locatorType, String identifierValue) {
        switch(locatorType){
            case ID:
                return BaseTest.driver.findElements(By.id(identifierValue));
            case CSS_SELECTOR:
                return BaseTest.driver.findElements(By.cssSelector(identifierValue));
            case TAGNAME:
                return BaseTest.driver.findElements(By.tagName(identifierValue));
            case XPATH:
                return BaseTest.driver.findElements(By.xpath(identifierValue));
            default :
                return null;
        }
    }

}


