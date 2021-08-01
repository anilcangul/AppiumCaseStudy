package Tests;
import Pages.Onboarding;
import Pages.homePage;
import Pages.productDetail;
import Pages.basket;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;

public class baseTest {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait webDriverWait;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName","Pixel 4");
            capabilities.setCapability("platformVersion","8.0");
            capabilities.setCapability("deviceName","emulator-5554");
            capabilities.setCapability("appPackage", "com.allandroidprojects.getirsample");
            capabilities.setCapability("appActivity","com.allandroidprojects.getirsample.startup.SplashActivity");

        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        webDriverWait = new WebDriverWait(driver, 12); //driver'a max 12 saniye bekleme süresi tanımlar
    }
    @Test
    public void passOnboarding () throws Exception {

        Onboarding onboarding = new Onboarding(webDriverWait,driver);

        onboarding.skipStartupScreen();
    }
    @Test (dependsOnMethods = {"passOnboarding"})
    public void checkTheHomePage () throws Exception {

        homePage  home = new homePage(webDriverWait,driver);

        home.isHomepageLoadCheck();
    }

    @Test (dependsOnMethods = {"checkTheHomePage"})
    public void changesCategory () throws Exception {

        homePage  home = new homePage(webDriverWait,driver);

        home.clickTheTextElement("WATER");

    }

    @Test (dependsOnMethods = {"changesCategory"})
    public void selectProduct () throws Exception {

        homePage  home = new homePage(webDriverWait,driver);

        home.clickTheProduct(0);

    }

    @Test(dependsOnMethods = {"selectProduct"})
    public void checkTheProductDetail () throws Exception {

        homePage  home = new homePage(webDriverWait,driver);
        productDetail  detail = new productDetail(webDriverWait,driver);
        basket  basket = new basket(webDriverWait,driver);

        //Seçilem ürünü 2 kez sepete ekler
        int productCount = 2;
        detail.addProductToBasket(productCount);

        //Ürünün birim fiyatını ürün sayısı ile çarpar
        double price = detail.getTheProductPrice()*productCount;

        //Ürüm fiyatını double'a dönüştürür
        double productDetailPrice= Math.round(price*100)/100.0d;
        System.out.println("Product Detail Price: " + productDetailPrice );

        //Ürün ismini değişkene tanımlar
        String detailName = detail.getTextElement(element.detailName);
        System.out.println("Product Name: " + detailName );
        //Detail sayfasından geri homepage'e döndürür
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        home.clickTheElement(element.basket, "xpath");

        //Sepet tutarını değişkene tanımlar
        double productBasketPrice = basket.getTheProductPrice();

        System.out.println("Basket Price: " + productBasketPrice);

        //Sepetteki ürn ismini değişkene tanımlar
        String basketName = basket.getTextElement(element.basketName);
        System.out.println("Product Basket Name: " + basketName);

        //Detay sayfasındaki ürünün fiyatı ve ismi sepet sayfasında doğrulanır.
        Assert.assertEquals(productBasketPrice,productDetailPrice);
        Assert.assertEquals(detailName,basketName);

    }

    @Test(dependsOnMethods = {"checkTheProductDetail"})
    public void deletesProductsFromBasket() throws Exception{
        basket  basket = new basket(webDriverWait,driver);

        basket.deletesAllProduct();


    }

    @AfterTest
    public void teardown(){

        driver.quit();


    }
}

