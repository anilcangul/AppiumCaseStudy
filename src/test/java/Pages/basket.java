package Pages;

import Tests.element;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class basket extends homePage {

    public basket(WebDriverWait webDriverWait, AndroidDriver<MobileElement> driver) {
        super(webDriverWait, driver);
    }


    public double getTheProductPrice(){

        //Sepetteki  price değerini replace ederek double formatında döndürür
        String price = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id(element.priceButton))).getText();

        String replace1 = price.replace("$","");
        String replace2 = replace1.replace(",",".");
        double doublePrice = Double.parseDouble(replace2);

        return doublePrice;
    }

    public void deletesAllProduct (){

        //Sepetteki tüm ürünleri siler
        int elementSize = getElementSizesbyXpath("//android.widget.TextView[@text='Remove']");
        int i =0;
        for (i=0; i<elementSize; i++){
            clickTheElement(element.removeButton,"id");
        }


    }

}
