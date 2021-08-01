package Pages;

import Tests.element;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class productDetail extends  homePage{

    public productDetail(WebDriverWait webDriverWait, AndroidDriver<MobileElement> driver) {
        super(webDriverWait, driver);
    }

    public void addProductToBasket(int count){

        //Ürünü gönderilen sayı kadar sepete ekler
        int i = 0;
        for (i=0; i<count; i++ ){
        clickTheTextElement("ADD TO CART");
        }

    }

    public double getTheProductPrice(){

        //Detail sayfasındaki price değerini replace ederek double formatında döndürür
        String price = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(element.detailPrice))).getText();

        String replace1 = price.replace("$","");
        String replace2 = replace1.replace(",",".");
        double doublePrice = Double.parseDouble(replace2);

        return doublePrice;
    }

}
