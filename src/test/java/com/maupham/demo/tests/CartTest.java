package com.maupham.demo.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CartPage;
import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.ProductItem;

import org.testng.Assert;

public class CartTest extends  BaseTest {
    private final ThreadLocal<CartPage> _carPage = new ThreadLocal<>();
    private final ThreadLocal<InventoryPage> _inventoryPage = new ThreadLocal<>();

   @BeforeMethod
   public void beforeMethod() {
       login();
       _inventoryPage.set(new InventoryPage(getDriver()));
       _carPage.set(new CartPage(getDriver()));
   }
    
   @Test
   public void testCartCout() {
       var inventoryPage = _inventoryPage.get();
       var productItems = inventoryPage.getProductItems();
       for (ProductItem productItem : productItems) {
           productItem.clickAddToCartButton();
       }
       inventoryPage.clickCartIcon();
       var expectcount = _carPage.get().getCartCount();
       var cartItems = _carPage.get().getCartItems();
     
       while (!cartItems.isEmpty()) {
           cartItems.get(0).clickRemoveButton();
           expectcount--;
           cartItems = _carPage.get().getCartItems();
       }
       var actual = _carPage.get().getCartCount();
       Assert.assertEquals(expectcount, actual);
   }

   @AfterMethod
   public void afterMethod() {
       _carPage.remove();
        _inventoryPage.remove();
   }
    
}
