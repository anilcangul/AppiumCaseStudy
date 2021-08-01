package Pages;

import Tests.element;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Onboarding extends homePage {

    public Onboarding(WebDriverWait webDriverWait) {
        super(webDriverWait);
    }


    public Onboarding(WebDriverWait webDriverWait, AndroidDriver<MobileElement> driver) {
        super(webDriverWait, driver);
    }
    public void skipStartupScreen(){
        //Onboarding ekranını skip ederek geçer
        clickTheElement(element.next, "id");
    }


}
