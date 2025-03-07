package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogInShouldFailTest {

  WebDriver driver;

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  }

  @AfterClass
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void testLoggingIntoApplication() throws InterruptedException {
    WebElement username = driver.findElement(By.name("username"));
    username.sendKeys("InvalidUser"); // Input invalid credentials
    WebElement password = driver.findElement(By.name("password"));
    password.sendKeys("InvalidPass123");

    driver.findElement(By.tagName("button")).click();
    Thread.sleep(2000);

    // Check for the presence of an error message (more reliable than asserting on title)
    WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(), 'Invalid')]"));
    Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid login!");
  }
}
