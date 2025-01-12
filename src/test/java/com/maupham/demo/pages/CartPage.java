package com.maupham.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
    private final By byCartItems = By.className("cart_item");
    private final By byContinueShoppingButton = By.id("continue-shopping");
    private final By byCheckoutButton = By.id("checkout");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItem> getCartItems() {
        List<WebElement> elements = getElements(byCartItems);
        if (elements.isEmpty()) {
            return new ArrayList<>();
        }

        List<CartItem> cartItems =  new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            CartItem cartItem = new CartItem(getDriver(), i);
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    public void clickContinueShoppingButton() {
        clickElement(byContinueShoppingButton);
    }

    public void clickCheckoutButton(){
        clickElement(byCheckoutButton);
    }
}
