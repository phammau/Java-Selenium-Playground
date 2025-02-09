package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //locators(UI elements) xác dịnh các phân tu trong giao diẹn
    private final By ByUsernameinput =  By.id("user-name");
    private final By ByPasswordinput = By.id("password");
    private final By ByLoginbutton  = By.id("login-button");
    private final By ByErrorMessage = By.xpath("//h3[@data-test='error' and contains(text(),'Epic sadface: Username and password do not match any user in this service')]");

    //action (menthod)//hành động
    public void nhapUsername(String username) {
        inputText(ByUsernameinput, username);
    }

    public void nhapPassword(String password) {
        inputText(ByPasswordinput, password);
    }
    
    public void clickButtonLogin() {
       clickElement(ByLoginbutton);
    }

    public String getError() {
        return getText(ByErrorMessage);
    }

    public void login(String username, String password){
        nhapUsername(username);
        nhapPassword(password);
        clickButtonLogin();
    }
}
