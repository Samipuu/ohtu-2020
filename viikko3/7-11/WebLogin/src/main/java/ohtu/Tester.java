package ohtu;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        ChromeDriverManager.getInstance().version("86.0.4240.22").setup();
        WebDriver driver = new ChromeDriver();
        WebElement element;
        Random r = new Random();
        driver.get("http://localhost:4567");
        
        sleep(2);
        /*
        //Login with correct username and password
        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
        */
        /*
        //Login with good username and bad password
        
        element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("bad");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
        */
        
        //Create new account and logout.

        element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("sami"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("pitkasalasana1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("pitkasalasana1");
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);

        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(3);
        
        driver.quit();
        
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
