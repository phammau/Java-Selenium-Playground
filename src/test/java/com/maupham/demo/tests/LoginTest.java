package com.maupham.demo.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.LoginPage;

public class LoginTest extends BaseTest {
    private final ThreadLocal<LoginPage> _loginPage = new ThreadLocal<>();

    @BeforeMethod
    public void beforeMethod() {
        _loginPage.set(new LoginPage(getDriver()));//khởi tao đối tuong loginpage với 1 webdriver  và gán nó cho luồng _loginPage
    }

    @Test
    public void Test01() {
        var loginPage = _loginPage.get();
        loginPage.nhapUsername("standard_user");
        loginPage.nhapPassword("secret_sauce");
        loginPage.clickButtonLogin();
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isDisplayedOK()); //hien thi thanh cong khi dnag nhap dung
    }

    @Test
    public void Test02() {
        var loginPage = _loginPage.get();
        loginPage.nhapUsername("standard_user1");
        loginPage.nhapPassword("secret_sauce");
        loginPage.clickButtonLogin();
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service");
    }
    
    @Test
    public void Test03() {
        var loginPage = _loginPage.get();
        loginPage.nhapUsername("standard_user");
        loginPage.nhapPassword("secret_sauce1");
        loginPage.clickButtonLogin();
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service");
    }
     
    @Test
    public void Test04() {
        var loginPage = _loginPage.get();
        loginPage.nhapUsername("standard_user1");
        loginPage.nhapPassword("secret_sauce1");
        loginPage.clickButtonLogin();
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service");
    }

    @AfterMethod
    public void afterMethod() {
        _loginPage.remove();
        
    }
}
