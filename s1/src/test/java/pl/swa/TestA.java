package pl.swa;

import static org.testng.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestA {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	//System.setProperty("webdriver.gecko.driver", "./vendor/geckodriver.exe");
    //driver = new FirefoxDriver();
	//System.setProperty("webdriver.ie.driver", "./vendor/IEDriverServer.exe");
    //DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
    //ieCapabilities.setCapability("nativeEvents", true);
	//driver = new InternetExplorerDriver(ieCapabilities);
	  
	System.setProperty("phantomjs.binary.path", "./vendor/phantomjs.exe");
	driver = new PhantomJSDriver();
	baseUrl = "http://www.szukajwarchiwach.pl/";
	//driver.manage().window().maximize();
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //driver = new HtmlUnitDriver(true);
  }

  @Test
  public void testUntitled() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.cssSelector("span")).click();
    driver.findElement(By.id("x_skan")).click();
    driver.findElement(By.name("_generic")).clear();
    driver.findElement(By.name("_generic")).sendKeys("Lublin");
    driver.findElement(By.cssSelector("input.submit.search_button")).click();
    driver.findElement(By.id("search_clear_button")).click();
    driver.findElement(By.cssSelector("input.search_button.submit")).click();
    //File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    //try {
    //	FileUtils.copyFile(scrFile, new File("C:\\selenium\\scr.png"));
    //} catch(Exception ex) {
    //
    //}
    
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
