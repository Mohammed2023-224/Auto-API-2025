package pages.parabank;

import engine.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotLoginInfoPage {

    private final WebDriver driver;

    public ForgotLoginInfoPage(WebDriver driver){
        this.driver=driver;
    }

    private final By firstNameField=By.name("firstName");
    private final By lastNameField=By.name("lastName");
    private final By AddressField=By.name("address.street");
    private final By cityField=By.name("address.city");
    private final By stateField=By.name("address.state");
    private final By zipCodeField=By.name("address.zipCode");
    private final By ssnField=By.name("ssn");
    private final By findInfoButton=By.xpath("//input[@value='Find My Login Info']");


    @Step("Type in first name field [{text}]")
    public void typeInFirstNameField(String text){
        ElementActions.typeInElement(driver,firstNameField,text);
    }

    @Step("Type in last name field [{text}]")
    public void typeInLastNameField(String text){
        ElementActions.typeInElement(driver,lastNameField,text);
    }


    @Step("Type in SSN field [{text}]")
    public void typeInSSNField(String text){
        ElementActions.typeInElement(driver,ssnField,text);
    }


    @Step("Type in zip code field [{text}]")
    public void typeInZipCodeField(String text){
        ElementActions.typeInElement(driver,zipCodeField,text);
    }

    @Step("Type in address field [{text}]")
    public void typeInAddressField(String text){
        ElementActions.typeInElement(driver,AddressField,text);
    }

    @Step("Type in city field [{text}]")
    public void typeInCityField(String text){
        ElementActions.typeInElement(driver,cityField,text);
    }



    @Step("Type in state field [{text}]")
    public void typeInStateField(String text){
        ElementActions.typeInElement(driver,stateField,text);
    }
    @Step("click find login info ")
    public void clickFindLoginInfo( ){
        ElementActions.clickElement(driver,findInfoButton);
    }


}
