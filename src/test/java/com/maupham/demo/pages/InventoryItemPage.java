package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryItemPage extends BasePage {
    private final By byName = By.xpath("//div[@data-test='inventory-item-name']");
    private final By byPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private final By byImage = By.cssSelector("img[class='inventory_details_img']");
    private final By byDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private final By byBackToProducts = By.id("back-to-products");

    public InventoryItemPage(WebDriver driver) {
        super(driver);
    }

    public void clickBackToProductsBtn() {
       clickElement(byBackToProducts);
    }

    public String getName() {
        return getText(byName);
    }

    public Double getPrice() {
        var price = Double.valueOf(getText(byPrice).substring(1));
        return price;
    }

    public String getImage() {
        return getElement(byImage).getAttribute("src");
    }

    public String getDescription() {
        return getText(byDescription);
    }

}
