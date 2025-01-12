package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends CartPage {
    private final By byTitle = By.xpath("//span[text()='Checkout: Overview']");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isDisplayedOK() {
        return !getElements(byTitle).isEmpty();
    }
}
