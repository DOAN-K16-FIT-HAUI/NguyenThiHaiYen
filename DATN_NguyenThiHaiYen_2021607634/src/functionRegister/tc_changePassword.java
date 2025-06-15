package functionRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tc_changePassword {

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

	public void changePassword(String currentPw, String newPw, String cfNewPw) {
		// Find and fill current Password
		WebElement inputCurrentPw = driver.findElement(By.id(":r9:"));
		inputCurrentPw.sendKeys(currentPw);

		// Find and fill new Password
		WebElement inputNewPassword = driver.findElement(By.id(":ra:"));
		inputNewPassword.sendKeys(newPw);
		
		// Find and fill confirm new Password
		WebElement inputCfNewPassword = driver.findElement(By.id(":rb:"));
		inputCfNewPassword.sendKeys(cfNewPw);
	}

	WebDriver driver = null;

	@BeforeClass
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

		// Find elements
		WebElement btn_home_profile = driver.findElement(By.xpath("//a[@href='/profile/me']"));

		btn_home_profile.click();

		Thread.sleep(1000);

		WebElement btn_navigatePageChangePasswordElement = driver
				.findElement(By.xpath("//span[contains(text(),'Đổi mật khẩu')]"));
		btn_navigatePageChangePasswordElement.click();
		Thread.sleep(2000);

	}

	@Test(priority = 0, enabled = true)
	public void mk1_changePasswordSuccessfull() throws InterruptedException {
		
		changePassword("Yen12345", "Yen54321", "Yen54321");
		WebElement btn_submitChangePw = driver.findElement(By.xpath("(//span[@class='text-inherit'][contains(text(),'Lưu thay đổi')])[2]"));
		btn_submitChangePw.click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu đã được thay đổi thành công.')]")).isDisplayed());

		WebElement btn_closeMessage = driver.findElement(By.xpath("//button[@aria-label='close']//*[name()='svg']"));
		btn_closeMessage.click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 1, enabled = true)
	public void mk2_wrongCurrentPassword() {
		
		changePassword("Yen12345", "Yen54321", "Yen54321");
		WebElement btn_submitChangePw = driver.findElement(By.xpath("(//span[@class='text-inherit'][contains(text(),'Lưu thay đổi')])[2]"));
		btn_submitChangePw.click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu hiện tại không đúng. Vui lòng kiểm tra và thử lại.')]")).isDisplayed());

	}

	@AfterClass
	public void afterMethod() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
