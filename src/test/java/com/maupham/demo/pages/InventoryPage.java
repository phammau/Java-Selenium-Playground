package com.maupham.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
       super(driver);
    }

    private final By bytitle = By.xpath("//span[text()='Products']");
    private final By bycart = By.className("shopping_cart_link");
    private final By byProductItems = By.xpath("//div[@data-test='inventory-item']");
    private final By bySort = By.xpath("//select[@data-test ='product-sort-container']");

    //kt xem phan tu co ton tai trên web hay ko
    public Boolean isDisplayedOK() {
        return !getElements(bytitle).isEmpty();
    }

    //kt va tra ve sô luong sp trong gio hang
    public int getCartCount() {
        String count = getElement(bycart).getText(); //lay van ban cua phan tu
        if("".equals(count)){ //kt xem có rông ko
            return 0; //neu rong tra ve 0
        }
        return Integer.parseInt(count); //chuyen doi vê so kieu int
    }

    // Lấy danh sách các đối tượng ProductItem
    public List<ProductItem> getProductItems() {
        List<WebElement> elements = getElements(byProductItems); //tìm kiếm tất cả các phần tử(list) web phù hợp với tiêu chí byproductItem và lưu trữ chúng trong danh sách elements
        if(elements.isEmpty()) { //Nếu danh sách elements rỗng, trả về một danh sách trống
            return  new ArrayList<>();
        }
        
        List<ProductItem> productItems = new ArrayList<>(); //khởi tạo một danh sách rong chứa các đối tượng ProductItem
       
        // Duyệt qua từng phần tử trong danh sách elements và tạo đối tượng ProductItem  mới tương ứng
        for (int i = 0; i <elements.size(); i++) {
            ProductItem productItem = new ProductItem(getDriver(), i); //khởi tạo tương ứng với phần tử web tại vị trí i trong danh sách elements.
            productItems.add(productItem);
        }
        return productItems; //trả về danh sách productItems

    }

    //click vao  gio hang
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
    public void loginAuto_CartPage(){
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();
    }
}
