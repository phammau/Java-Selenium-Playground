package com.maupham.demo.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CartPage;
import com.maupham.demo.pages.CheckoutStepOnePage;
import com.maupham.demo.pages.CheckoutStepTwoItem;
import com.maupham.demo.pages.CheckoutStepTwoPage;
import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.ProductItem;

public class CheckoutStepTwoTest extends BaseTest {
    private final ThreadLocal<CartPage> _cartPage = new ThreadLocal<>();
    private final ThreadLocal<CheckoutStepTwoPage> _checkoutStepTwoPage = new ThreadLocal<>();
    private final ThreadLocal<InventoryPage> _inventoryPage = new ThreadLocal<>();
    private final ThreadLocal<CheckoutStepOnePage> _checkoutStepOnePage = new ThreadLocal<>();

    @BeforeMethod
    public void beforeMethod(){
        login();
        _inventoryPage.set(new InventoryPage(getDriver()));
    }

    @Test
    public void testCheckoutTwo01(){
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems();

        for (ProductItem productItem : productItems) {
            productItem.clickAddToCartButton();
        }

        _inventoryPage.set(new InventoryPage(getDriver()));
        _inventoryPage.get().clickCartIcon();

        _cartPage.set(new CartPage(getDriver()));
        _cartPage.get().clickCheckoutButton();

        _checkoutStepOnePage.set(new CheckoutStepOnePage(getDriver()));
        _checkoutStepOnePage.get().autoFillAndProceedToStepTwo("ABC", "DFG", "123456E");

        _checkoutStepTwoPage.set(new CheckoutStepTwoPage(getDriver()));
        Assert.assertEquals(_cartPage.get().getCartCount(),  _checkoutStepTwoPage.get().getCartCount());
    }

    @Test
    public  void testCheckoutTwo02() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems();

        var expectedNames = new ArrayList<String>();
        var expectedPrices = new ArrayList<Double>();
        var expectedDescriptions = new ArrayList<String>();

        for (ProductItem productItem : productItems) {
            expectedNames.add(productItem.getName());
            expectedPrices.add(productItem.getPrice());
            expectedDescriptions.add(productItem.getDescription());
            productItem.clickAddToCartButton();
        }
        _inventoryPage.get().clickCartIcon();

        _cartPage.set(new CartPage(getDriver()));
        _cartPage.get().clickCheckoutButton();

        _checkoutStepOnePage.set(new CheckoutStepOnePage(getDriver()));
        _checkoutStepOnePage.get().autoFillAndProceedToStepTwo("ABC", "DFG", "123456E");
        _checkoutStepTwoPage.set(new CheckoutStepTwoPage(getDriver()));

        for (int i = 0; i < productItems.size(); i++) {
            var checkoutStepTwoPage = _checkoutStepTwoPage.get();
            var checkoutStepTwoItems = checkoutStepTwoPage.getCheckoutStepTwoItems();
            CheckoutStepTwoItem checkoutStepTwoItem = checkoutStepTwoItems.get(i);

            String actualName = checkoutStepTwoItem.getName();
            Double actualPrice = checkoutStepTwoItem.getPrice();
            String actualDescription = checkoutStepTwoItem.getDescription();

            Assert.assertEquals(expectedNames.get(i), actualName);
            System.out.println(expectedNames.get(i)+","+actualName);

            Assert.assertEquals(expectedDescriptions.get(i), actualDescription);
            Assert.assertEquals(expectedPrices.get(i), actualPrice);
        }
    }

    @Test
    public void testPriceTotals() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems();
        for (ProductItem productItem : productItems) {
            productItem.clickAddToCartButton();
        }
        
        _inventoryPage.set(new InventoryPage(getDriver()));
        _inventoryPage.get().clickCartIcon();

        _cartPage.set(new CartPage(getDriver()));
        _cartPage.get().clickCheckoutButton();

        _checkoutStepOnePage.set(new CheckoutStepOnePage(getDriver()));
        _checkoutStepOnePage.get().autoFillAndProceedToStepTwo("ABC", "DFG", "123456E");

        _checkoutStepTwoPage.set(new CheckoutStepTwoPage(getDriver()));

        var checkoutStepTwoPage = _checkoutStepTwoPage.get();
        var checkoutStepTwoItems = checkoutStepTwoPage.getCheckoutStepTwoItems();
        var count = 0.0;
        
        for (CheckoutStepTwoItem checkoutStepTwoItem : checkoutStepTwoItems) {
            count += checkoutStepTwoItem.getPrice();
        }
        var priceTotals = count;
        Assert.assertEquals( _checkoutStepTwoPage.get().getPriceTotals(),priceTotals);
    }

    @AfterMethod
    public void afterMethod() {
       _cartPage.remove();
       _checkoutStepTwoPage.remove();
       _inventoryPage.remove();
    }
}
