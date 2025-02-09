package com.maupham.demo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CheckoutComplete;
import com.maupham.demo.pages.CheckoutStepTwoPage;

public class ComepleteChecoutkTest extends BaseTest {
    private WebDriver driver;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutComplete checkoutComplete;

    public ComepleteChecoutkTest() {}

    @BeforeMethod
    public void BeforeMethod() {
        driver = setUpDriver();
        loginAndGoToCheckoutStepTwoPage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
    }

    @Test
    public void testComplete() {
        checkoutStepTwoPage.clickFinishButton();
        checkoutComplete = new CheckoutComplete(driver);
        Assert.assertTrue(checkoutComplete.isDisplayOk());
    }

    @AfterMethod
    public void AfterMethod() {
        driver.close();
    }


}
