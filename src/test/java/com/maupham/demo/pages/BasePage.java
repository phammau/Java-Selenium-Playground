package com.maupham.demo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
   protected WebDriver driver;
   protected WebElement element;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
       return driver;
    }

    //tìm và trả về một phần tử web (WebElement) dựa trên tiêu chí tìm kiếm (By)
    public WebElement getElement(By by) {
        element = driver.findElement(by);
        return element;
    }
    
    //ttìm và trả  ve danh sách các phần tử web (WebElement) dựa trên tiêu chí tìm kiếm (By)
    public List<WebElement> getElements(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    public void inputText(By by,String text) {
        getElement(by).sendKeys(text); //Dùng để nhập dữ liệu vào các trường nhập liệu
    }

    public void clickElement(By by) {
        getElement(by).click();
    }

    public String getText(By by) {
        return getElement(by).getText();
    }

    //chọn một tùy chọn trong một dropdown (menu sổ xuống) dựa trên giá trị (value)
   public void selectDropdownOption(By by, String value) {
        element = getElement(by); //Lấy phần tử dropdown
        Select selectDropdown = new Select(element); //Khởi tạo đối tượng Select truyen vao phan tu dropdown
        selectDropdown.selectByValue(value); //chon dua vao value
   }
}
