package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {

//        ChromeDriverManager.getInstance().setup();
//        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new HtmlUnitDriver();
        System.setProperty("webdriver.chrome.driver", "/home/mihamaki/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        element.submit();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("ukkonooa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        element.submit();

        sleep(2);

        element = driver.findElement(By.linkText("back to home"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        Random random = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("ukko" + random.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("nooa");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("nooa");
        element = driver.findElement(By.name("signup"));
        element.submit();

        sleep(2);

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(3);

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
