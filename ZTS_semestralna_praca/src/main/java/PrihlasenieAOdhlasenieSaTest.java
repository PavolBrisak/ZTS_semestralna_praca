import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
public class PrihlasenieAOdhlasenieSaTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void prihlaseniesaaodhlaseniesa() {
    driver.get("https://www.martinus.sk/");
    driver.manage().window().maximize();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"))).click();

    {
      WebElement element = driver.findElement(By.cssSelector(".swiper-slide-next > a > img"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.cssSelector(".header-user__name")).click();
    driver.findElement(By.id("email")).sendKeys("mizukage295@gmail.com");
    driver.findElement(By.id("email")).sendKeys(Keys.ENTER);
    WebElement hesloElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("heslo")));
    hesloElement.sendKeys("zts2023sem");
    driver.findElement(By.id("heslo")).sendKeys(Keys.ENTER);
    assertThat(driver.findElement(By.cssSelector(".header-user__name")).getText(), is("Test"));
    driver.findElement(By.cssSelector(".header-user__name")).click();
    driver.findElement(By.linkText("Odhlásiť")).click();
    assertThat(driver.findElement(By.cssSelector(".header-user__name")).getText(), is("Prihlásiť"));
  }
}
