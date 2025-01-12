package com.maupham.demo.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.maupham.demo.helpers.Driverhelper;
import com.maupham.demo.pages.CartPage;
import com.maupham.demo.pages.InventoryPage;
import com.maupham.demo.pages.LoginPage;

//chuẩn bị và thiết lập môi trường
public class BaseTest {
    private final String BASE_URL = "https://www.saucedemo.com/";
    private final String CORRECT_USERNAME = "standard_user";
    private final String CORRECT_PASSWORD = "secret_sauce";

    public BaseTest() { }

    protected WebDriver setUpDriver() {
        WebDriver driver = Driverhelper.getDriver();  //Khởi tạo đối tượng WebDriver tu class Driverhelper
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  //thiêt lap thoi gian cho ngam dịnh ,nếu ko tim thay web sẽ tiêp tuc cho 10s khi nem ra ngoai le
        //mở rộng cửa sổ trình duyệt đến kích thước tối đa
        driver.manage().window().maximize();
         //Điều hướng đến URL cụ thể cua web
         driver.get(BASE_URL);
        //Trả về đối tượng WebDriver
        return driver;
    }

    protected void login(WebDriver driver) { //WebDriver driver phương thức này có thể làm việc với bất kỳ đối tượng WebDriver nào.
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(CORRECT_USERNAME, CORRECT_PASSWORD);
    }

    protected void loginAndGoToCheckoutStepTwoPage(WebDriver driver){
        login(driver);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
    }

}