package tc_website;

import static org.testng.Assert.assertEquals;

import java.util.List;

import javax.sound.midi.Track;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class tc_cartManagement {
	
	public void login(String mail, String pw) {
		// Find and fill name
		WebElement inputEmail = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[1]/div[1]/div[1]/input[1]"));
		inputEmail.sendKeys(mail);

		// Find and fill email
		WebElement inputPassword = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[2]/div[1]/div[1]/input[1]"));
		inputPassword.sendKeys(pw);
	}

	public void btn_Login_click() throws InterruptedException {
		WebElement btnLoginElement = driver
				.findElement(By.cssSelector("button[type='submit'] span[class='text-inherit']"));
		btnLoginElement.click();
		Thread.sleep(2000);
	}
	
	WebDriver driver = null;
	
	@BeforeTest
	public void beforeTest() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Chromedriver\\chromedriver.exe");

		driver = new ChromeDriver();

		// 1 - Maximize browser
		driver.manage().window().maximize();

		// 2 - Navigate to URL
		driver.navigate().to("https://nguyetviet.io.vn");

		// Wait web load
		Thread.sleep(2000);
	}
	
	//Add cart logout
	@Test(priority = 0,enabled = true)
	public void addCartLogout() throws InterruptedException {
		
		WebElement btn_home_product = driver.findElement(By.xpath("//a[@href='/products']"));
		btn_home_product.click();
		Thread.sleep(2000);

	     //Hover (move to element)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement product = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
	    actions.moveToElement(product).perform();
		Thread.sleep(1000);
		
		
		//click button ViewDetail
		WebElement btnaddCart = driver.findElement(By.xpath("//div[3]//div[1]//div[2]//div[1]//button[1]"));
		btnaddCart.click();
		Thread.sleep(1000);
		
		
		WebElement messageError = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]"));
		String txtMessageError = messageError.getText();
		System.out.print(txtMessageError);
		Assert.assertEquals(txtMessageError, "Bạn chưa đăng nhập. Vui lòng đăng nhập để tiếp tục.");
		Thread.sleep(2000);
		
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']"));
		btn_closeMessage.click();
		Thread.sleep(1000);
		

	
		//add cart from product detail
	/*	JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement product = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
	    actions.moveToElement(product).perform();
		Thread.sleep(1000);
		
		WebElement btnViewDetailProduct = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/button[3]"));
		btnViewDetailProduct.click();
		Thread.sleep(1000);
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.tagName("h1")));
		Thread.sleep(2000);
		
		WebElement btnaddCart = driver.findElement(By.xpath("//span[contains(text(),'THÊM VÀO GIỎ HÀNG')]"));
		btnaddCart.click();
		Thread.sleep(1000);
		
		WebElement messageError = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]"));
		String txtMessageError = messageError.getText();
		System.out.print(txtMessageError);
		Assert.assertEquals(txtMessageError, "Bạn chưa đăng nhập. Vui lòng đăng nhập để tiếp tục.");
	*/
	}
	
	//View cart without products
	@Test(priority = 1,enabled = true)
	public void gh1_viewEmptyCart() throws InterruptedException {
		
		WebElement btn_home_login = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/a[2]/span[1]/*[name()='svg'][1]"));
		btn_home_login.click();
		Thread.sleep(2000);
		
		login("nguyenyen2003+4@gmail.com", "Yen12345");
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);

		// Find elements
		WebElement btn_home_cart = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/div[1]/button[1]/span[1]"));
		btn_home_cart.click();
		Thread.sleep(2000);
		
		WebElement btn_cart = driver.findElement(By.xpath("//span[contains(text(),'Giỏ hàng')]"));
		btn_cart.click();
		Thread.sleep(2000);
		
		WebElement messageEmptyCart = driver.findElement(By.cssSelector(".text-center.text-gray-500"));
		String actualMessageString = messageEmptyCart.getText();
		String expectedMessageString = "Giỏ hàng của bạn đang trống";
		Assert.assertEquals(actualMessageString, expectedMessageString);
	}
	
	//Add product to cart
	@Test(priority = 2,enabled = true)
	public void gh2_addCart() throws InterruptedException {
		
		WebElement btn_product = driver.findElement(By.xpath("//span[contains(text(),'XEM THÊM SẢN PHẨM')]"));
		btn_product.click();
		Thread.sleep(2000);

		// Hover (move to element)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement product = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product);
		Thread.sleep(1000);

		Actions actions = new Actions(driver);
		actions.moveToElement(product).perform();
		Thread.sleep(1000);

		// click button add Cart
		WebElement btnViewDetailProduct = driver.findElement(By.xpath("//div[3]//div[1]//div[2]//div[1]//button[1]"));
		btnViewDetailProduct.click();
		Thread.sleep(1000);
		WebElement messagElement = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]"));
		String messageString = messagElement.getText();
		Assert.assertEquals(messageString, "Thêm sản phẩm vào giỏ hàng thành công");
		Thread.sleep(6000);

		 
		
		//Add second product
		
		// Hover (move to element)
	/*	WebElement product2 = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[6]/div[1]/div[2]/img[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", product2);
		Thread.sleep(1000);

		actions.moveToElement(product2).perform();
		Thread.sleep(1000);

		// click button add Cart
		WebElement btnViewDetailProduct2 = driver.findElement(By.xpath("//body[1]/div[1]/div[2]/main[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[2]/div[6]/div[1]/div[2]/div[1]/button[1]"));
		btnViewDetailProduct2.click();
		Thread.sleep(1000);
		WebElement messagElement2 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]"));
		String messageString2 = messagElement2.getText();
		Assert.assertEquals(messageString2, "Thêm sản phẩm vào giỏ hàng thành công");
		Thread.sleep(6000);
	*/	
	}
	
	//View cart has product
	@Test(priority = 3,enabled = true)
	public void gh4_viewCartHasProduct() throws InterruptedException {
		Thread.sleep(3000);
		// Find elements
		WebElement btn_home_cart = driver.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/div[1]/button[1]"));
		btn_home_cart.click();
		Thread.sleep(2000);

		WebElement btn_cart = driver.findElement(By.xpath("//span[contains(text(),'Giỏ hàng')]"));
		btn_cart.click();
		Thread.sleep(2000);
		
	     //Hover (move to element)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement cart = driver.findElement(By.xpath("//h3[contains(text(),'Giỏ hàng')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", cart);
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
	    actions.moveToElement(cart).perform();
		Thread.sleep(1000);

		WebElement totalElement = driver.findElement(By.cssSelector("div[class='ml-2 text-emerald']"));
		String total = totalElement.getText();
		
		Assert.assertNotEquals(total,"0 đ", "Giỏ hàng không có sản phẩm sau khi thêm!");
		Thread.sleep(2000);
	}
	
	//Delete all products
	@Test(priority = 4, enabled = true)
	public void gh5_deleteProduct() throws InterruptedException {
		WebElement btn_removeElement = driver.findElement(
				By.xpath("/html[1]/body[1]/div[1]/div[2]/main[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/button[1]"));
		btn_removeElement.click();
		Thread.sleep(1000);
		WebElement btn_confirmDelete = driver.findElement(By.xpath("//span[contains(text(),'Xác nhận')]"));
		btn_confirmDelete.click();
		Thread.sleep(1000);
		WebElement messgElement = driver
				.findElement(By.xpath("//div[contains(text(),'Xóa sản phẩm khỏi giỏ hàng thành công')]"));
		String messageString = messgElement.getText();
		Assert.assertEquals(messageString, "Xóa sản phẩm khỏi giỏ hàng thành công");
	}
	
	
	@AfterTest
	public void afterTest() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
