package pages;

import com.aventstack.extentreports.model.Report;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ReportPage extends Pages {
    WebDriver driver;
    public ReportPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(id="Reports_listView_basicAction_Add")
    private WebElement addReportBtn;

    @FindBy(xpath="//ul[@class='dropdown-menu']/descendant::a[contains(text(),'Detail Report')]")
    private WebElement detailReport;
    @FindBy(name="reportname")
    private WebElement reportName;
    @FindBy(xpath="//label[text()='Report Folder']/following-sibling::div/descendant::span")
    private WebElement reportFolderField;
    @FindBy(xpath="//label[text()='Report Folder']/following-sibling::div/descendant::select[@name='reportfolderid']")
    private WebElement reportFolderDropDown;
    @FindBy(xpath="//label[text()='Primary Module']/following-sibling::div/descendant::span")
    private WebElement primaryModuleField;
    @FindBy(xpath="//label[text()='Primary Module']/following-sibling::div/descendant::select[@id='primary_module']")
    private WebElement primaryModuleDropDown;
    @FindAll({
    @FindBy(xpath="//button[text()='Next' or @type='submit']"),
    @FindBy(xpath = "//button/strong[text()='Next']")})
    private WebElement nextBtn;
    @FindBy(xpath="//label[text()='Select Columns(MAX 25)']/following-sibling::div/descendant::input")
    private WebElement selectColumnsField;

    @FindBy(xpath="//span/p")
    private WebElement selectColumnLabel;
    @FindBy(xpath="//div[@class='conditionList']/descendant::span[text()='Select Field']")
    private WebElement selectField;
    @FindBy(xpath="(//input[contains(@id,'search')])[last()]")
    private WebElement selectFieldSearchField;

    @FindBy(xpath="//*[text()='Choose List conditions']")
    private WebElement chooseListCondtionsLabel;
    @FindBy(xpath="//input[@data-label='Subject']")
    private WebElement campareField;
    @FindBy(id="generateReport")
    private WebElement saveAndgenerateReport;
    @FindBy(xpath="//button[@value='Save']")
    private WebElement saveBtn;
    @FindBy(css="#mCSB_8_container")
    private WebElement reportTypeField;
    @FindBy(name="reporttype")
    private WebElement reportTypeDropDown;
    @FindBy(xpath = "//input[@name='reportname']/ancestor::th/following-sibling::th[1]")
    private WebElement primaryModuleFieldInList;
    @FindBy(xpath="//ul[@class='select2-results mCustomScrollbar _mCS_11 mCS_no_scrollbar']/li/div")
    private List<WebElement> primaryModuleDropdownList;
    @FindBy(xpath = "//td[@data-name='reportname']")
    private List<WebElement> reportNameList;
    @FindBy(xpath="//div[@id='chartcontent']/descendant::div[contains(@class,'jqplot-point-label jqplot-series-0 jqplot-point-')]")
    private List<WebElement> chartCountNum;
    @FindBy(xpath="//span[text()='Search']")
    private WebElement searchBtn;
    @FindBy(xpath = "//span[@class='pageNumbersText']")
    private WebElement pageNumberText;
    @FindBy(xpath="//div[@id='chartcontent']/descendant::div[contains(@class,'jqplot-point-label jqplot-series-0 jqplot-point-')]/following-sibling::canvas")
    private WebElement barGraph;
    @FindBy(xpath = "//div[@id='chartcontent']/div")
    private WebElement noChartData;
    @FindBy(xpath = "//button[@data-trigger='clearListSearch']")
    private WebElement clearBtn;
    @FindBy(xpath = "//a[contains(text(),'Chart Report')]")
    private WebElement chartReport;
    @FindBy(xpath = "//td[@data-name='reportname']/span")
    private WebElement reportTxt;
    @FindBy(xpath = "//label[contains(text(),'Select Related Modules')]/following-sibling::div/descendant::input")
    private WebElement selectRelatedModuleField;
    @FindBy(xpath = "//div[@class='conditionList']/descendant::a/span[text()='Select Field']")
    private WebElement conditionSelectField;
    @FindBy(xpath = "//div[@class='conditionList']/descendant::input[@type='text' and @name]")
    private WebElement compareField;
    @FindBy(xpath = "//span[text()='Select Groupby Field']/ancestor::div[@class='row tab-pane active']/descendant::a/span[text()='None']")
    private WebElement selectGropByField;
    @FindBy(id="chartcontent")
    private WebElement chartContent;
    @FindBy(xpath = "//td[@data-name='owner']/span")
    private WebElement reportOwner;
    @FindBy(xpath = "//div[@class='reportHeader']")
    private WebElement reportHeader;

    @Override
    public Pages navigateToMetaInfo(String url) {
        return null;
    }

    @Override
    public Pages convertJsonToPojo() {
        return null;
    }

    public ReportPage clickOnAddReport(ChartType chartType){
        actions.click(addReportBtn);
        switch (chartType) {
            case DETAILREPORT:
                 actions.click(detailReport);
                 break;
            case CHART_REPORT:
                actions.click(chartReport);
                break;
            default:
                System.out.println("No report selected");
        }
        return this;
    }

    public ReportPage selectRelatedModule(String secondaryModuleName) {
        actions.click(selectRelatedModuleField);
        actions.type(selectRelatedModuleField, secondaryModuleName, Keys.ENTER);
        Actions act=new Actions(driver);
        act.sendKeys(Keys.ESCAPE).perform();
        return this;
    }

    public ReportPage verifyChartContentDisplayed() {
        Assert.assertTrue(chartContent.isDisplayed(), "Chart content is not displayed");
        return this;
    }
    public ReportPage verifyDetailReportContentDisplayed(){
        Assert.assertTrue(reportHeader.isDisplayed());
        return this;
    }

    public enum ChartType{
        DETAILREPORT,
        CHART_REPORT
    }

    public ReportPage enterReportName(String name){
        actions.type(reportName, name);
        return this;
    }

    public ReportPage selectReportFolder(String foldername){
        Actions actions=new Actions(driver);
        actions.click(reportFolderField).sendKeys(foldername, Keys.ENTER).perform();
       // actions.click(reportFolderField);
       // actions.selectDropDownByText(reportFolderDropDown, foldername);
        return this;
    }

    public ReportPage selectPrimaryModule(String module){
        Actions action=new Actions(driver);
        action.click(primaryModuleField).sendKeys(module, Keys.ENTER).perform();
        //actions.click(primaryModuleField);
        //actions.selectDropDownByText(primaryModuleDropDown, module);
        return this;
    }

    public ReportPage clickOnNextBtn(){
        actions.waitOrPause();
        try {
            actions.click(nextBtn);
        }catch (Exception e){
           actions.jsElementClick(nextBtn);
        }
        return this;
    }

    public ReportPage enterSelectColumnField(List<String> columnsName){
        actions.click(selectColumnsField);
        for(String column:columnsName) {
            actions.type(selectColumnsField, column, Keys.ENTER);
        }
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        actions.waitOrPause();
//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(selectColumnLabel)).click();
     //  actions.jsElementClick(selectColumnLabel);
        return this;
    }

    public ReportPage selectAllConditions(String columnName, String comaparefield){
        actions.waitOrPause();
        for(int i=0;i<20;) {
            try {
                actions.jsElementClick(chooseListCondtionsLabel);
                break;
            }catch (Exception e){
                System.out.println("Element not clicked "+chooseListCondtionsLabel);
                i++;
            }
        }
        actions.click(selectField);
        //actions.clickOnElement("//ul[@id='select2-results-1321']/descendant::div[text()='"+columnName+"']");
        actions.type(selectFieldSearchField, columnName, Keys.ENTER);
        actions.type(campareField,comaparefield);
        return this;
    }

    public ReportPage clickOnSaveAndGenerateReport(){
        actions.click(saveAndgenerateReport);
        return this;
    }

    public ReportPage enterCampareField(String camparefield){
        actions.type(campareField, camparefield);
        return this;
    }

    public ReportPage clickOnSaveBtn(){
        actions.click(saveBtn);
    return this;
    }

    public ReportPage searchReportName(String reportname){
        actions.type(reportName, reportname,Keys.ENTER);
        return this;
    }

    public ReportPage verifyDownloadIsDisplayed(String reportname){
        WebElement element=driver.findElement(By.xpath("//span[text()='"+reportname+"']/ancestor::tbody/descendant::a[@title='Download Report']"));
        Assert.assertTrue(element.isDisplayed());
        System.out.println(ExpectedConditions.elementToBeClickable(element).apply(driver).isDisplayed());
        return this;
    }

    public ReportPage enterChartTypeAndClickOnFirstChart(String chartType){
        actions.click(reportTypeField);
        actions.selectDropDownByText(reportTypeDropDown, chartType);
        actions.click(searchBtn);
        actions.waitOrPause();
        reportNameList.get(0).click();
        return this;
    }

    public ReportPage enterChartType(String chartType){
        actions.click(reportTypeField);
        actions.selectDropDownByText(reportTypeDropDown, chartType);
        return this;
    }

    public ReportPage enterPrimaryModule(String moduleName){
        actions.waitOrPause();
        actions.click(primaryModuleFieldInList);
        primaryModuleDropdownList.forEach(element -> {
            try {
                if(element.getText().equals(moduleName)){
                    actions.click(element);

                }
            } catch (StaleElementReferenceException e) {

        }});

        return this;
    }

    public ReportPage clickOnFirstReportNameInList(){
        actions.waitOrPause();
        actions.jsElementClick(reportNameList.get(0));
        return this;
    }

    public ReportPage verifyNoChartDataOrCountNumberDisplayed(){
        try{
           boolean flag= noChartData.isDisplayed();
           if(flag){
               report.pass("No data info message displayed: "+noChartData.getText());
               Assert.assertTrue(true, "No chart message displayed");
           }
        }catch (Exception e){
            List<String> reportCounts = chartCountNum.stream().map(num -> num.getText()).collect(Collectors.toList());
            System.out.println(reportCounts);
            reportCounts.stream().forEach(num->{
                Integer num1=Integer.parseInt(num);
                if(num1 instanceof Integer){
                    Assert.assertTrue(true, "No chart displayed");
                }else{
                    Assert.fail("No chart displayed");
                }
            });
        }
        return this;
    }

    public ReportPage clickOnSearchBtn(){
        for(int i=0;i<20;i++) {
            try {
                System.out.println("searchBtn");
                actions.click(searchBtn);
                break;
            }catch (Exception e){
                actions.jsElementClick(searchBtn);
                break;
            }
        }
        return this;
    }

    public ReportPage verifyTheReportCountNumbersAreDisplayed(){
        List<String> reportCounts = chartCountNum.stream().map(num -> num.getText()).collect(Collectors.toList());
        System.out.println(reportCounts);
        reportCounts.stream().forEach(num->{
            Integer num1=Integer.parseInt(num);
            if(num1 instanceof Integer){
              report.pass("Graph numbers are displaying");
            }else{
                report.fail("numbers are not displayed");
            }
        });
        return this;
    }

    public ReportPage enterAllCondtionFields(String field, String comparefield){
        Actions act=new Actions(driver);
        act.click(conditionSelectField).sendKeys(field, Keys.ENTER).build().perform();
        act.click(compareField).sendKeys(comparefield).build().perform();
        return this;
    }

    public ReportPage selectGroupByField(String groupName){
        actions.waitOrPause();
        Actions act=new Actions(driver);
        act.click(selectGropByField).sendKeys(groupName, Keys.ENTER).build().perform();
        return this;
    }

    public ReportPage clickOnGenerateReport(){
        actions.click(saveAndgenerateReport);
        return this;
    }

//    public ReportPage clickOnChartBar(){
//        int count=0;
//        for(WebElement ele: chartCountNum){
//            System.out.println(ele.getLocation());
//            Actions act=new Actions(driver);
//           act.moveToElement(barGraph, 0, chartCountNum.get(count++).getLocation().getY()).click().perform();
//            System.out.println(pageNumberText.getText()+"==>"+ReportNum.get(count++));
//            driver.navigate().back();
//        }
//        return this;
//    }

    public void VerifyReportNameAndOwnerName(String reportname, String owner){
        report.info("verifing report name and owner name");
        actions.type(reportName, reportname, Keys.ENTER);
        actions.waitOrPause();
        Assert.assertEquals(reportname, reportTxt.getText().trim());
        Assert.assertTrue( reportOwner.getText().trim().startsWith(owner), "owner name is not matching");
    }

}

