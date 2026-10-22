package com.parabank.pages;

import com.parabank.models.Payee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * OOP Concept: Multi-level Inheritance (Extends BankingNavigationPage)
 * OOP Concept: Encapsulation (Consumes Payee data model)
 * OOP Concept: Polymorphism (Method Overloading)
 */
public class BillPayPage extends BankingNavigationPage {

    private final By payeeNameInput = By.name("payee.name");
    private final By addressInput = By.name("payee.address.street");
    private final By cityInput = By.name("payee.address.city");
    private final By stateInput = By.name("payee.address.state");
    private final By zipCodeInput = By.name("payee.address.zipCode");
    private final By phoneInput = By.name("payee.phoneNumber");
    private final By accountInput = By.name("payee.accountNumber");
    private final By verifyAccountInput = By.name("verifyAccount");
    private final By amountInput = By.name("amount");
    private final By fromAccountIdSelect = By.name("fromAccountId");
    private final By sendPaymentButton = By.cssSelector("input[value='Send Payment']");
    private final By resultHeading = By.cssSelector("div#billpayResult h1.title");
    private final By resultMessage = By.cssSelector("div#billpayResult p:first-of-type");

    public BillPayPage(WebDriver driver) {
        super(driver);
    }

    // =========================================================================
    // POLYMORPHISM: Method Overloading for Bill Payments
    // =========================================================================

    public BillPayPage payBill(Payee payee, String amount) {
        fillPayeeDetails(payee);
        type(amountInput, amount);
        click(sendPaymentButton);
        return this;
    }

    public BillPayPage payBill(Payee payee, String amount, String fromAccount) {
        fillPayeeDetails(payee);
        type(amountInput, amount);
        selectByVisibleText(fromAccountIdSelect, fromAccount);
        click(sendPaymentButton);
        return this;
    }

    private void fillPayeeDetails(Payee payee) {
        type(payeeNameInput, payee.getName());
        type(addressInput, payee.getAddress());
        type(cityInput, payee.getCity());
        type(stateInput, payee.getState());
        type(zipCodeInput, payee.getZipCode());
        type(phoneInput, payee.getPhoneNumber());
        type(accountInput, payee.getAccountNumber());
        type(verifyAccountInput, payee.getAccountNumber());
    }

    public String getConfirmationHeading() {
        return getText(resultHeading);
    }

    public boolean isBillPaymentComplete() {
        return isElementDisplayed(resultHeading) && getConfirmationHeading().contains("Bill Payment Complete");
    }

    public String getConfirmationMessage() {
        return getText(resultMessage);
    }
}
