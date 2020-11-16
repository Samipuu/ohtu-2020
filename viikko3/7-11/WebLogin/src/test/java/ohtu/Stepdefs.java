package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class Stepdefs {
    WebDriver driver;
    //WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPassword(String username, String password) {
        logInWith(username, password);
    }
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();  
    }
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void createValidNewUser(String user, String password) {
        createNewUser(user, password, password);
    }
    
    @Then("a new user is created")
    public void newUserCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @When("a too short username {string} and valid password {string} and matching password confirmation are entered")
    public void tooShortUsernameCreation(String user, String password) {
        createNewUser(user, password, password);
    }
    
    @Then("user is not created and error {string} is reported")
    public void userNotCreated(String error) {
        pageHasContent("Create username and give password");
        pageHasContent(error);        
    }
    
    @When("a valid username {string} and invalid password {string} with matching password confirmation are entered")
    public void validUsernameInvalidPasswordCreation(String user, String password) {
        createNewUser(user, password, password);
    }
    
    @When("a valid username {string} and valid password {string} and not matching password confirmation {string} are entered")
    public void validUsernameValidPasswordWithDifferentConfirmation(String user, String password, String confirmation) {
        createNewUser(user, password, confirmation);
    }
    
    @Given("user with username {string} with password {string} is successfully created")
    public void userSuccesfullyCreated(String user, String password) {
        newUserIsSelected();
        createNewUser(user, password, password);
    }
    
    @Given("user with username {string} and password {string} is unsuccessfully created")
    public void userUnsuccesfullyCreated(String user, String password) {
        newUserIsSelected();
        createNewUser(user, password, password);
    }
    
    @When("created username {string} with selected password {string} are entered")
    public void createdUserCanBeUsedToLogIn(String user, String password) {
        logInWith(user, password);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
    
    @Before
    public void setUp() {
        ChromeDriverManager.getInstance().version("86.0.4240.22").setup();
        driver = new ChromeDriver();
    }
    
    
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void createNewUser(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
