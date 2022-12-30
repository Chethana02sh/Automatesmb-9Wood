package pages;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends Pages{

    WebDriver driver;
    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(id="Products_editView_fieldName_productname")
    private WebElement productName;

    @FindBy(id="Products-editview-fieldname-unit_price")
    private WebElement unitPrice;

    @FindBy(id="Products_editView_fieldName_qtyinstock")
    private WebElement quantityInStocks;

    @FindBy(xpath="//button[@name='saveButton']")
    private WebElement saveBtn;

    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public ProductPage verifyAndEnterProductName(String productname){
        actions.type(productName, productname);
        return this;
    }

    public ProductPage verifyAndEnterUnitPrice(String unitprice){
        actions.type(unitPrice, unitprice);
        return this;
    }

    public ProductPage verifyAndEnterStocks(String stocks){
        actions.type(quantityInStocks, stocks);
        return this;
    }

    public CampaignPage saveProduct(String productName, String unitprice, String stocks){
        verifyAndEnterProductName(productName)
                .verifyAndEnterUnitPrice(unitprice)
                .verifyAndEnterStocks(stocks);
        actions.click(saveBtn);
        return new CampaignPage(driver);
    }
}
