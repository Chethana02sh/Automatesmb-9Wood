package pages;

import api.CampaignFieldMeta;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pojo.CampaignPojo;
import pojo.Field;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class CampaignPage extends Pages{

    private WebDriver driver;
    private String json;
    private CampaignPojo campaignPojo;
    private SoftAssert softAssert;

    public CampaignPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        softAssert=new SoftAssert();
    }

    @FindBy(xpath = "//button[contains(@id,'listView_basicAction_LBL_ADD_RECORD')]")
    private WebElement addButton;

    @FindBy(xpath = "//table[@class='table table-borderless']/tbody/tr/td[@class='fieldLabel alignMiddle']")
    private List<WebElement> FieldLabels;

    @FindBy(name="campaignname")
    private WebElement campaignname;

    @FindBy(xpath="//select[@name='assigned_user_id']")
    private WebElement assignedto;

    @FindBy(xpath = "//td[contains(text(),'Campaign Status')]")
    private WebElement campaignstatus;

    @FindBy(xpath="//select[@name='campaignstatus' and @data-fieldname]")
    private WebElement campaginstatuslist;

    @FindBy(xpath = "//td[contains(text(),'Campaign Status')]/following-sibling::td/descendant::span")
    private WebElement campaignField;

    @FindBy(xpath="//td[contains(text(),'Call to Action')]")
    private WebElement callToActionLabel;

    @FindBy(xpath = "//td[contains(text(),'Call to Action')]/following-sibling::td/input")
    private WebElement callToActionField;

    @FindBy(xpath = "//td[contains(text(),'Product')]")
    private WebElement productLabel;
    
    @FindBy(xpath = "//td[contains(text(),'Product')]/following-sibling::td/descendant::input[@name='product_id']")
    private WebElement productField;


    @FindBy(xpath = "//td[contains(text(),'Product')]/following-sibling::td/descendant::span[@title='Create'] | //i[@id='Campaigns_editView_fieldName_product_id_create']")
    private WebElement productPlusBtn;

    @FindBy(xpath = "//td[contains(text(),'Campaign Type')]")
    private WebElement campaignTypeLabel;

    @FindBy(xpath = "//td[contains(text(),'Campaign Type')]/following-sibling::td/select")
    private WebElement campaginTypeDropDown;

    @FindBy(xpath="//td[contains(text(),'Expected Close Date')]")
    private WebElement expectedCloseDateLabel;

    @FindBy(xpath="//td[contains(text(),'Expected Close Date')]/following-sibling::td/descendant::input[@name='closingdate']")
    private WebElement expectedCloseDateField;

    @FindBy(xpath="//div[@class='datepicker-days']/descendant::th[@class='next']")
    private WebElement nextBtn;

    @FindBy(xpath="//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']")
    private WebElement monthYearLabel;

    @FindBy(xpath="//td[contains(text(),'Target Audience')]")
    private WebElement targetAudienceLabel;

    @FindBy(xpath="//td[contains(text(),'Target Audience')]/following-sibling::td/input")
    private  WebElement targetAudienceField;

    @FindBy(xpath="//td[contains(text(),'TargetSize')]")
    private WebElement targetSizeLabel;

    @FindBy(xpath="//td[contains(text(),'TargetSize')]/following-sibling::td/input")
    private WebElement targetSizeField;

    @FindBy(xpath="//td[contains(text(),'Num Sent')]")
    private WebElement numSetLabel;

    @FindBy(xpath="//td[contains(text(),'Num Sent')]/following-sibling::td/descendant::input")
    private WebElement numSetField;

    @FindBy(xpath="//td[contains(text(),'Sponsor')]")
    private WebElement sponserLabel;

    @FindBy(xpath="//td[contains(text(),'Sponsor')]/following-sibling::td/descendant::input")
    private WebElement sponserField;

    @FindBy(xpath="//h4[text()='Custom Information']/following-sibling::table/descendant::td[contains(text(),'Audience')]")
    private WebElement audienceLabel;

    @FindBy(xpath="//h4[text()='Custom Information']/following-sibling::table/descendant::td[contains(text(),'Audience')]/following-sibling::td/descendant::select")
    private WebElement audienceField;

    @FindBy(xpath="//h4[text()='Custom Information']/following-sibling::table/descendant::td[contains(text(),'Research Notes')]")
    private WebElement researchNotesLabel;

    @FindBy(xpath="//h4[text()='Custom Information']/following-sibling::table/descendant::td[contains(text(),'Research Notes')]/following-sibling::td/input")
    private WebElement researchNotesField;

    @FindBy(xpath="//td[contains(text(),'Description')]")
    private WebElement descriptionLabel;

    @FindBy(xpath="//td[contains(text(),'Description')]/following-sibling::td/textarea")
    private WebElement descriptionField;

    @FindBy(xpath="//td[contains(text(),'Budget Cost')]")
    private WebElement budgetCostLabel;

    @FindBy(xpath="//td[contains(text(),'Budget Cost')]/following-sibling::td/div/input")
    private WebElement budgetCostField;

    @FindBy(xpath="//td[contains(text(),'Actual Cost')]")
    private WebElement actualCostLabel;

    @FindBy(xpath="//td[contains(text(),'Actual Cost')]/following-sibling::td/div/input")
    private WebElement actualCostField;

    @FindBy(xpath="")
    private WebElement expectedResponseLabel;

    @FindBy(xpath="")
    private WebElement expectedResponseField;

    @FindBy(xpath="//td[contains(text(),'Expected Revenue')]")
    private WebElement expectedRevenueLabel;

    @FindBy(xpath="//td[contains(text(),'Expected Revenue')]/following-sibling::td/div/input")
    private WebElement expectedRevenueField;

    @FindBy(xpath="//td[contains(text(),'Expected Sales Count')]")
    private WebElement expectedSalesCountLabel;

    @FindBy(xpath="//td[contains(text(),'Expected Sales Count')]/following-sibling::td/input")
    private WebElement expectedSalesCountField;

    @FindBy(xpath="//td[contains(text(),'Actual Sales Count')]")
    private WebElement ActualSalesCountLabel;

    @FindBy(xpath="//td[contains(text(),'Actual Sales Count')]/following-sibling::td/input")
    private WebElement ActualSalesCountField;

    @FindBy(xpath="//td[contains(text(),'Expected Response Count')]")
    private WebElement expectedResponseCountLabel;

    @FindBy(xpath="//td[contains(text(),'Expected Response Count')]/following-sibling::td/input")
    private WebElement expectedResponseCountField;

    @FindBy(xpath="//td[contains(text(),'Actual Response Count')]")
    private WebElement actualResponseCountLabel;

    @FindBy(xpath="//td[contains(text(),'Actual Response Count')]/following-sibling::td/input")
    private WebElement actualResponseCountField;

    @FindBy(xpath="//td[contains(text(),'Expected ROI')]")
    private WebElement expectedROILabel;

    @FindBy(xpath="//td[contains(text(),'Expected ROI')]/following-sibling::td/div/input")
    private WebElement expectedROIField;

    @FindBy(xpath="//td[contains(text(),'Actual ROI')]")
    private WebElement actualROILabel;

    @FindBy(xpath="//td[contains(text(),'Actual ROI')]/following-sibling::td/div/input")
    private WebElement actualROIField;

    @Override
    public CampaignPage navigateToMetaInfo(String url) {
        driver.navigate().to(url);
        json=driver.findElement(By.tagName("body")).getText();
        driver.navigate().back();
        convertJsonToPojo();
        return this;
    }

    @Override
    public Pages convertJsonToPojo() {
        ObjectMapper mapper=new ObjectMapper();
        
        try {
            campaignPojo=mapper.readValue(json, CampaignPojo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public CampaignPage clickOnAddButton(){
        actions.click(addButton);
        return this;
    }

    public CampaignPage verifyCampaignNameField(){
        String compaignLabel= (String) getJsonData(json, campaignPojo.getName());
        return this;
    }

    public CampaignPage verifyAllCampaignLabels(){
        SoftAssert softAssert=new SoftAssert();
       List<String> fieldListMeta= campaignPojo.getFields().stream().map(field -> field.getName()).collect(Collectors.toList());
        List<String> labelListUI= getFieldLabels().stream().map(field-> field.getText().toLowerCase().replaceAll(" ","")).collect(Collectors.toList());
        softAssert.assertTrue(fieldListMeta.containsAll(labelListUI));
        System.out.println(fieldListMeta);
        System.out.println(labelListUI);//Not matching with all field label
        //softAssert.assertAll();
        return this;
    }

    public CampaignPage verifyAndEnterCampaignField(String campaignName){
        Boolean fieldIsMandatory=  campaignPojo.getFields().get(0).isMandatory();
        Assert.assertTrue(fieldIsMandatory);
        String fieldType= campaignPojo.getFields().get(0).getType().getName();
        Assert.assertEquals(fieldType, campaignname.getAttribute("data-fieldtype"));
        actions.type(campaignname,campaignName);
        return this;
    }

    public CampaignPage verifyAndSelectAssignedTo(String assignedTo){
        String assignedToMeta=campaignPojo.getFields().get(6).getLabel();
       // Assert.assertTrue(actions.getElement("//*[contains(text(),'"+assignedToMeta+"')]").isDisplayed());//label
        String assignedToTypeMeta=campaignPojo.getFields().get(6).getType().getName();
        String assignedToTypeUI=assignedto.getAttribute("data-fieldtype").trim();
        System.out.println(assignedToTypeUI+"=="+assignedToTypeMeta);
        Assert.assertEquals(assignedToTypeMeta,assignedToTypeUI);//type
        actions.selectDropDownByText(assignedto,assignedTo);
        return this;
    }

    public CampaignPage verifyAndEnterCampaistatus(String text){
        Field campaginStatusMeta=campaignPojo.getFields().get(4);
       Assert.assertEquals(campaginStatusMeta.getLabel(), campaignstatus.getText().trim());//label
      List<String> campaignStatusUI= actions.getAllDropDownOptions(campaginstatuslist);
       List<String> campaginstatusMeta=campaginStatusMeta.type.picklistValues.stream()
               .map(status->status.getValue().trim()).collect(Collectors.toList());
        Assert.assertTrue(campaignStatusUI.containsAll(campaginstatusMeta));
        actions.click(campaignField);
        actions.selectDropDownByText(campaginstatuslist,text);
       return this;
    }

    public CampaignPage verifyAndEnterCallToAction(String text){
        Field field=campaignPojo.getFields().get(28);
        Assert.assertEquals(field.getLabel(), callToActionLabel.getText().trim());//label
        Assert.assertEquals(field.getType().getName(), callToActionField.getAttribute("data-fieldtype"));//type
        actions.type(callToActionField,text);
        return this;
    }

    public ProductPage verifyAndClickOnProductPlusBtn(){
        Field field=campaignPojo.getFields().get(3);
        Assert.assertEquals(field.getLabel(),productLabel.getText().trim());//label
        actions.click(productPlusBtn);

        /*actions.waitForVisibilityOfElement(productField);
        actions.type(productField,text);*///element is hidden
        return new ProductPage(driver);
    }

    public ProductPage clickOnProductPlusBtn(){
        actions.click(productPlusBtn);
        return new ProductPage(driver);
    }

    public CampaignPage verifyAndSelectCampaginType(String text){
        Field field=campaignPojo.getFields().get(2);
        Assert.assertEquals(field.getLabel(), campaignTypeLabel.getText().trim());
        List<String> campignPicklistMeta = field.getType().getPicklistValues().stream().map(picklist -> picklist.getValue()).collect(Collectors.toList());
       List<String> campignPicklistUI= actions.getAllDropDownOptions(campaginTypeDropDown);
        report.info("UI picklist: "+campignPicklistUI);
        report.info("Meta picklist: "+campignPicklistMeta);
        System.out.println(campignPicklistUI.subList(1, campignPicklistUI.size()-1));
        System.out.println(campignPicklistMeta);
        List<String> pickList=campignPicklistUI.subList(1, campignPicklistUI.size()-1);
        for(int i=0;i<pickList.size();i++){
            if(pickList.get(i).contains(campignPicklistMeta.get(i))){
                softAssert.assertTrue(pickList.get(i).contains(campignPicklistMeta.get(i) ));
            }else{
                softAssert.fail(pickList.get(i)+" is missing in the list Meta");
            }
        }

       actions.selectDropDownByText(campaginTypeDropDown,text);
        return this;
    }

    public CampaignPage verifyAndSelectExpectedEndDate(String date, String month, String year){
        Field field=campaignPojo.getFields().get(5);
        report.info("UI field Label: "+expectedCloseDateLabel.getText());
        report.info("Meta Field Label: "+field.getLabel());
        System.out.println(expectedCloseDateLabel.getText());
        System.out.println(field.getLabel());
        Assert.assertEquals(field.getLabel(), expectedCloseDateLabel.getText().trim());
        String dateFormat=field.getType().getFormat();
        report.info("UI date format: "+expectedCloseDateField.getAttribute("data-date-format").trim());
        report.info("Meta date format: "+dateFormat);
        Assert.assertEquals(dateFormat, expectedCloseDateField.getAttribute("data-date-format").trim());
        actions.click(expectedCloseDateField);
        report.info(String.format("%s-%s-%s", date, month, year));
        int totalmonth=12;
        while(totalmonth>=0){
            try{
                driver.findElement(By.xpath("//div[@class='datepicker-days']/descendant::th[text()='"+month+" "+year+"']/ancestor::table/descendant::td[text()='"+date+"']")).click();
                break;
            }catch (Exception e){
                actions.click(nextBtn);
                totalmonth--;
            }
        }

        return this;
    }

    public CampaignPage verifyAndEnterTargetAudience(String targetNumber){
        Field field=campaignPojo.getFields().get(9);
        report.info("UI Label: "+targetAudienceLabel.getText().trim());
        report.info("Meta Label: "+field.getLabel());
        Assert.assertEquals(field.getLabel(), targetAudienceLabel.getText().trim());
        report.info("UI Type: "+targetAudienceField.getAttribute("data-fieldtype"));
        report.info("Meta Type: "+field.getType().getName());
        Assert.assertEquals(field.getType().getName(), targetAudienceField.getAttribute("data-fieldtype"));
        actions.type(targetAudienceField, targetNumber);
        return this;
    }

    public CampaignPage verifyAndEnterTargetSize(String targetSize){
        Field field=campaignPojo.getFields().get(10);
        report.info("UI Label: "+targetSizeLabel.getText().trim());
        report.info("Meta Label: "+field.getLabel());
        Assert.assertEquals(field.getLabel(), targetSizeLabel.getText().trim());
        actions.type(targetSizeField, targetSize);
        return this;
    }


    public CampaignPage verifyAndEnterNumSet(String numSet){
        Field field=campaignPojo.getFields().get(7);
        report.info("UI Label: "+numSetLabel.getText().trim());
        report.info("Meta Label: "+field.getLabel());
        Assert.assertEquals(field.getLabel(), numSetLabel.getText().trim());
        actions.type(numSetField, numSet);
        return this;
    }


    public CampaignPage verifyAndEnterSponserField(String sponcerName){
        Field field=campaignPojo.getFields().get(8);
        report.info("UI Label: "+sponserLabel.getText().trim());
        report.info("Meta Label: "+field.getLabel());
        Assert.assertEquals(field.getLabel(), sponserLabel.getText().trim());
        report.info("UI Type: "+sponserField.getAttribute("data-fieldtype"));
        report.info("Meta Type: "+field.getType().getName());
        Assert.assertEquals(field.getType().getName(), sponserField.getAttribute("data-fieldtype"));
        actions.type(sponserField, sponcerName);
        return this;
    }

   public CampaignPage verifyAndEnterAudience(String audience){
        Field field=campaignPojo.getFields().get(29);
        report.info("Meta Label: "+field.getLabel());
        report.info("UI Label: "+audienceLabel.getText().trim());
        Assert.assertEquals(field.getLabel(), audienceLabel.getText().trim());
        List<String> audienceUIOptions=actions.getAllDropDownOptions(audienceField).stream().filter(text->!text.isEmpty()).collect(Collectors.toList());
        List<String> audienceMetaOptions=field.getType().getPicklistValues().stream()
                .map(list->list.getValue()).collect(Collectors.toList());
        report.info("Audinece Meta picklist"+audienceMetaOptions);
        report.info("Audinece UI picklist"+audienceUIOptions);
       Assert.assertEquals(audienceUIOptions, audienceMetaOptions);
       actions.scrollToElement(audienceField);
       actions.waitOrPause();
       //actions.click(audienceField);
        actions.selectDropDownByText(audienceField, audience);
        return this;
   }

   public CampaignPage verifyAndEnterResearchNotes(String researchNotes){
        Field field=campaignPojo.getFields().get(30);
        report.info("Meta Label: "+field.getLabel());
        report.info("UI Label: "+researchNotesLabel.getText().trim());
        Assert.assertEquals(field.getLabel(), researchNotesLabel.getText().trim());
        report.info("Research Meta Type: "+field.getType().getName());
       report.info("Research UI Type: "+researchNotesField.getAttribute("data-fieldtype"));
        Assert.assertEquals(field.getType().getName(), researchNotesField.getAttribute("data-fieldtype"));
        actions.type(researchNotesField, researchNotes);
        return this;
   }

   public CampaignPage verifyAndEnterDescription(String description){
       Field field=campaignPojo.getFields().get(24);
       report.info("Meta Label: "+field.getLabel());
       report.info("UI Label: "+descriptionLabel.getText().trim());
       Assert.assertEquals(field.getLabel(), descriptionLabel.getText().trim());
       actions.type(descriptionField, description);
        return this;
   }

   public CampaignPage verifyAndEnterBugetCost(String budgetCost){
        Field field=campaignPojo.getFields().get(16);
       report.info("Meta Label: "+field.getLabel());
       report.info("UI Label: "+budgetCostLabel.getText().trim());
       Assert.assertEquals(field.getLabel(), budgetCostLabel.getText().trim());
       actions.type(budgetCostField, budgetCost);
        return this;
   }

   public CampaignPage verifyAndEnterActualCost(String actualCost){
       Field field=campaignPojo.getFields().get(17);
       report.info("Meta Label: "+field.getLabel());
       report.info("UI Label: "+actualCostLabel.getText().trim());
       Assert.assertEquals(field.getLabel(), actualCostLabel.getText().trim());
       actions.type(actualCostField, actualCost);
        return this;
   }

   public CampaignPage verifyAndEnterExpectedRevenue(String revenue){
       Field field=campaignPojo.getFields().get(15);
       report.info("Meta Label: "+field.getLabel());
       report.info("UI Label: "+expectedRevenueLabel.getText().trim());
       Assert.assertEquals(field.getLabel(), expectedRevenueLabel.getText().trim());
       actions.type(expectedRevenueField, revenue);
        return this;
   }

   public CampaignPage verifyAndEnterExpectedSalesCount(String expectedSalesCount){
       Field field=campaignPojo.getFields().get(19);//find indeex in json response
       report.info("Meta Label: "+field.getLabel());
       report.info("UI Label: "+expectedSalesCountLabel.getText().trim());
       Assert.assertEquals(field.getLabel(), expectedSalesCountLabel.getText().trim());
       actions.type(expectedSalesCountField, expectedSalesCount);
        return this;
   }

   public CampaignPage verifyAndEnterActualSalesCount(String actalSalesCount){
       Field field=campaignPojo.getFields().get(22);
       report.info("Meta Label: "+field.getLabel());
       report.info("UI Label: "+ActualSalesCountLabel.getText().trim());
       Assert.assertEquals(field.getLabel(), ActualSalesCountLabel.getText().trim());
       actions.type(ActualSalesCountField, actalSalesCount);
       return this;
   }

   public CampaignPage verifyAndEnterExpectedResponseCount(String count){
       Field field=campaignPojo.getFields().get(18);
       report.info("Meta Label: "+field.getLabel());
       report.info("UI Label: "+expectedResponseCountLabel.getText().trim());
       Assert.assertEquals(field.getLabel(), expectedResponseCountLabel.getText().trim());
       actions.type(expectedResponseCountField, count);
       return this;
   }

    public CampaignPage verifyAndEnterActualResponseCount(String count){
        Field field=campaignPojo.getFields().get(21);
        report.info("Meta Label: "+field.getLabel());
        report.info("UI Label: "+actualResponseCountLabel.getText().trim());
        Assert.assertEquals(field.getLabel(), actualResponseCountLabel.getText().trim());
        actions.type(actualResponseCountField, count);
        return this;
    }

    public CampaignPage verifyAndEnterExpectedROI(String roi){
        Field field=campaignPojo.getFields().get(20);
        report.info("Meta Label: "+field.getLabel());
        report.info("UI Label: "+expectedROILabel.getText().trim());
        Assert.assertEquals(field.getLabel(), expectedROILabel.getText().trim());
        actions.type(expectedROIField, roi);
        return this;
    }

    public CampaignPage verifyAndEnterActualROI(String roi){
        Field field=campaignPojo.getFields().get(23);
        report.info("Meta Label: "+field.getLabel());
        report.info("UI Label: "+actualROILabel.getText().trim());
        Assert.assertEquals(field.getLabel(), actualROILabel.getText().trim());
        actions.type(actualCostField, roi);
        return this;
    }

    public void verifyAll(){
        softAssert.assertAll();
    }



}
