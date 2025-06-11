package functionRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFile.WebAction;

public class tc_dky {

	public void registerAccount(String name, String email, String pw, String cfPw) {
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys(name);

		// Find input mail
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys(email);

		// Find input password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys(pw);

		// Find input confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys(cfPw);
	}

	public void btnSubmitRegister() throws InterruptedException {

		// Click submit
		WebElement btn_submit_dky = driver.findElement(By.cssSelector("button[type='submit']"));
		btn_submit_dky.click();
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

		// Find elements
		WebElement btn_dky1 = driver.findElement(By.xpath("//a[@href='/auth/sign-up']"));

		// WebElement btn_dky1 =
		// driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/header[1]/div[1]/div[3]/a[3]/span[1]"));

		btn_dky1.click();

		Thread.sleep(2000);
	}

	// Register successfully
	// @Test(priority = 0, enabled = true)
	public void dk1_registerSuccessfully() throws InterruptedException {

		// Fill all input
		registerAccount("Nguyen Thi Hai Yen", "nguyenyen2003+11@gmail.com", "Yen12345", "Yen12345");

		Thread.sleep(2000);

		btnSubmitRegister();

		// Check message register successfully
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Đăng ký tài khoản thành công!')]")).isDisplayed());
		System.out.println("Hiển thị đúng message đăng ký thành công");

		Thread.sleep(4000);

		// Check navigate to page login
		String expectedUrl = "https://nguyetviet.io.vn/auth/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	// Register fail when submit email already exist
	// @Test(priority = 1, enabled = true)
	public void dky2_registerByExistedMail() throws InterruptedException {

		// Fill all input
		registerAccount("Nguyen Thi Hai Yen", "nguyenyen2003+11@gmail.com", "Yen12345", "Yen12345");

		Thread.sleep(2000);

		// Click submit
		btnSubmitRegister();

		// Check message register successfully
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Email đã tồn tại')]")).isDisplayed());

		System.out.println("Hiển thị message Email đã tồn tại");

	}

	// Register with empty name
	//@Test(priority = 2, enabled = true)
	public void dk3_emptyName() throws InterruptedException {

		// Find input mail
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys("nguyenyen2003+11@gmail.com");

		// Find input password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys("Yen12345");

		// Find input confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Tên đăng nhập là bắt buộc')]")).isDisplayed());

	}

	// Register with empty Email
	//@Test(priority = 3, enabled = true)
	public void dk4_emptyEmail() throws InterruptedException {

		// Find input name
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys("Nguyen Thi Hai Yen");

		// Find input password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys("Yen12345");

		// Find input confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Email là bắt buộc')]")).isDisplayed());

	}

	// Register with empty Password
	//@Test(priority = 4, enabled = true)
	public void dk5_emptyPassword() throws InterruptedException {

		// Find input name
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys("Nguyen Thi Hai Yen");

		// Find input mail
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys("nguyenyen2003+11@gmail.com");

		// Find input confirm password
		WebElement inputConfirmPassword = driver.findElement(By.id(":r3:"));
		inputConfirmPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu là bắt buộc')]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu xác nhận không khớp')]")).isDisplayed());

	}

	// Register with empty Confirm Password
	//@Test(priority = 5, enabled = true)
	public void dk6_emptyPassword() throws InterruptedException {

		// Find input name
		WebElement inputName = driver.findElement(By.id(":r0:"));
		inputName.sendKeys("Nguyen Thi Hai Yen");

		// Find input mail
		WebElement inputMail = driver.findElement(By.id(":r1:"));
		inputMail.sendKeys("nguyenyen2003+11@gmail.com");

		// Find input password
		WebElement inputPassword = driver.findElement(By.id(":r2:"));
		inputPassword.sendKeys("Yen12345");

		// Click submit
		btnSubmitRegister();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Xác nhận mật khẩu là bắt buộc')]")).isDisplayed());

	}

	// Register with invalid mail
	@Test(priority = 6, enabled = true)
	public void dky7_InvalidMail() throws InterruptedException {

		// Fill all input
		registerAccount("Nguyen Thi Hai Yen", "nguyenyen2003gmail.com", "Yen12345", "Yen12345");

		Thread.sleep(2000);

		// Click submit
		//btnSubmitRegister();

		// Check message register successfully
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Email không hợp lệ.')]")).isDisplayed());

	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}
}
