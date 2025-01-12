package com.maupham.demo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.LoginPage;

public class LoginTest extends BaseTest{
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private WebDriver driver;

    public LoginTest() {}

    @BeforeMethod
    public void BeforeMethod() {
        driver = setUpDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void Test_01() {
        loginPage.nhapUsername("standard_user");
        loginPage.nhapPassword("secret_sauce");
        loginPage.clickButtonLogin();
        inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isDisplayedOK()); //hien thi thanh cong khi dnag nhap dung
    }

    @Test
    public void Test_02() {
        loginPage.nhapUsername("standard_user1");
        loginPage.nhapPassword("secret_sauce");
        loginPage.clickButtonLogin();
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service");
    }
    
    @Test
    public void Test_03() {
        loginPage.nhapUsername("standard_user");
        loginPage.nhapPassword("secret_sauce1");
        loginPage.clickButtonLogin();
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service");
    }
     
    @Test
    public void Test_04() {
        loginPage.nhapUsername("standard_user1");
        loginPage.nhapPassword("secret_sauce1");
        loginPage.clickButtonLogin();
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service");
    }
    
    @AfterMethod
    public void AfterMethod() {
        driver.close();
    }
        
}
