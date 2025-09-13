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
        //su dung index xac dinh vi tri cu the cac phan tu trong ds
        //tao cac XPath dong
        index++; // tang chi so len 1 de phu hop voi chi muc của XPath (nhieu phan tu)

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
        var  price = Double.valueOf(getText(byPrice).substring(1));
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
