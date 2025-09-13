package com.maupham.demo.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.maupham.demo.pages.CartPage;
import com.maupham.demo.pages.InventoryItemPage;
import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.ProductItem;


public class InventoryTest extends BaseTest {
    private final ThreadLocal<CartPage> _cartPage = new ThreadLocal<>();
    private final ThreadLocal<InventoryPage> _inventoryPage = new ThreadLocal<>();// su dung ThreadLocal de dam bao moi luong co mot InventoryPage rieng

    @BeforeMethod 
    public void beforeMethod() {
        login(); // dang nhap vao ung dung
        _inventoryPage .set(new InventoryPage(getDriver())); // khoi tao doi tuong InventoryPage
    } 

    @Test
    public void testAddToCart() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems(); // lay ds hien co tren trang
        var expectcount = 0; //kiem tra va xac minh so luong sp co trong gio hang la 0
        Assert.assertEquals(inventoryPage.getCartCount(),expectcount);

        //them tung sp vap gio hang va kiem tra so luong cap nhat
         for (var productItem : productItems){
            productItem.clickAddToCartButton();
            expectcount++;
            Assert.assertEquals(inventoryPage.getCartCount(),expectcount);
         }

        //xoa tung sp trong gio hang va kiem tra so luong cap nhat
        for (var productItem : productItems) {
            productItem.clickRemoveBtn();
            expectcount--;
            Assert.assertEquals(inventoryPage.getCartCount(),expectcount);
        } 
    }

    @Test
    public void testClickProductItem() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems();
        var softAssert = new SoftAssert();
       
        for (var productItem : productItems) {
            var expectedImage = productItem.getImage();
            var expectedName = productItem.getName();
            var expectedPrice = productItem.getPrice();
            var expectedDescription= productItem.getDescription();
           
            productItem.clickName();
            var inventoryItemPage = new InventoryItemPage(getDriver());

            var actualImage = inventoryItemPage.getImage();
            var actualName = inventoryItemPage.getName();
            var actualPrice = inventoryItemPage.getPrice();
            var actualDescription = inventoryItemPage.getDescription();

            softAssert.assertEquals(actualImage, expectedImage);
            softAssert.assertEquals(actualName, expectedName);
            softAssert.assertEquals(actualPrice, expectedPrice);
            softAssert.assertEquals(actualDescription, expectedDescription);
        
            inventoryItemPage.clickBackToProductsBtn();
        }
        softAssert.assertAll();
    }

    @Test
    public void testClickAddToCartButtonCartItem() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems();  //lay ds sp tu trang inventoryPage thong qua phuong thuc getProductItems().
        for (var i = 0; i < productItems.size(); i++) {
            var productItem = productItems.get(i);
            var expectedName = productItem.getName();
            var expectedPrice = productItem.getPrice();
            var expectedDescription = productItem.getDescription();

            productItem.clickAddToCartButton(); //add gio hang
            inventoryPage.clickCartIcon(); //click vao gio hang de kt

            _cartPage.set(new CartPage(getDriver()));
            var cartItems = _cartPage.get().getCartItems();
            var cartItem = cartItems.get(i);

            var actualName = cartItem.getName();
            var actualPrice = cartItem.getPrice();
            var actualDescription = cartItem.getDescription();
            
            Assert.assertEquals(actualName, expectedName);
            Assert.assertEquals(actualDescription, expectedDescription);
            Assert.assertEquals(actualPrice, expectedPrice);
           
            _cartPage.get().clickContinueShoppingButton();
        }
    }

    @Test
    public void testSortProductsByPriceLowToHigh() {
        var softAssert = new SoftAssert();
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems(); //lay ds sp ban dau
        
        var expected = new ArrayList<>(productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList())); // Tạo danh sách expected chứa giá của sản phẩm ban dau,ánh xạ (map) từng sản phẩm sang giá của nó,chuyển kết quả stream thành danh sách (tolist).

        inventoryPage.sortProductByPrice_LowToHight(); //sap xep sp tu thap den cao
        var actual = productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList()); // lay ds sau khi sap xep

        softAssert.assertFalse(expected.equals(actual)); //kiem tra ds ban dau va sau khi sap xep co khac nhau hay khong(phai dam bao khac nhau)

        Collections.sort(expected); //sap xep ds mong doi (expected) de lam chuan                                                            
        softAssert.assertTrue(expected.equals(actual));
        softAssert.assertAll();
    }

    @Test
    public void testSortProductsByPriceHighToLow() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems(); 
        var expected = new ArrayList<>(productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList())); // Tạo danh sách expected chứa giá của sản phẩm ban dau,ánh xạ (map) từng sản phẩm sang giá của nó,chuyển kết quả stream thành danh sách (tolist).

        inventoryPage.sortProductByPrice_HightToLow(); //sap xep tu thap den cao
        var actual = productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList()); 
       
        Assert.assertFalse(expected.equals(actual)); 
      
        Collections.sort(expected,Comparator.reverseOrder());                                                           
        
        Assert.assertTrue(expected.equals(actual));
    }
    
    @Test
    public void testSortProductsByNameAtoZ() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems();
        var expected = new ArrayList<>(productItems.stream().map(ProductItem::getName).collect(Collectors.toList())); 

        inventoryPage.sortProductByName_ZToA();                                                    
        var actual = productItems.stream().map(ProductItem::getName).collect(Collectors.toList());                  

        Assert.assertFalse(expected.equals(actual));                                                     

        Collections.sort(expected, Comparator.reverseOrder());  //sap xep giam trong Collections                                                              
        Assert.assertTrue(expected.equals(actual));
    }

    @AfterMethod
    public  void afterMethod() {
        _inventoryPage.remove();
    }
}

