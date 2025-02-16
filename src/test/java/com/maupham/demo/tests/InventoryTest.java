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
    private final ThreadLocal<InventoryPage> _inventoryPage = new ThreadLocal<>();// Sử dụng ThreadLocal để đảm bảo mỗi luồng có một thể hiện InventoryPage riêng

    @BeforeMethod 
    public void beforeMethod() {
        login(); //Đăng nhập vào ứng dụng
        _inventoryPage .set(new InventoryPage(getDriver())); // Khởi tạo đối tượng InventoryPage
    } 

    @Test
    public void testAddToCart() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems(); //Lấy danh sách sản phẩm hiện có trên trang
        var expectcount = 0; //Kiểm tra và xác minh số lượng sản phẩm trong giỏ hàng ban đầu là 0
        Assert.assertEquals(inventoryPage.getCartCount(),expectcount);

        //Thêm từng sản phẩm vào giỏ hàng và kiểm tra số lượng cập nhật
         for (var productItem : productItems){
            productItem.clickAddToCartButton();
            expectcount++;
            Assert.assertEquals(inventoryPage.getCartCount(),expectcount);
         }

        //Xoa từng sản phẩm trong giỏ hàng và kiểm tra số lượng cập nhật
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
        var productItems = inventoryPage.getProductItems();  //Lấy danh sách các sản phẩm từ trang inventoryPage thông qua phương thức getProductItems().
        for (var i = 0; i < productItems.size(); i++) {
            var productItem = productItems.get(i);
            var expectedName = productItem.getName();
            var expectedPrice = productItem.getPrice();
            var expectedDescription = productItem.getDescription();

            productItem.clickAddToCartButton(); //add gio hang
            inventoryPage.clickCartIcon(); //click vao gio hang để kt

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
        var productItems = inventoryPage.getProductItems(); //lấy danh sách sp ban đầu
        
        var expected = new ArrayList<>(productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList())); // Tạo danh sách expected chứa giá của sản phẩm ban dau,ánh xạ (map) từng sản phẩm sang giá của nó,chuyển kết quả stream thành danh sách (tolist).

        inventoryPage.sortProductByPrice_LowToHight(); //sắp xếp sản phẩm theo giá từ thấp đến cao.
        var actual = productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList()); // Lấy danh sách giá sau khi thực sắp xếp

        softAssert.assertFalse(expected.equals(actual)); //Kiểm tra danh sách ban đầu và sau khi sắp xếp có khác nhau không(phai đảm bảo khác nhau)

        Collections.sort(expected); //Sắp xếp danh sách mong đợi (expected) để làm chuẩn                                                               
        softAssert.assertTrue(expected.equals(actual));
        softAssert.assertAll();
    }

    @Test
    public void testSortProductsByPriceHighToLow() {
        var inventoryPage = _inventoryPage.get();
        var productItems = inventoryPage.getProductItems(); 
        var expected = new ArrayList<>(productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList())); // Tạo danh sách expected chứa giá của sản phẩm ban dau,ánh xạ (map) từng sản phẩm sang giá của nó,chuyển kết quả stream thành danh sách (tolist).

        inventoryPage.sortProductByPrice_HightToLow(); //sắp xếp sản phẩm theo giá từ thấp đến cao.
        var actual = productItems.stream().map(ProductItem::getPrice).collect(Collectors.toList()); // Lấy danh sách giá sau khi thực sắp xếp
       
        Assert.assertFalse(expected.equals(actual)); //Kiểm tra danh sách ban đầu và sau khi sắp xếp có khác nhau không(phai đảm bảo khác nhau)
      
        Collections.sort(expected,Comparator.reverseOrder()); //Sắp xếp danh sách mong đợi (expected) để làm chuẩn                                                               
        
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

        Collections.sort(expected, Comparator.reverseOrder());  //sap xếp giảm trong Collections                                                              
        Assert.assertTrue(expected.equals(actual));
    }

    @AfterMethod
    public  void afterMethod() {
        _inventoryPage.remove();
    }
}

