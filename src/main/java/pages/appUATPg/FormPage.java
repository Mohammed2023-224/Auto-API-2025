package pages.appUATPg;

import engine.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FormPage {
private final WebDriver driver;
public FormPage(WebDriver driver){
    this.driver=driver;
}
private By options(String option){
    return By.xpath("//input[@value='"+option+"']");
}

public void handleForm(){
    By expField= By.id("exp");
    By expFieldAssertion= By.id("exp_help");
    By checkCheckBoxes= By.id("check_validate");
    By redValidate= By.id("rad_validate");
    By selectTool= By.id("select_tool");
    By selectToolValidate= By.id("select_tool_validate");
    By multipleSelection= By.id("select_lang");
    By multipleSelectionValidate= By.id("select_lang_validate");
    By notesField= By.id("notes");
    By notesFieldValidate= By.id("area_notes_validate");
    By onlyReadField= By.id("common_sense");
    By readGerman= By.id("german");
    By readGermanValidate= By.id("german_validate");
    By fluency= By.id("fluency");
    By fluencyValidate= By.id("fluency_validate");
    By uploadCV= By.id("upload_cv");
    By uploadCVValidate= By.id("validate_cv");
    By uploadFiles= By.id("upload_files");
    By uploadFilesValidate= By.id("validate_files");
    By downloadFile= By.id("download_file");
    By salary= By.id("salary");
    By city= By.id("validationCustom03");
    By state= By.id("validationCustom04");
    By zip= By.id("validationCustom05");
    By agree= By.id("invalidCheck");
    By nonEnglishText= By.id("नाव");
    By nonEnglishTextValidate= By.id("नाव_तपासा");
    By nonEnglishSelectionValidate= By.id("check_validate_non_english");
    By submitButton= By.tagName("button");
    ElementActions.typeInElement(driver,expField,"6");
    Assert.assertTrue(ElementActions.getText(driver,expFieldAssertion).contains("6"));
    ElementActions.clickElement(driver,options("PYTHON"));
    ElementActions.clickElement(driver,options("JAVASCRIPT"));
    Assert.assertTrue(ElementActions.getText(driver,checkCheckBoxes).contains("PYTHON JAVASCRIPT"));
    ElementActions.clickElement(driver,options("JAVASCRIPT"));
    Assert.assertTrue(ElementActions.getText(driver,checkCheckBoxes).contains("PYTHON"));
    ElementActions.clickElement(driver,options("SELENIUM"));
    Assert.assertTrue(ElementActions.getText(driver,redValidate).contains("SELENIUM"));
    ElementActions.clickElement(driver,options("PROTRACTOR"));
    Assert.assertTrue(ElementActions.getText(driver,redValidate).contains("PROTRACTOR"));
    ElementActions.selectDDLOption(driver,selectTool,"Cypress");
    Assert.assertTrue(ElementActions.getText(driver,selectToolValidate).contains("cyp"));
    ElementActions.selectDDLOption(driver,multipleSelection,"Python");
    Assert.assertTrue(ElementActions.getText(driver,multipleSelectionValidate).contains("python"));
    ElementActions.selectDDLOption(driver,multipleSelection,"Java");
    Assert.assertTrue(ElementActions.getText(driver,multipleSelectionValidate).contains("java"));
    ElementActions.pressKey(driver,multipleSelection, Keys.CONTROL);
    ElementActions.selectDDLOption(driver,multipleSelection,"TypeScript");
    Assert.assertTrue(ElementActions.getText(driver,multipleSelectionValidate).contains("java,python,typescript"));
    ElementActions.typeInElement(driver,notesField,"test");
    Assert.assertTrue(ElementActions.getText(driver,notesFieldValidate).contains("test"));
    Assert.assertTrue(driver.findElement(onlyReadField).getDomProperty("placeholder").contains("Common Sense"));
    ElementActions.clickElementUsingJavaScript(driver,readGerman);
    Assert.assertTrue(ElementActions.getText(driver,readGermanValidate).contains("true"));
    ElementActions.clickElementUsingJavaScript(driver,readGerman);
    Assert.assertTrue(ElementActions.getText(driver,readGermanValidate).contains("false"));
    ElementActions.typeInElement(driver,uploadCV,"C:\\Users\\USER\\Downloads\\Nada_Ali_Resume_updated-4.pdf");
    Assert.assertTrue(ElementActions.getText(driver,uploadCVValidate).contains("Nada_Ali_Resume_updated-4.pdf"));
    ElementActions.typeInElement(driver,uploadFiles,"C:\\Users\\USER\\Downloads\\Nada_Ali_Resume_updated-4.pdf");
    ElementActions.typeInElement(driver,uploadFiles,"C:\\Users\\USER\\Downloads\\sample_text.txt");
    Assert.assertTrue(ElementActions.getText(driver,uploadFilesValidate).contains("Nada_Ali_Resume_updated-4.pdf sample_text.txt"));
    Assert.assertTrue(driver.findElement(salary).getDomProperty("placeholder").contains("You should not provide this"));
}
}
