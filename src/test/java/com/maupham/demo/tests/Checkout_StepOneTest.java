package com.maupham.demo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.maupham.demo.pages.CheckoutStepOnePage;
import com.maupham.demo.pages.CheckoutStepTwoPage;

public class Checkout_StepOneTest extends BaseTest {
   private CheckoutStepOnePage checkoutStepOnePage;
   private CheckoutStepTwoPage checkoutStepTwoPage;
   private WebDriver driver;

   public Checkout_StepOneTest() {}

   @BeforeMethod
   public void BeforeMethod() {
      driver = setUpDriver();
      loginAndGoToCheckoutStepOnePage(driver);
   }
    
   @Test
   public void testCheckout01() {
      checkoutStepOnePage = new CheckoutStepOnePage(driver);
      checkoutStepOnePage.inputFirstName("ABC");
      checkoutStepOnePage.inputLastName("DFG");
      checkoutStepOnePage.inputPostalCode("12345e");
      checkoutStepOnePage.clickContinue();
      checkoutStepTwoPage= new CheckoutStepTwoPage(driver);
      Assert.assertTrue(checkoutStepTwoPage.isDisplayedOK());
   }

   @Test
   public void testCheckout02() {
      checkoutStepOnePage = new CheckoutStepOnePage(driver);
      checkoutStepOnePage.inputFirstName("ABC");
      checkoutStepOnePage.inputLastName("DFG");
      checkoutStepOnePage.clickContinue();
      Assert.assertEquals(checkoutStepOnePage.getErrorCheckout(), "Error: Postal Code is required");
   }
      
   @Test
   public void testCheckout03() {
      checkoutStepOnePage = new CheckoutStepOnePage(driver);
      checkoutStepOnePage.inputFirstName("ABC");
      checkoutStepOnePage.inputPostalCode("12345e");
      checkoutStepOnePage.clickContinue();
      Assert.assertEquals(checkoutStepOnePage.getErrorCheckout(), "Error: Last Name is required");
   }
      
   @Test
   public void testCheckout04() {
      checkoutStepOnePage = new CheckoutStepOnePage(driver);
      checkoutStepOnePage.inputLastName("DFG");
      checkoutStepOnePage.inputPostalCode("12345e");
      checkoutStepOnePage.clickContinue();
      Assert.assertEquals(checkoutStepOnePage.getErrorCheckout(), "Error: First Name is required");
   }
   
   @AfterMethod
   public void AfterMethod(){
      driver.close();
   }
 }
