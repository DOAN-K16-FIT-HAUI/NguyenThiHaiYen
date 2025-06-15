package functionRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class tc_logout {
	
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
		driver.navigate().to("https://nguyetviet.io.vn/auth/login");

		// Wait web load
		Thread.sleep(2000);
		
		login("nguyenyen2003+15@gmail.com", "Yen12345");
		btn_Login_click();
		Thread.sleep(1000);
		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(1000);
		
		driver.navigate().to("https://nguyetviet.io.vn/profile/me");
		
		Thread.sleep(1000);
	}




	@Test(priority = 0, enabled = true)
	public void dx1_logoutSuccessfully() throws InterruptedException {
		
		WebElement btn_logout = driver.findElement(By.xpath("//span[contains(text(),'Đăng xuất')]"));
		btn_logout.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Đăng xuất thành công')]")).isDisplayed());
		
	}
	
	@AfterMethod
	public void afterMethod() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
