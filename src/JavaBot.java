import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaBot {
	
	public static void pageLoad(String id, WebDriver chromeDriver) {
		WebDriverWait pageWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
		pageWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(id)));
		
		// alternate option for page load
//		try {
//			Thread.sleep(2000); // similar to chromeDriver.wait()
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) {
		// chrome driver
		System.setProperty("Webdriver.chrome.driver", "C:\\chromedriver.exe");
		
		// err 404 work-around
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable notifications");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);	
		WebDriver chromeDriver = new ChromeDriver(options);
		
		// visit automationintesting.online
		String URL = "https://automationintesting.online/";
		chromeDriver.get(URL);
		
		// clear introduction banner
		WebElement bannerButton = chromeDriver.findElement(By.xpath("//*[@id=\"collapseBanner\"]/div/div[3]/div[2]/button"));
		bannerButton.click();
		
		// submission contact form elements
		pageLoad("name", chromeDriver);
		WebElement formName = chromeDriver.findElement(By.id("name"));
		WebElement formEmail = chromeDriver.findElement(By.id("email"));
		WebElement formPhone = chromeDriver.findElement(By.id("phone"));
		WebElement formSubject = chromeDriver.findElement(By.id("subject"));
		WebElement formMessage = chromeDriver.findElement(By.id("description"));
		WebElement formSubmit = chromeDriver.findElement(By.id("submitContact"));
		// enter and submit form
		formName.sendKeys("Test McTesterson");
		formEmail.sendKeys("testyTester@testing.com");
		formPhone.sendKeys("15845554854");
		formSubject.sendKeys("Very Interested");
		formMessage.sendKeys("'Hello! I am very interested in booking a room at your B&B for my vacation this summer. Would you be able to tell me more about your rates? Also are dogs permitted during our stay? If so, are there any breed restrictions?");
		formSubmit.click();
		
		// visit admin panel
		WebElement adminPanel = chromeDriver.findElement(By.xpath("//*[@id=\"footer\"]/div/p/a[5]"));
		adminPanel.click();
		
		// admin loginS
		WebElement adminUsername = chromeDriver.findElement(By.id("username"));
		WebElement adminPassword = chromeDriver.findElement(By.id("password"));
		WebElement adminLoginButton = chromeDriver.findElement(By.id("doLogin"));
		adminUsername.sendKeys("admin");
		adminPassword.sendKeys("password");
		adminLoginButton.click();
		
		// room management
		pageLoad("roomName", chromeDriver);
		WebElement roomNumb = chromeDriver.findElement(By.id("roomName"));
		Select roomType = new Select(chromeDriver.findElement(By.id("type")));
		Select roomAccessiblity = new Select(chromeDriver.findElement(By.id("accessible")));
		WebElement roomPrice = chromeDriver.findElement(By.id("roomPrice"));
		WebElement roomWifi = chromeDriver.findElement(By.id("wifiCheckbox"));
		WebElement roomRefreshments = chromeDriver.findElement(By.id("refreshCheckbox"));
		WebElement roomTv = chromeDriver.findElement(By.id("tvCheckbox"));
		WebElement roomSafe = chromeDriver.findElement(By.id("safeCheckbox"));
		WebElement roomViews = chromeDriver.findElement(By.id("viewsCheckbox"));
		WebElement roomCreate = chromeDriver.findElement(By.id("createRoom"));
		// checkbox array
		WebElement[] roomCheckboxes = {roomWifi, roomRefreshments, roomTv, roomSafe, roomViews};
		
		roomNumb.sendKeys("402");
		roomType.selectByVisibleText("Suite");
		roomAccessiblity.selectByVisibleText("true");
		roomPrice.sendKeys("450");
		for(int i=0; i<roomCheckboxes.length; i++) {
			roomCheckboxes[i].click();
		}
		roomCreate.click();
		
		chromeDriver.close();
	}
}
