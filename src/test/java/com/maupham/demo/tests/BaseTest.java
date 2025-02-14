package com.maupham.demo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.maupham.demo.pages.CartPage;
import com.maupham.demo.pages.CheckoutStepOnePage;
import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

//chuẩn bị và thiết lập môi trường
public class BaseTest {
    private final String BASE_URL = "https://www.saucedemo.com/";
    private final String CORRECT_USERNAME = "standard_user";
    private final String CORRECT_PASSWORD = "secret_sauce";
    private final String INPUTFIRSTNAME = "ABC";
    private final String INPUTLASTNAME = "DFG";
   // private final String INPUTPOSTALCODE = "12345e";


    private final ThreadLocal<WebDriver> _driver = new ThreadLocal<>();
    public BaseTest() { }

    @BeforeMethod
    public void setUp() {
        _driver.set(setUpDriver());// gán WebDriver duy nhất cho thread hiện tại
    }

    @AfterMethod
    public void tearDown() {
        if (_driver.get() != null) {
            _driver.get().quit();
            _driver.remove();
        }
    }

    public WebDriver getDriver() {
        return _driver.get();
    }

    protected WebDriver setUpDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();// Khởi tạo tùy chọn trình duyệt
        options.addArguments("--start-maximized");// Mở trình duyệt với cửa sổ tối đa
        
        WebDriver driver = new ChromeDriver(options); // Khởi tạo WebDriver với ChromeOptions
        driver.get(BASE_URL);// Điều hướng đến trang web
        return driver;
    }

    protected void login() { 
        var  loginPage = new LoginPage(getDriver());
        loginPage.login(CORRECT_USERNAME, CORRECT_PASSWORD);
    }

    protected void loginAndGoToCheckoutStepOnePage(WebDriver driver){
        login();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
    }

    protected void loginAndGoToCheckoutStepTwoPage(WebDriver driver){
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();

       CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
       checkoutStepOnePage.autoFillAndProceedToStepTwo(INPUTFIRSTNAME, INPUTLASTNAME, INPUTLASTNAME);
    }
}