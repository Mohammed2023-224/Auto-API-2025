import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.parabank.*;

import java.util.ArrayList;

public class ParaBankTests extends BaseTest{
 HomePage homePage;
 RegisterPage registerPage;
 AccountView accountView;
OpenAccount openAccount;
    TransferFunds transferFunds;
    BillPay billPay;
    UpdateContactInfo updateContactInfo;
    RequestLoan requestLoan;
    ForgotLoginInfoPage forgotLoginInfoPage;

    /* Not the best approach for test cases as all test cases must be independent, focus on certain test case,
     with dynamic data and relatively short
     Can be enhanced using singleton pattern or the correct usage of fluent design pattern and to read data from external
     file (Excel , Json) and to shorten test cases to be independent and relatively short
     */

@Test
public void FullJourneyTest(){
    String loginInfo="test27";
    homePage.navigateToHomePage();
    homePage.clickUserName(loginInfo);
    homePage.clickPassword(loginInfo);
    homePage.clickLoginLink();
    homePage.assertError(homePage.getErrorText());
    homePage.clickRegisterLink();
    registerPage.clickRegisterButton();
    registerPage.assertMissingFirstNameError();
    registerPage.typeInLastNameField("test");
    registerPage.typeInAddressField("test");
    registerPage.typeInCityField("test");
    registerPage.typeInStateField("test");
    registerPage.typeInSSNField("test");
    registerPage.typeInZipCodeField("test");
    registerPage.typeInPhoneField("test");
    registerPage.typeInUserNameField("test1");
    registerPage.typeInPasswordField("test1");
    registerPage.typeConfirmPasswordInField("testing");
    registerPage.clickRegisterButton();
    registerPage.assertNotMatchingPassword();
    registerPage.assertMissingFirstNameError();
    registerPage.typeInFirstNameField("test");
    registerPage.typeInLastNameField("test");
    registerPage.typeInAddressField("test");
    registerPage.typeInCityField("test");
    registerPage.typeInStateField("test");
    registerPage.typeInSSNField("test");
    registerPage.typeInZipCodeField("test");
    registerPage.typeInPhoneField("test");
    registerPage.typeInUserNameField(loginInfo);
    registerPage.typeInPasswordField(loginInfo);
    registerPage.typeConfirmPasswordInField(loginInfo);
    registerPage.clickRegisterButton();
    registerPage.assertWelcomeMessage();
    homePage.clickBodyInfoLink("Accounts Overview");
    String totalBalance=accountView.getTotalBalance();
    ArrayList<String> accounts=accountView.getAccountNumber();
    String accountBalance=accountView.getCertainAccountAccountBalance(accounts.get(0));
    accountView.clickAccountBalance();
    String currentBalance=accountView.getAccountBalance();
    String availableBalance=accountView.getAccountAvailableBalance();
    String accountType=accountView.getAccountType();
    Assert.assertTrue(accountBalance.equalsIgnoreCase(currentBalance));
    Assert.assertTrue(accountType.equalsIgnoreCase("checking"));
    Assert.assertTrue(accounts.get(0).equalsIgnoreCase(accountView.getAccountID()));
    homePage.clickBodyInfoLink("Open New Account");
    openAccount.selectAccountType("SAVINGS");
    openAccount.selectFirstAccount();
    openAccount.clickOpenAccount();
    String newAccount=openAccount.getAccountID();
    accounts.add(newAccount);
    int newSize=Math.toIntExact(accounts.stream().count());
    openAccount.clickAccountID();
    Assert.assertTrue(accountView.getAccountBalance().contains("100"));
    Assert.assertTrue(accountView.getAccountType().equalsIgnoreCase("savings"));
    Assert.assertTrue(accounts.get(newSize-1).equalsIgnoreCase(newAccount));
    homePage.clickBodyInfoLink("Transfer Funds");
    transferFunds.typeAmount("300");
    transferFunds.selectFromAccount(accounts.get(0));
    transferFunds.selectToAccount(accounts.get(newSize-1));
    transferFunds.clickTransferButton();
    transferFunds.assertTransferIsCompleted();
    homePage.clickBodyInfoLink("Bill Pay");
    billPay.typeAmount("2000");
    billPay.chooseAccount(accounts.get(0));
    billPay.typeAccount("testingAccount");
    billPay.clickSendPayment();
    billPay.assertMissingNameError();
    billPay.typeAmount("2000");
    billPay.chooseAccount(accounts.get(0));
    billPay.typeAccount("55");
    billPay.typePayeeName("testing");
    billPay.typePhone("yr");
    billPay.typeVerifyAccount("55");
    billPay.typeZipCode("test");
    billPay.typeState("test");
    billPay.typeCity("test");
    billPay.typeAddress("yesy");
    billPay.clickSendPayment();
    billPay.assertCompletePayment();
    homePage.clickBodyInfoLink("Update Contact Info");
    updateContactInfo.typeAddress("testing");
    updateContactInfo.typeCity("testing");
    updateContactInfo.typePhone("testing");
    updateContactInfo.typeFirstName("testing");
    updateContactInfo.typeLastName("testing");
    updateContactInfo.typeState("testing");
    updateContactInfo.typeZipCode("testing");
    updateContactInfo.clickUpdate();
    updateContactInfo.assertUpdate();
    homePage.clickBodyInfoLink("Request Loan");
    requestLoan.typeAmount("5000");
    requestLoan.typeDownPayment("6000000");
    requestLoan.selectAccount(accounts.get(0));
    requestLoan.clickApply();
    requestLoan.deniedAssertion();
    homePage.clickBodyInfoLink("Log Out");
    homePage.clickUserName("test");
    homePage.clickPassword("test");
    homePage.clickLoginLink();
    homePage.clickBodyInfoLink("Log Out");
    homePage.clickForgetInfoLink();
    forgotLoginInfoPage.typeInAddressField("test");
    forgotLoginInfoPage.typeInCityField("test");
    forgotLoginInfoPage.typeInSSNField("test");
    forgotLoginInfoPage.typeInLastNameField("test");
    forgotLoginInfoPage.typeInFirstNameField("test");
    forgotLoginInfoPage.typeInStateField("test");
    forgotLoginInfoPage.typeInZipCodeField("test");
    forgotLoginInfoPage.clickFindLoginInfo();


}

    @BeforeClass
    private void initObjects(){
        homePage=new HomePage(getDriver());
        registerPage=new RegisterPage(getDriver());
        accountView=new AccountView(getDriver());
        openAccount=new OpenAccount(getDriver());
        transferFunds=new TransferFunds(getDriver());
        billPay=new BillPay(getDriver());
        updateContactInfo=new UpdateContactInfo(getDriver());
        requestLoan=new RequestLoan(getDriver());
        forgotLoginInfoPage=new ForgotLoginInfoPage(getDriver());
    }
}
