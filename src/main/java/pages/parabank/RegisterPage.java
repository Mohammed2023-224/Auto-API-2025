package pages.parabank;

import engine.actions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
    }
// can be consolidated in one dynamic method returning by class
    private final By firstNameField=By.name("customer.firstName");
    private final By lastNameField=By.name("customer.lastName");
    private final By AddressField=By.name("customer.address.street");
    private final By cityField=By.name("customer.address.city");
    private final By stateField=By.name("customer.address.state");
    private final By zipCodeField=By.name("customer.address.zipCode");
    private final By phoneField=By.name("customer.phoneNumber");
    private final By ssnField=By.name("customer.ssn");
    private final By userNameField=By.name("customer.username");
    private final By passwordField=By.name("customer.password");
    private final By confirmField=By.name("repeatedPassword");
    private final By registerButton=By.xpath("//input[@value='Register']");
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



    @Step("Type in city field [{text}]")
    public void typeInCityField(String text){
        ElementActions.typeInElement(driver,cityField,text);
    }



    @Step("Type in state field [{text}]")
    public void typeInStateField(String text){
        ElementActions.typeInElement(driver,stateField,text);
    }



    @Step("Type in phone field [{text}]")
    public void typeInPhoneField(String text){
        ElementActions.typeInElement(driver,phoneField,text);
    }


    @Step("Type in user name field [{text}]")
    public void typeInUserNameField(String text){
        ElementActions.typeInElement(driver,userNameField,text);
    }

    @Step("Type in password field [{text}]")
    public void typeInPasswordField(String text){
        ElementActions.typeInElement(driver,passwordField,text);
    }


    @Step("Type in confirm password field [{text}]")
    public void typeConfirmPasswordInField(String text){
        ElementActions.typeInElement(driver,confirmField,text);
    }


    @Step("Type in zip code field [{text}]")
    public void typeInZipCodeField(String text){
        ElementActions.typeInElement(driver,zipCodeField,text);
    }

    @Step("Type in address field [{text}]")
    public void typeInAddressField(String text){
        ElementActions.typeInElement(driver,AddressField,text);
    }
    @Step("click register ")
    public void clickRegisterButton( ){
        ElementActions.clickElement(driver,registerButton);
    }

}
