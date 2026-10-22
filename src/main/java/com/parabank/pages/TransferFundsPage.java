package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * OOP Concept: Multi-level Inheritance (Extends BankingNavigationPage)
 * OOP Concept: Polymorphism (Method Overloading for dynamic transfer parameters)
 */
public class TransferFundsPage extends BankingNavigationPage {

    private final By amountInput = By.id("amount");
    private final By fromAccountSelect = By.id("fromAccountId");
    private final By toAccountSelect = By.id("toAccountId");
    private final By transferButton = By.cssSelector("input[value='Transfer']");
    private final By resultHeading = By.cssSelector("div#showResult h1.title");
    private final By resultMessage = By.cssSelector("div#showResult p");

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    // =========================================================================
    // POLYMORPHISM: Method Overloading for Fund Transfers
    // =========================================================================

    /**
     * Overloaded method: Transfers amount specifying both source and destination accounts.
     */
    public TransferFundsPage transferFunds(String amount, String fromAccount, String toAccount) {
        type(amountInput, amount);
        selectByVisibleText(fromAccountSelect, fromAccount);
        selectByVisibleText(toAccountSelect, toAccount);
        click(transferButton);
        return this;
    }

    /**
     * Overloaded method: Transfers amount between default selected accounts.
     */
    public TransferFundsPage transferFunds(String amount) {
        type(amountInput, amount);
        click(transferButton);
        return this;
    }

    public String getConfirmationHeading() {
        return getText(resultHeading);
    }

    public boolean isTransferComplete() {
        return isElementDisplayed(resultHeading) && getConfirmationHeading().contains("Transfer Complete");
    }

    public String getConfirmationMessage() {
        return getText(resultMessage);
    }
}
