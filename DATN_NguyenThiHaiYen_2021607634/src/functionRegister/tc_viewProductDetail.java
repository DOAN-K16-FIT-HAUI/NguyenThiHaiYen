package tc_website;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tc_viewProductDetail {
	public void login(String mail, String pw) {
		// Find and fill name
		WebElement inputEmail = driver.findElement(By.id(":r0:"));
		inputEmail.sendKeys(mail);

		// Find and fill email
		WebElement inputPassword = driver.findElement(By.id(":r1:"));
		inputPassword.sendKeys(pw);
	}

	public void btn_Login_click() throws InterruptedException {
		WebElement btnLoginElement = driver
				.findElement(By.cssSelector("button[type='submit'] span[class='text-inherit']"));
		btnLoginElement.click();
		Thread.sleep(2000);
	}

	WebDriver driver = null;

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Chromedriver\\chromedriver.exe");

		driver = new ChromeDriver();

		// 1 - Maximize browser
		driver.manage().window().maximize();

		// 2 - Navigate to url
		driver.navigate().to("https://nguyetviet.io.vn");

		// Wait web load
		Thread.sleep(2000);

	}

	//View product logout
	@Test(priority = 0, enabled = true)
	public void ct1_viewProductLogout() throws InterruptedException {
		
		WebElement btn_home_product = driver.findElement(By.xpath("//a[@href='/products']"));
		btn_home_product.click();
		Thread.sleep(2000);
		
		String txtNameOutsite = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[3]/h3[1]")).getText();
		
	     //Hover (move to element)
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement product = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
	    actions.moveToElement(product).perform();
		Thread.sleep(1000);
		
		
		//click button ViewDetail
		WebElement btnViewDetailProduct = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/button[3]"));
		btnViewDetailProduct.click();
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.tagName("h1")));
		
		String txtNameDetail = driver.findElement(By.tagName("h1")).getText();
		Thread.sleep(2000);

		Assert.assertEquals(txtNameOutsite,txtNameDetail,"Xem chi tiết sản phẩm failed");
	}
	
	//View product login
	@Test
	public void ct2_viewProductLogin() throws InterruptedException {
		
		WebElement btn_home_login = driver.findElement(By.xpath("//a[@href='/auth/login']"));
		btn_home_login.click();
		Thread.sleep(2000);
		
		login("nguyenyen2003+7@gmail.com", "Yen12345");
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);

		// Find elements
		WebElement btn_home_product = driver.findElement(By.xpath("//a[@href='/products']"));
		btn_home_product.click();
		Thread.sleep(1000);
		
		String txtNameOutsite = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[3]/h3[1]")).getText();
		
	     //Hover (move to element)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement product = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		Thread.sleep(1000);
		
		Actions actions = new Actions(driver);
	    actions.moveToElement(product).perform();
		Thread.sleep(1000);
		
		
		//click button ViewDetail
		WebElement btnViewDetailProduct = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/button[3]"));
		btnViewDetailProduct.click();

		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.tagName("h1")));
		
		String txtNameDetail = driver.findElement(By.tagName("h1")).getText();
		Thread.sleep(2000);

		Assert.assertEquals(txtNameOutsite,txtNameDetail,"Xem chi tiết sản phẩm failed");
	}
	@AfterMethod
	public void afterMethod() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
