package amazon;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class StartApplication {

		private static AndroidDriver driver;
		public static void main(String[] args) throws MalformedURLException, InterruptedException {

			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/Apps/Amazon/");
			File app = new File(appDir, "in.amazon.mShop.android.shopping.apk");

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("deviceName", "Micromax A311");
			capabilities.setCapability("platformVersion", "4.4.2");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

			driver = new AndroidDriver(new URL("https://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
			StartApplication  start=new StartApplication();
			start.search_for_product("65-inch TV");
			start.user_verifies_the_details();
			start.user_adds_the_product_to_cart();
			start.navigate_to_the_cart_menu();
			start.verify_Cart();
			driver.quit();

	}

public void search_for_product(String Product) {
		if(Product.equals("65-inch TV")) {
		
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_SearchResultPage_OR.HomeSearch));
		
		Assert.assertTrue(Amazon_SearchResultPage_OR.HomeSearch.isDisplayed());
		
		Amazon_SearchResultPage_OR.HomeSearch.click();
			try {
				
				Amazon_CartPage_OR.ClearSearch.click();

			}
			catch(Exception E) {
				
				System.out.println("Searching for the First Time");
			}
	
		
		Amazon_SearchResultPage_OR.HomeSearch.sendKeys(data.Product);
		
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_SearchResultPage_OR.SearchDropDown));
		
		Amazon_SearchResultPage_OR.SearchDropDown.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_SearchResultPage_OR.ResultsCount));
		
		}
	}
public void user_verifies_the_details() throws MalformedURLException, InterruptedException {
		
		
		String ProductDetailsPage = Amazon_ProductPage_OR.ProdcutName.getText();
		Log.info("Verify the Choosen Product is same as TV or not");
		Assert.assertEquals(ProductDetailsPage, ProductName);
		System.out.println("The Name of the Choosen Prodcut is :"+ProductDetailsPage);
		Log.info("Get the Price of ChoosenProduct");
		String ProductPrice = Amazon_ProductPage_OR.Price.getText();
		System.out.println("The Price of the Choosen Prodcut is: "+ProductPrice);
		
//		CF.scrollToText("From the manufacturer", driver);
//		Assert.assertTrue(Amazon_OR.FromTheManufacturer.isDisplayed());
		
	}
	
	public void user_adds_the_product_to_cart() throws MalformedURLException, InterruptedException {
		Log.info("Add the Procut to Cart by Scrolling to Add Cart Section");
		Thread.sleep(4000);
		CF.scrollToText("Add to Cart", driver);
//		wait.until(ExpectedConditions.elementToBeClickable(Amazon_ProductPage_OR.AddToCart));
//		Amazon_ProductPage_OR.AddToCart.click();
		Log.info("Waiting for the Element" + Amazon_ProductPage_OR.AddedToCart);
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_ProductPage_OR.AddedToCart));
		Log.info("Verify the presence of the " + Amazon_ProductPage_OR.AddedToCart + "in current Page");
		Assert.assertTrue(Amazon_ProductPage_OR.AddedToCart.isDisplayed());
	}
	
	
	public void navigate_to_the_cart_menu() {
		Log.info("Waiting for the Element" + Amazon_HomePage_OR.AmazonLogo);
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_HomePage_OR.AmazonLogo));
		Log.info("Click the Element " + Amazon_HomePage_OR.AmazonLogo);
		Amazon_HomePage_OR.AmazonLogo.click();
		Log.info("Waiting for the Element" + Amazon_CartPage_OR.CartMenu);
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_CartPage_OR.CartMenu));
		Log.info("Click the Element " + Amazon_CartPage_OR.CartMenu);
		Amazon_CartPage_OR.CartMenu.click();
		Log.info("Waiting for the Element" + Amazon_CartPage_OR.ProceedToBuy);
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_CartPage_OR.ProceedToBuy));
	}
	
	
	public void verify_Cart() {
		Log.info("Waiting for the Element" + Amazon_CartPage_OR.SubTotal);
		wait.until(ExpectedConditions.elementToBeClickable(Amazon_CartPage_OR.SubTotal));
		String ProductCartPage = Amazon_ProductPage_OR.ProdcutName.getText();
		Log.info("Verify the Expected Element is displayd on Current Page");
		Assert.assertTrue(Amazon_ProductPage_OR.ProdcutName.isDisplayed());
		Log.info("Verify the Product is TV or not");
		Assert.assertTrue("The product is not present in the Cart", ProductCartPage.contains(data.tv));
	}
	
	/**
	 * Final Step on Test Case Execution : Terminating the App from the User Session
	 * @throws Throwable
	 */
	
	public void user_closes_the_app() throws Throwable {
		Log.warn("Closing the App");
		driver.closeApp();
		
	}


}