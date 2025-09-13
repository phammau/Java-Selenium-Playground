package com.maupham.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
       super(driver);
    }

    private final By bytitle = By.xpath("//span[text()='Products']");
    private final By bycart = By.className("shopping_cart_link");
    private final By byProductItems = By.xpath("//div[@data-test='inventory-item']");
    private final By bySort = By.xpath("//select[@data-test ='product-sort-container']");

    //kt xem phan tu co ton tai tren web hay khong
    public Boolean isDisplayedOK() {
        return !getElements(bytitle).isEmpty();
    }

    //kt va tra ve so luong sp trong gio hang
    public int getCartCount() {
        var count = getElement(bycart).getText(); //lay van ban cua phan tu
        if("".equals(count)){ //kt xem có rong ko
            return 0; //neu rong tra ve 0
        }
        return Integer.parseInt(count); //chuyen doi vê so kieu int
    }

    // lay ds cac doi tuong ProductItem
    public List<ProductItem> getProductItems() {
        var elements = getElements(byProductItems); //tim kiem ca phan tu (list) web phu hop voi tieu chi byproductItem va luu trong ds elements
       
        if(elements.isEmpty()) { // neu ds rong tra ve mang rong
            return new ArrayList<>();
        }
        var productItems = new ArrayList<ProductItem>(); //khoi tao ds rong chua cac doi tuong ProductItem(chi ro tha so)
       
        // duyet qua tung phan tu trong elements và tao doi tuong ProductItem moi tuong ung
        for (int i = 0; i <elements.size(); i++) {
            ProductItem productItem = new ProductItem(getDriver(), i); //khoi tao ung  voi cac phan tu thu i trong ds elements.
            productItems.add(productItem);
        }
        return productItems; 
    }

    public void clickCartIcon() {
        clickElement(bycart);
    }
    
    //select option
    public void sortProductByPrice_LowToHight() {
        selectDropdownOption(bySort, "lohi");
    }

    public void sortProductByPrice_HightToLow() {
        selectDropdownOption(bySort, "hilo");
    }

    public void sortProductByName_AToZ() {
        selectDropdownOption(bySort, "az");
    }

    public void sortProductByName_ZToA() {
        selectDropdownOption(bySort, "za");
    }
}
