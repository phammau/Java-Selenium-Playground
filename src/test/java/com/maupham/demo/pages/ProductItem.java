package com.maupham.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductItem extends InventoryPage{
    
    private final  By ButtonAddToCart;
    private final  By ButtonRemove;
    private final  By byImage;
    private final  By byName;
    private final  By byDescription;
    private final  By byPrice;

    public ProductItem(WebDriver driver, int index) {
        super(driver);

        //Sử dụng index giúp bạn xác định vị trí cụ thể của các phần tử trong danh sách.
        //tạo ra các XPath động, cho phép bạn truy cập các phần tử cụ thể dựa trên vị trí của chúng trong danh sách.
        index++; // Tăng chỉ số lên 1 để phù hợp với chỉ mục của XPath (nhiếu phân tử)

        byImage = By.xpath("//div[@class='inventory_item'][" + index + "]//img[@class='inventory_item_img']");
        byName = By.xpath("//div[@class='inventory_item'][" + index + "]//div[@data-test='inventory-item-name']");
        byDescription = By.xpath("//div[@class='inventory_item'][" + index + "]//div[@data-test='inventory-item-desc']");
        byPrice = By.xpath("//div[@class='inventory_item'][" + index + "]//div[@data-test='inventory-item-price']");
        ButtonAddToCart = By.xpath("//div[@class='inventory_item'][" + index + "]//button[text()='Add to cart']");
        ButtonRemove  = By.xpath("//div[@class='inventory_item'][" + index + "]//button[text()='Remove']");
    }

    public void clickAddToCartButton() {
        clickElement(ButtonAddToCart);
    }

    public void clickRemoveBtn() {
        getElement(ButtonRemove).click();
    }

    public String getName() {
        return getText(byName);
    }

    public Double getPrice() {
        Double price = Double.valueOf(getText(byPrice).substring(1));
        return price;
    }
 
    public String getDescription() {
        return getText(byDescription);
    }

    public String getImage() {
        return getElement(byImage).getAttribute("src");
    }
    public void clickName() {
        clickElement(byName);
    }

}
