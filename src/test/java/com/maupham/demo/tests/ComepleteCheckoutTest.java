package com.maupham.demo.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CheckoutComplete;
import com.maupham.demo.pages.CheckoutStepTwoPage;

public class ComepleteCheckoutTest extends BaseTest {

    private final ThreadLocal<CheckoutStepTwoPage> _checkoutStepTwoPage = new ThreadLocal<>();
    private final ThreadLocal<CheckoutComplete> _checkoutComplete = new ThreadLocal<>();

    @BeforeMethod
    public void beforeMethod() {
        loginAndGoToCheckoutStepTwoPage();
        _checkoutStepTwoPage.set(new CheckoutStepTwoPage(getDriver()));
    }

    @Test
    public void testComplete() {
        _checkoutStepTwoPage.get().clickFinishButton();
        _checkoutComplete.set(new CheckoutComplete(getDriver()));
        Assert.assertTrue(_checkoutComplete.get().isDisplayOk());
    }

    @AfterMethod
    public void afterMethod() {
        _checkoutStepTwoPage.remove();
        _checkoutComplete.remove();
    }


}
