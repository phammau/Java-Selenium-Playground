package com.maupham.demo.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CartPage;
import com.maupham.demo.pages.CheckoutStepOnePage;
import com.maupham.demo.pages.CheckoutStepTwoPage;
import com.maupham.demo.pages.CheckoutStepTwo_Item;
import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.ProductItem;

public class Checkout_StepTwoTest extends BaseTest {
    private WebDriver driver;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private InventoryPage inventoryPage;
    
    public Checkout_StepTwoTest () {}

    @BeforeMethod
    public void BeforeMethod(){
        driver = setUpDriver();
        login(driver);
        inventoryPage= new InventoryPage(driver);
    }

    @Test
    public void testCheckoutTwo01(){
        List<ProductItem> productItems = inventoryPage.getProductItems();
        for (ProductItem productItem : productItems) {
            productItem.clickAddToCartButton();
        }
        inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();

        cartPage= new CartPage(driver);
        cartPage.clickCheckoutButton();

        checkoutStepOnePage= new CheckoutStepOnePage(driver);
        checkoutStepOnePage.loginAutoCheckoutStepTwo("ABC", "DFG", "123456E");

        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        Assert.assertEquals(cartPage.getCartCount(), checkoutStepTwoPage.getCartCount());
    }

    @Test
    public  void testCheckoutTwo02() {
        List<ProductItem> productItems = inventoryPage.getProductItems();
        List<String> expectedNames = new ArrayList<>();
        List<Double> expectedPrices = new ArrayList<>();
        List<String> expectedDescriptions = new ArrayList<>();

        for (ProductItem productItem : productItems) {
            expectedNames.add(productItem.getName());
            expectedPrices.add(productItem.getPrice());
            expectedDescriptions.add(productItem.getDescription());
            productItem.clickAddToCartButton();
        }
        inventoryPage.clickCartIcon();

        cartPage =  new CartPage(driver);
        cartPage.clickCheckoutButton();

        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.loginAutoCheckoutStepTwo("ABC", "DFG", "123456E");
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);

        for (int i = 0; i < productItems.size(); i++) {
            List<CheckoutStepTwo_Item> checkoutStepTwo_Items = checkoutStepTwoPage.getCheckoutStepTwo_Items();
            CheckoutStepTwo_Item checkoutStepTwo_Item = checkoutStepTwo_Items.get(i);

            String actualName = checkoutStepTwo_Item.getName();
            Double actualPrice = checkoutStepTwo_Item.getPrice();
            String actualDescription = checkoutStepTwo_Item.getDescription();

            Assert.assertEquals(expectedNames.get(i), actualName);
            System.out.println(expectedNames.get(i)+","+actualName);

            Assert.assertEquals(expectedDescriptions.get(i), actualDescription);
            Assert.assertEquals(expectedPrices.get(i), actualPrice);
        }
    }

    @Test
    public void testPriceTotals() {
        List <ProductItem> productItems = inventoryPage.getProductItems();
        for (ProductItem productItem : productItems) {
            productItem.clickAddToCartButton();
        }
        
        inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();

        cartPage= new CartPage(driver);
        cartPage.clickCheckoutButton();

        checkoutStepOnePage= new CheckoutStepOnePage(driver);
        checkoutStepOnePage.loginAutoCheckoutStepTwo("ABC", "DFG", "123456E");

        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);

        List<CheckoutStepTwo_Item> checkoutStepTwo_Items = checkoutStepTwoPage.getCheckoutStepTwo_Items();
        double count = 0;
        
        for (CheckoutStepTwo_Item checkoutStepTwo_Item : checkoutStepTwo_Items) {
            count += checkoutStepTwo_Item.getPrice();
        }
        System.out.println(count);
        double priceTotals = count;

        System.out.println(checkoutStepTwoPage.getPriceTotals());

        Assert.assertEquals(checkoutStepTwoPage.getPriceTotals(),priceTotals);
    }

    @AfterMethod
    public void AfterMethod() {
        driver.close();
    }


}
