package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoItem extends CheckoutStepTwoPage {
    private final  By byName;
    private final  By byPrice;
    private final  By byDescription;

    public CheckoutStepTwoItem(WebDriver driver, int index) {
        super(driver);
        index++;
        byName = By.xpath("//div[@class='cart_item']["+index+"]//div[@data-test='inventory-item-name']");
        byPrice = By.xpath("//div[@class='cart_item']["+index+"]//div[@data-test='inventory-item-price']");
        byDescription = By.xpath("//div[@class='cart_item']["+index+"]//div[@data-test='inventory-item-desc']");
    }
    
    public String getName() {
        return getText(byName);
    }
    
    public Double getPrice() {
        var price = Double.valueOf(getText(byPrice).replace("$", ""));
        return price;
    }

    public String getDescription() {
        return getText(byDescription);
    }
}
