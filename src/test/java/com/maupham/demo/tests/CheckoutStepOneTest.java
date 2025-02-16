package com.maupham.demo.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CheckoutStepOnePage;
import com.maupham.demo.pages.CheckoutStepTwoPage;

public class CheckoutStepOneTest extends BaseTest {
   private final ThreadLocal<CheckoutStepOnePage> _checkoutStepOnePage = new ThreadLocal<>();
   private final ThreadLocal<CheckoutStepTwoPage> _checkoutStepTwoPage = new ThreadLocal<>();

   @BeforeMethod
   public void BeforeMethod() {
      loginAndGoToCheckoutStepOnePage();
   }
    
   @Test
   public void testCheckout01() {
      _checkoutStepOnePage.set(new CheckoutStepOnePage(getDriver()));
      _checkoutStepOnePage.get().inputFirstName("ABC");
      _checkoutStepOnePage.get().inputLastName("DFG");
      _checkoutStepOnePage.get().inputPostalCode("12345e");
      _checkoutStepOnePage.get().clickContinue();
      _checkoutStepTwoPage.set(new CheckoutStepTwoPage(getDriver()));
      Assert.assertTrue(_checkoutStepTwoPage.get().isDisplayedOK());
   }

   @Test
   public void testCheckout02() {
      _checkoutStepOnePage.set(new CheckoutStepOnePage(getDriver()));
      _checkoutStepOnePage.get().inputFirstName("ABC");
      _checkoutStepOnePage.get().inputLastName("DFG");
      _checkoutStepOnePage.get().clickContinue();
      Assert.assertEquals(_checkoutStepOnePage.get().getErrorCheckout(), "Error: Postal Code is required");
   }
      
   @Test
   public void testCheckout03() {
      _checkoutStepOnePage.set(new CheckoutStepOnePage(getDriver()));
      _checkoutStepOnePage.get().inputFirstName("ABC");
      _checkoutStepOnePage.get().inputPostalCode("12345e");
      _checkoutStepOnePage.get().clickContinue();
      Assert.assertEquals(_checkoutStepOnePage.get().getErrorCheckout(), "Error: Last Name is required");
   }
      
   @Test
   public void testCheckout04() {
      _checkoutStepOnePage.set(new CheckoutStepOnePage(getDriver()));
      _checkoutStepOnePage.get().inputLastName("DFG");
      _checkoutStepOnePage.get().inputPostalCode("12345e");
      _checkoutStepOnePage.get().clickContinue();
      Assert.assertEquals(_checkoutStepOnePage.get().getErrorCheckout(), "Error: First Name is required");
   }
   
   @AfterMethod
   public void afterMethod(){
      _checkoutStepOnePage.remove();
      _checkoutStepTwoPage.remove();
   }
 }
