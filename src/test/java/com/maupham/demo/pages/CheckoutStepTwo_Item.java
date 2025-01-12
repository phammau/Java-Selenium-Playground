package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwo_Item extends CheckoutStepTwoPage {
    private final  By byName;
    private final  By byPrice;
    private final  By byDescription;
    private final  By byFinishButton;

    public CheckoutStepTwo_Item(WebDriver driver, int index) {
        super(driver);
        index++;
        byName = By.xpath("//div[@class='inventory_item_name']");
        byPrice = By.xpath("//div[@class='inventory_item_price']");
        byDescription = By.xpath("//div[@class='inventory_item_desc']");
        byFinishButton = By.id("finish");
    }
    
    public String getName() {
        return getText(byName);
    }
    
    public Double getPrice() {
        Double price = Double.valueOf(getText(byPrice).substring(1));
        return price;
    }

    public String getDescription() {
        return getText(byDescription);
    }

    public void clickFinishButton(){
        clickElement(byFinishButton);
    }

}
