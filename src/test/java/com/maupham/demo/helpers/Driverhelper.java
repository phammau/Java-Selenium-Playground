package com.maupham.demo.helpers;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverHelper {
    
    public static WebDriver getDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        
        var path = System.getProperty("user.dir");
        var options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless");
        }
        var logFile = new File(path + "/chromedriver.log");
        var service = new ChromeDriverService.Builder()
            .withVerbose(true)
            .withLogFile(logFile)
            .build();
        return new ChromeDriver(service, options);  
    }
}
