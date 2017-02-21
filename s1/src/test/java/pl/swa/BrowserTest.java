package pl.swa;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.beust.testng.TestNG;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class BrowserTest {
	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void f() {
		driver.get("http://www.google.pl/");
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("coœ tam");
		// driver.findElement(By.id("lst-ib")).submit();
		Actions action = new Actions(driver);
		action.keyDown(Keys.ENTER).perform();

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains("Szukaj w Google"));

		Assert.assertEquals(driver.getTitle(), "coœ tam - Szukaj w Google");
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./vendor/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
