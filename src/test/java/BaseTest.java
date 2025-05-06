package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public ExtentHtmlReporter extentHtmlReporter;
    public static ExtentReports extentReport;
    public static ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod() {
        extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports"+File.separator+"Automation Report"+ UUID.randomUUID() +".html");
        extentHtmlReporter.config().setEncoding("utf-8");
        extentHtmlReporter.config().setDocumentTitle("Automation Report");
        extentHtmlReporter.config().setReportName("Automation Test Results");
        extentHtmlReporter.config().setTheme(Theme.DARK);
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentHtmlReporter);
        extentReport.setSystemInfo("Automation Tester", "Pavan Kumar");
    }

    @BeforeMethod
    @Parameters(value = {"browserName"})
    public void beforeMethodMethod(String browserName, Method testMethod) {
        logger = extentReport.createTest(testMethod.getName());
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.amazonURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethodMethod(ITestResult testResult) {
        if(testResult.getStatus()==ITestResult.SUCCESS) {
            String methodName = testResult.getMethod().getMethodName();
            String logText = "Test Case :- "+ methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if(testResult.getStatus()==ITestResult.FAILURE) {
            String methodName = testResult.getMethod().getMethodName();
            String logText = "Test Case :- "+ methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }else if(testResult.getStatus()==ITestResult.SKIP) {
            String methodName = testResult.getMethod().getMethodName();
            String logText = "Test Case :- "+ methodName + " Skipped";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            logger.log(Status.SKIP, m);
        }
        driver.quit();

    @AfterTest
    public void afterTestMethod() {
        extentReport.flush();
    }

    public void setupDriver(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" +File.separator +"chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers" +File.separator +"geckodriver");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" +File.separator +"chromedriver");
            driver = new ChromeDriver();
        }
    }
}
