package functionRegister;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tc_login {

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

		// Find elements
		WebElement btn_home_login = driver.findElement(By.xpath("//a[@href='/auth/login']"));

		btn_home_login.click();

		Thread.sleep(2000);
	}

	// Login successfully
	@Test(priority = 0, enabled = true)
	public void dn1_loginSuccessfully() throws InterruptedException {

		login("nguyenyen2003+15@gmail.com", "Yen12345");
		Thread.sleep(2000);
		btn_Login_click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Đăng nhập thành công')]")).isDisplayed());
	}

	// Login with wrong Email
	@Test(priority = 1, enabled = true)
	public void dn2_loginWithWrongEmail() throws InterruptedException {
		login("nguyenyen200@gmail.com", "Yen12345");
		Thread.sleep(2000);
		btn_Login_click();
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//div[contains(text(),'Tài khoản hoặc mật khẩu không chính xác. Vui lòng thử lại.')]"))
				.isDisplayed());
	}

	// Login with wrong Password
	@Test(priority = 2, enabled = true)
	public void dn3_loginWithWrongPassword() throws InterruptedException {
		login("nguyenyen2003+15@gmail.com", "Yen1234566666");
		Thread.sleep(2000);
		btn_Login_click();
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//div[contains(text(),'Tài khoản hoặc mật khẩu không chính xác. Vui lòng thử lại.')]"))
				.isDisplayed());
	}

	// Login with Empty Email
	@Test(priority = 3, enabled = true)
	public void dn4_loginWithEmptyEmail() throws InterruptedException {

		WebElement inputPassword = driver.findElement(By.id(":r1:"));
		inputPassword.sendKeys("Yen12345");
		Thread.sleep(1000);
		btn_Login_click();
		Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[1]/div[2]"))
				.isDisplayed());
	}

	// Login with Empty Password
	@Test(priority = 4, enabled = true)
	public void dn5_loginWithEmptyPassword() throws InterruptedException {

		WebElement inputPassword = driver.findElement(By.id(":r0:"));
		inputPassword.sendKeys("nguyenyen2003+15@gmail.com");
		Thread.sleep(1000);
		btn_Login_click();
		Assert.assertTrue(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[2]/form[1]/div[2]/div[2]"))
				.isDisplayed());
	}

	// Login with Invalid Email
	@Test(priority = 5, enabled = true)
	public void dn6_loginWithInvalidEmail() throws InterruptedException {
		login("nguyenyen2003+15gmail.com", "Yen12345");
		Thread.sleep(1000);
		btn_Login_click();
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//div[contains(text(),'Email không hợp lệ.')]"))
				.isDisplayed());
	}
	
	// Login with Password Has Only Numbers
	@Test(priority = 6, enabled = true)
	public void dn7_loginWithPasswordHasOnlyNumbers() throws InterruptedException {
		login("nguyenyen2003+15@gmail.com", "11111111");
		Thread.sleep(1000);
		btn_Login_click();
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//div[contains(text(),'Mật khẩu phải chứa 1 ký tự và 1 số')]"))
				.isDisplayed());
	}
	
	// Login with Password Has Only Alphabet
	@Test(priority = 7, enabled = true)
	public void dn8_loginWithPasswordHasOnlyAlphabet() throws InterruptedException {
		login("nguyenyen2003+15@gmail.com", "yennguyen");
		Thread.sleep(1000);
		btn_Login_click();
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//div[contains(text(),'Mật khẩu phải chứa 1 ký tự và 1 số')]"))
				.isDisplayed());
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {

		Thread.sleep(2000);
		driver.quit();

	}

}
