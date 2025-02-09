package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutComplete extends BasePage {
    private final By byTitle;
    private final By byButtonBackHome;

    
    public CheckoutComplete(WebDriver driver) {
        super(driver);
        byTitle = By.xpath("//span[@class='title']");
        byButtonBackHome = By.xpath("//button[@name='back-to-products']");
    }

    public String getName() {
        return getText(byTitle);
    }

    public void clickButtonBackHome() {
        clickElement(byButtonBackHome);
    }

    public Boolean isDisplayOk() {
        return !getElements(byTitle).isEmpty();
    }
}