package Pages;

import Tests.element;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class homePage {

    public WebDriverWait webDriverWait;
    public AndroidDriver<MobileElement> driver;

    public homePage(WebDriverWait webDriverWait, AndroidDriver<MobileElement> driver) {
        this.webDriverWait = webDriverWait;
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    public homePage(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;

    }

    public int getElementSizesbyXpath(String elementClassName){

        //Gönderilen lacator ismine sahip elementlerin sayısını döner
        List<MobileElement> elementsByClassName = driver.findElements(By.xpath(elementClassName));
        System.out.println(elementsByClassName.toString());
        return elementsByClassName.size();

    }

    public void isHomepageLoadCheck() throws Exception{
        int a = getElementSizesbyXpath(element.homePageProductCardView);
        System.out.println("Product size : " + a);

        //Ürün kartı sayısı 0 ise hata fırlatır, sayfanın yüklenmediğini gösterir
        if(a == 0){
            throw new Exception("HomePage is not loaded!");
        }
    }

    public void clickTheElement(String Element, String type){
        //Gönderilen elementi türüne göre click fonk. çalıştırır
        switch (type){
            case "id":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.id(Element))).click();
                break;
            case "class":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.className(Element))).click();
                break;
            case "xpath":
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(Element))).click();
                break;
        }

    }
    public void clickTheTextElement(String element){
        //Gönderilen Text'i içeren element'e click yapar
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//android.widget.TextView[@text='"+element+"']"))).click();

    }

    public void clickTheProduct(int index){
        //Ürün listesinde gönderilen index değerine göre click yapar
        List<MobileElement> elements = driver.findElements(By.xpath(element.homePageProductCardView));
        MobileElement element =  elements.get(index);
        element.click();

    }

    public String getTextElement (String element){
        //Element'in Text'ini döndürür
                String xpathText = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath(element))).getText();
                return xpathText;

    }

    public  boolean verifyElementSelected(String element ){

        //Gönderilem elementin seçili olduğu doğrular
        MobileElement babycareElement = (MobileElement) driver.findElement(By.xpath(element));
        boolean isSelected = babycareElement.isSelected();
        return  isSelected;

    }

    public void sendKeysTheElement(String Element, String Text){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.className(Element))).sendKeys(Text);

    }


}

