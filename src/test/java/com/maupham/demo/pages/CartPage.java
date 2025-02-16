package com.maupham.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By byCartItems = By.className("cart_item");
    private final By byContinueShoppingButton = By.id("continue-shopping");
    private final By byCheckoutButton = By.xpath("//button[@data-test='checkout']");
    private final By byCart = By.xpath("//span[@data-test='shopping-cart-badge']");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItem> getCartItems() {
        var elements = getElements(byCartItems);
        if (elements.isEmpty()) {
            return new ArrayList<>();
        }

        var cartItems =  new ArrayList<CartItem>();
        for (int i = 0; i < elements.size(); i++) {
            CartItem cartItem = new CartItem(getDriver(), i);
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    public void clickContinueShoppingButton() {
        clickElement(byContinueShoppingButton);
    }

    public void clickCheckoutButton() {
        clickElement(byCheckoutButton);
    }

    public int getCartCount() {
        String count = getElement(byCart).getText();
        if (count.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(count);
    }
}

