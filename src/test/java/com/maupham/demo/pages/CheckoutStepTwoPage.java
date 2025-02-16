package com.maupham.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends CartPage {
    private final By byTitle = By.xpath("//span[text()='Checkout: Overview']");
    private final By byCartItems = By.className("cart_item");
    private final By byFinishButton = By.id("finish");
    private final By byCart = By.xpath("//span[@data-test='shopping-cart-badge']");
    private final By byPriceTotals = By.xpath("//div[@class='summary_subtotal_label']");


    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isDisplayedOK() {
        return !getElements(byTitle).isEmpty();
    }

    public int getCountCheckoutStepTwo() {
        var count = getElement(byCart).getText();
        if("".equals(count)){
            return 0;
        }
        return Integer.parseInt(count);
    }

    public List<CheckoutStepTwoItem> getCheckoutStepTwoItems() {
        var elements = getElements(byCartItems);
        if(elements.isEmpty()) {
            return new ArrayList<>();
        }
        var checkoutStepTwo_Items = new ArrayList<CheckoutStepTwoItem>();
        for (int i = 0; i < elements.size(); i++) {
            CheckoutStepTwoItem checkoutStepTwoItem = new CheckoutStepTwoItem(driver, i);
            checkoutStepTwo_Items.add(checkoutStepTwoItem);
        }
        return checkoutStepTwo_Items;
    }

    public void clickFinishButton() {
        clickElement(byFinishButton);
    }

    @SuppressWarnings("override")
    public int getCartCount() {
        var count = getElement(byCart).getText();
        if("".equals(count)) {
            return 0;
        }
        return Integer.parseInt(count);
    }

    public Double getPriceTotals() {
        var price = Double.valueOf(getText(byPriceTotals).replace("Item total: $", ""));
        return price;
    }
}
