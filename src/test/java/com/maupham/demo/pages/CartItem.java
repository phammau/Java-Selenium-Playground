package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartItem extends CartPage {
    private final By byName;
    private final By byPrice;
    private final By byDescription;
    private final By byRemoveButton;

    public CartItem(WebDriver driver, int index) {
        super(driver);
        index++;
        byName = By.xpath("//div[@class='cart_item'][" + index + "]//div[@class='inventory_item_name']");
        byDescription = By.xpath("//div[@class='cart_item'][" + index + "]//div[@class='inventory_item_desc']");
        byPrice = By.xpath("//div[@class='cart_item'][" + index + "]//div[@class='inventory_item_price']");
        byRemoveButton = By.xpath("//div[@class='cart_item'][" + index + "]//button[text()='Remove']");
    }

    public String getName() {
        return getText(byName);
    }
    
    public Double getPrice() {
        var price = Double.valueOf(getText(byPrice).substring(1));
        return price;
    }

    public String getDescription() {
        return getText(byDescription);
    }

    public void clickRemoveButton() {
        clickElement(byRemoveButton);
    }
}
