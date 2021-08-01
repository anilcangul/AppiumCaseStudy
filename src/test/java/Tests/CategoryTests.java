package Tests;
import Pages.Onboarding;
import Pages.homePage;
import Pages.productDetail;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners
public class CategoryTests {

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
        webDriverWait = new WebDriverWait(driver, 12);
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
    public void openTheLeftMenu () throws Exception {

        homePage  home = new homePage(webDriverWait,driver);

        home.clickTheElement(element.openLeftMenu,"xpath");

        //Menu'nun açıldığı kullanıcı isminin varlığıyla doğrulanır
        String userName = home.getTextElement(element.userName);
        System.out.println("User Name: "+userName);
        Assert.assertEquals(userName,"Sample User");
    }

    @Test (dependsOnMethods = {"openTheLeftMenu"})
    public void selectTheBabyCare () throws Exception {

        homePage  home = new homePage(webDriverWait,driver);

        home.clickTheElement(element.categoryBabyCare,"xpath");

        //Baby Care sayfasının seçili olduğu doğrulanır
        Boolean verify = home.verifyElementSelected(element.clickableBabyCare);
        System.out.println(verify);

    }

    @Test (dependsOnMethods = {"selectTheBabyCare"})
    public void selectProduct () throws Exception {

        homePage  home = new homePage(webDriverWait,driver);

        //3. ürün index methodu ile seçilir
        home.clickTheProduct(2);

    }

    @Test(dependsOnMethods = {"selectProduct"})
    public void checkTheProductDetail () throws Exception {

        productDetail  detail = new productDetail(webDriverWait,driver);

        //Ürün fiyatını değişkene tanımlar
        double price = detail.getTheProductPrice();

        //Ürün fiyatını double olarak tanımlar
        double productDetailPrice= Math.round(price*100)/100.0d;
        System.out.println("Product Detail Price: " + productDetailPrice );

        //Ürün fiyatını kontrol eder
        Assert.assertEquals(productDetailPrice,1.98);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Sayfanın varlığı kontrol edilir
        detail.isHomepageLoadCheck();

    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
