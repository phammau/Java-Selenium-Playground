package com.maupham.demo.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CartItem;
import com.maupham.demo.pages.CartPage;
import com.maupham.demo.pages.InventoryItemPage;
import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.ProductItem;


public class InventoryTest extends BaseTest {
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private InventoryItemPage inventoryItemPage;
    private WebDriver driver;
   
    public InventoryTest() {}

    @BeforeMethod 
    public void BeforeMethod() {
        driver = setUpDriver(); // Khởi tạo WebDriver
        UserLogin(driver); //Đăng nhập vào ứng dụng
        inventoryPage = new InventoryPage(driver); // Khởi tạo đối tượng InventoryPage
    } 
//->pass
    @Test
    public void Test_Addtcart() {
        List<ProductItem> productItems = inventoryPage.getProductItems(); //Lấy danh sách sản phẩm hiện có trên trang
        int expectcount = 0; //Kiểm tra và xác minh số lượng sản phẩm trong giỏ hàng ban đầu là 0
        Assert.assertEquals(inventoryPage.getCartCount(),expectcount);

        //Thêm từng sản phẩm vào giỏ hàng và kiểm tra số lượng cập nhật
         for (ProductItem productItem : productItems){
            productItem.clickAddToCartButton();
            expectcount++;
            Assert.assertEquals(inventoryPage.getCartCount(),expectcount);
         }

        //Xoa từng sản phẩm trong giỏ hàng và kiểm tra số lượng cập nhật
        for (ProductItem productItem : productItems) {
            productItem.clickRemoveBtn();
            expectcount--;
            Assert.assertEquals(inventoryPage.getCartCount(),expectcount);
        } 
    }

    @Test
    public void test_ClickProductItem(){
         List<ProductItem> productItems = inventoryPage.getProductItems();
       
        for (ProductItem productItem : productItems) {
            String expecttedImage = productItem.getImage();
            String expecttedName = productItem.getName();
            Double expecttedPrice = productItem.getPrice();
            String expecttedDescription= productItem.getDescription();
           
            productItem.clickName();
            inventoryItemPage = new InventoryItemPage(driver);

            String actualImage = inventoryItemPage.getImage();
            String actualName = inventoryItemPage.getName();
            Double actualPrice = inventoryItemPage.getPrice();
            String actualDescription = inventoryItemPage.getDescription();

            Assert.assertEquals(actualImage,expecttedImage);
            Assert.assertEquals(actualName,expecttedName);
            Assert.assertEquals(actualPrice,expecttedPrice);
            Assert.assertEquals(actualDescription,expecttedDescription);
        
            inventoryItemPage.clickBackToProductsBtn();
        }
    }

    @Test
    public void test_ClickAddtocartdBtn_CartItem(){
        List<ProductItem> productItems = inventoryPage.getProductItems();  //Lấy danh sách các sản phẩm từ trang inventoryPage thông qua phương thức getProductItems().
        for (int i = 0; i < productItems.size(); i++) {
            ProductItem productItem = productItems.get(i);
            String expectedName = productItem.getName();
            Double expectedPrice = productItem.getPrice();
            String expectedDescription = productItem.getDescription();

            productItem.clickAddToCartButton(); //add gio hang
            inventoryPage.clickCartIcon(); //click vao gio hang để kt

            cartPage = new CartPage(driver);
            List<CartItem> cartItems = cartPage.getCartItems();
            CartItem cartItem = cartItems.get(i);

            String actualName = cartItem.getName();
            Double actualPrice = cartItem.getPrice();
            String actualDescription = cartItem.getDescription();
            
            Assert.assertEquals(actualName, expectedName);
            Assert.assertEquals(actualDescription, expectedDescription);
            Assert.assertEquals(actualPrice, expectedPrice);
           
            cartPage.clickContinueShoppingButton();
        }
    }

    @Test
    public void testSortProductsByPriceLowToHigh(){
        List<ProductItem> productItems = inventoryPage.getProductItems(); //lấy danh sách
        
        List<Double> expected = new ArrayList<>(productItems.stream().map(ProductItem::getPrice).toList()); // Tạo danh sách expected chứa giá của sản phẩm ban dau,ánh xạ (map) từng sản phẩm sang giá của nó,chuyển kết quả stream thành danh sách (tolist).

        inventoryPage.sortProductByPrice_LowToHight(); //sắp xếp sản phẩm theo giá từ thấp đến cao.
        List<Double> actual = productItems.stream().map(ProductItem::getPrice).toList(); // Lấy danh sách giá sau khi thực sắp xếp

        Assert.assertFalse(expected.equals(actual)); //Kiểm tra danh sách ban đầu và sau khi sắp xếp có khác nhau không(phai đảm bảo khác nhau)

        Collections.sort(expected); //Sắp xếp danh sách mong đợi (expected) để làm chuẩn                                                               
        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void testSortProductsByPriceHighToLow(){
        List<ProductItem> productItems = inventoryPage.getProductItems(); 
        List<Double> expected = new ArrayList<>(productItems.stream().map(ProductItem::getPrice).toList()); // Tạo danh sách expected chứa giá của sản phẩm ban dau,ánh xạ (map) từng sản phẩm sang giá của nó,chuyển kết quả stream thành danh sách (tolist).
       
        inventoryPage.sortProductByPrice_HightToLow(); //sắp xếp sản phẩm theo giá từ thấp đến cao.
        List<Double> actual = productItems.stream().map(ProductItem::getPrice).toList(); // Lấy danh sách giá sau khi thực sắp xếp
        System.out.println(actual);
        Assert.assertFalse(expected.equals(actual)); //Kiểm tra danh sách ban đầu và sau khi sắp xếp có khác nhau không(phai đảm bảo khác nhau)
        System.out.println(expected);
        Collections.sort(expected,Comparator.reverseOrder()); //Sắp xếp danh sách mong đợi (expected) để làm chuẩn                                                               
        System.out.println(expected);
        Assert.assertTrue(expected.equals(actual));
    }
    
    @Test
    public void testSortProductsByNameAtoZ(){
        List<ProductItem> productItems = inventoryPage.getProductItems();
        List<String> expected = new ArrayList<>(productItems.stream().map(ProductItem::getName).toList()); 

        inventoryPage.sortProductByName_ZToA();                                                    
        List<String> actual = productItems.stream().map(ProductItem::getName).toList();                  

        Assert.assertFalse(expected.equals(actual));                                                     

        Collections.sort(expected, Comparator.reverseOrder());  //sap xếp giảm trong Collections                                                              
        Assert.assertTrue(expected.equals(actual));
    }


    @AfterMethod
    public  void AfterMethod() {
        driver.close();
    }
}

