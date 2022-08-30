package TestSwagsLab;

import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    static WebDriver driver;
    public static void setup(){
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

    }
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
    public static void verification(String locator , String expected){
        String actual = driver.findElement(By.xpath(locator)).getText();
        Assert.isTrue(actual.equals(expected),"Expected result does not match with Actual Result");
    }
    public static void login(){
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
    }
    public static void addToCart(){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
    }
    public static void checkout(){
        driver.findElement(By.name("checkout")).click();
    }
    public static void continueCheckout(){
        driver.findElement(By.name("firstName")).sendKeys("Ram");
        driver.findElement(By.name("lastName")).sendKeys("lal");
        driver.findElement(By.name("postalCode")).sendKeys("122334");
        driver.findElement(By.name("continue")).click();

    }
    public static void finish(){
        driver.findElement(By.name("finish")).click();
    }
    public static void main(String args[]) throws InterruptedException {
        setup();
        login();
        verification("//span[@class='title']","PRODUCTS");
        addToCart();
        verification("//span[@class='title']","YOUR CART");
        checkout();
        verification("//span[@class='title']","CHECKOUT: YOUR INFORMATION");
        continueCheckout();
        verification("//span[@class='title']","CHECKOUT: OVERVIEW");
        finish();
        verification("//span[@class='title']","CHECKOUT: COMPLETE!");
        closeBrowser();
    }

}
