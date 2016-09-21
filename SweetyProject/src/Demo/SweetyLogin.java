package Demo;

import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SweetyLogin {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
	// Initialize Browser in Firefox
		WebDriver driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		driver.get("http://damp-sands-8561.herokuapp.com/");
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys("reachranjitha@gmail.com");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("codetheoryio");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
	// Verify page title
		String actualTitle=driver.findElement(By.xpath("//h2[contains(.,'Welcome to Sweety')]")).getAttribute("innerHTML");
		String expectedTitle="Welcome to Sweety";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Verify Home page "+ actualTitle);
		
		
	// Verify Successful sign
		String actualSigninMsg=driver.findElement(By.xpath("//div[@class='alert alert-info fade in']")).getText();
		String expectedSigninMsg="Signed in successfully.";
		Assert.assertEquals(actualSigninMsg, expectedSigninMsg);
		System.out.println("Verify Successful login "+ actualSigninMsg);
	
	// Verify Calendar display
		driver.findElement(By.xpath("//a[text()='Levels']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		Thread.sleep(2000L);
		
	    // Verify for current Year displayed
		DateFormat df=new SimpleDateFormat("yyyy");
		Date d=new Date();
		String currentyear=df.format(d);
		System.out.println("Current Date "+ currentyear);
		
		Select year=new Select(driver.findElement(By.xpath("//*[@id='entry_recorded_at_1i']")));
		String actualYear= year.getFirstSelectedOption().getText();
		Assert.assertEquals(actualYear, currentyear);
		System.out.println("Verify year "+ actualYear);
	
		       // Verify for current Month displayed
				DateFormat df1=new SimpleDateFormat("MMMM");
				Date d1=new Date();
				String currentmonth=df1.format(d1);
				System.out.println("Current Month "+ currentmonth);
				
				Select month=new Select(driver.findElement(By.xpath("//*[@id='entry_recorded_at_2i']")));
				String actualMonth= month.getFirstSelectedOption().getText();
				Assert.assertEquals(actualMonth, currentmonth);
				System.out.println("Verify Month "+ actualMonth);
		
			   // Verify for current date displayed
				DateFormat df2=new SimpleDateFormat("dd");
				Date d2=new Date();
				String currentdate=df2.format(d2);
				System.out.println("Current Date "+ currentdate);
				
				Select date=new Select(driver.findElement(By.xpath("//*[@id='entry_recorded_at_3i']")));
				String actualDate= date.getFirstSelectedOption().getText();
				//String expectedSigninMsg="Signed in successfully.";
				Assert.assertEquals(actualDate, currentdate);
				System.out.println("Verify Date "+ actualDate);
				Thread.sleep(6000);
				
	// Enter BloodSugar Levels			
	WebElement element = (new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='entry_level']")));
	
	//driver.findElement(By.xpath("//input[@id='entry_level']")).sendKeys("11");
	//driver.findElement(By.xpath("//input[@value='Submit']")).click();
	
	//Verify for multiple entries
	
	String[] array1 = { "2", "4", "5", "6", "10" };
	try{
	for (int i = 0; i<=4; i++)
	{
		//driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		WebElement element1 = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='entry_level']")));
		
		
		Select year1=new Select(driver.findElement(By.xpath("//select[@id='entry_recorded_at_1i']")));
		year1.selectByValue("2015");
		Select month1=new Select(driver.findElement(By.xpath("//*[@id='entry_recorded_at_2i']")));
		month1.selectByValue("10");
		Select day1=new Select(driver.findElement(By.xpath("//*[@id='entry_recorded_at_3i']")));
		day1.selectByValue("7");
		
		driver.findElement(By.xpath("//input[@id='entry_level']")).sendKeys(array1[i]);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
	}
		// Verify Maximum entry
	}catch (Throwable t) {
				String actualErrorMsg=driver.findElement(By.xpath("//div[@class='alert alert-warning fade in']")).getText();
				String expectedErrornMsg="Maximum entries reached for this date.";
				Assert.assertEquals(actualErrorMsg, expectedErrornMsg);
				System.out.println(" Maximum entry error Msg "+ actualErrorMsg);
	}
	
	
	//Verify Monthly Reports
	driver.findElement(By.xpath("//a[text()='Reports']")).click();
	driver.findElement(By.xpath("//a[text()='Monthly']")).click();
	
	String actualmonthlyMsg=driver.findElement(By.xpath("//h3[contains(text(),'Monthly Report as of for')]")).getText();
	String expectedmonthlyMsg="Monthly Report as of for "+currentmonth;
	Assert.assertEquals(actualmonthlyMsg, expectedmonthlyMsg);
	System.out.println(" Display of monthly Report:"+ actualmonthlyMsg);
	
	//Verify Columns in Reports
	String col1=driver.findElement(By.xpath("//th[text()='Min']")).getText();
	System.out.println(" Report contains:"+ col1);
	String col2=driver.findElement(By.xpath("//th[text()='Max']")).getText();
	System.out.println(" Report contains:"+ col2);
	String col3=driver.findElement(By.xpath("//th[text()='Avg']")).getText();
	System.out.println(" Report contains:"+ col3);
	
	
	//Verify Daily Reports
	driver.findElement(By.xpath("//a[text()='Reports']")).click();
	driver.findElement(By.xpath("//a[text()='Daily']")).click();
	driver.close();
		
	}

}
