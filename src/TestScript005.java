import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestScript005 {
	@Test(dataProvider = "loginData")
	public void testCase05(String username, String password) throws Exception {

		System.setProperty("webdriver.firefox.driver","C:\\firefox\\geckodriver.exe");
		Thread.sleep(2000);	
    	WebDriver driver = new FirefoxDriver();
		String baseUrl = KeysExternal.BASE_URL;
		driver.get(baseUrl + "/V4/");
		Thread.sleep(2000);
		String actualBoxMsg;
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(2000);
	    try{
	       	Alert alt = driver.switchTo().alert();
			actualBoxMsg = alt.getText(); 
			alt.accept();				
			assertEquals(actualBoxMsg,KeysExternal.EXPECT_ERROR);
		} 
	    catch (NoAlertPresentException Ex){
	    	String pageText = driver.findElement(By.xpath("/html/body/table/tbody")).getText();
			assertTrue(pageText.contains(username.substring(0, 4)));
			assertTrue(pageText.contains(username.substring(4)));
			Thread.sleep(2000);	
        }
	    driver.manage().deleteAllCookies();
	    driver.close();
	}
	
	@DataProvider(name = "loginData")
	public Object[][] TestingData() throws Exception {
		
		return new Object[][] {
            { KeysExternal.USER_NAME, KeysExternal.USER_PASSWORD },
            { "invalid", "valid" },
            { "valid", "invalid" },
            { "invalid", "invalid" },
			{ KeysExternal.USER_NAME, KeysExternal.USER_PASSWORD }
    };
    
    /*
     * In this declaration, the size of the array is set explicitly using the dimensions (5 rows, 2 columns),
     *  and then each cell is assigned a value individually. 
     * This approach is useful when the data is not known at the time of the array declaration and needs to be populated dynamically. 
     * 
     * Object[][] data = new Object[5][2];
		// 1st row
		data[0][0] = KeysExternal.USER_NAME;
		data[0][1] = KeysExternal.USER_PASSWORD;
		//2nd row
		data[1][0] = "invalid";
		data[1][1] = "valid";
		//3rd row
		data[2][0] = "valid";
		data[2][1] = "invalid";
		//4th row
		data[3][0] = "invalid";
		data[3][1] = "invalid";
		//5th row
		data[4][0] = KeysExternal.USER_NAME;
		data[4][1] = KeysExternal.USER_PASSWORD;
		*/
    }
	
}
