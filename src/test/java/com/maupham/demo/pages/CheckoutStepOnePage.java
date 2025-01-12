package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends CartPage {
    private final By byFirstName = By.id("first-name");
    private final By byLastName = By.id("last-name");
    private final By byZipPostalCode = By.id("postal-code");
    private final By byErrorCheckout = By.xpath("//div[@class ='error-message-container error']");
    private final By byTitleCheckout = By.xpath("//span[@class= 'title'][@data-test = 'title']");
    private final By byConTinue = By.id("continue");
    
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }
    
    public void  inputFirstName(String name){
        inputText(byFirstName, name);
    }

    public void  inputLastName(String name){
        inputText(byLastName, name);
    }

    public void  inputPostalCode(String name){
        inputText(byZipPostalCode, name);
    }

    public String getErrorCheckout(){
        return getText(byErrorCheckout);
    }

    public Boolean isDisplayOk(){
        return !getElements(byTitleCheckout).isEmpty();
    }
    
    public void clickContinue(){
        clickElement(byConTinue);
    }
    
    public void loginAutoCorect_CheckoutStep(String firstname, String lasttname, String postalname) {
        inputFirstName("ABC");
        inputLastName("DFG");
        inputPostalCode("postalCode");
        clickContinue();
    }
}
