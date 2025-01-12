package com.maupham.demo.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driverhelper {
    
    public static WebDriver getDriver() {

        String path = System.getProperty("user.dir"); //Lấy đường dẫn thư mục hiện tại
        ChromeOptions options =  new ChromeOptions(); //Tạo đối tượng ChromeOptions ,khởi tạo cho trình duyệt Chrome.
        //this line for windows
        System.setProperty("webdriver.chrome.driver",path+"/drivers/chromedriver.exe");  //Đặt thuộc tính hệ thống cho ChromeDriver
        return new ChromeDriver(options);  //Khởi tạo và trả về ChromeDriver
    }
}
