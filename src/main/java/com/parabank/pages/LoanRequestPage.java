package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * OOP Concept: Multi-level Inheritance (Extends BankingNavigationPage)
 * Handles automated loan applications and business credit evaluation logic.
 */
public class LoanRequestPage extends BankingNavigationPage {

    private final By amountInput = By.id("amount");
    private final By downPaymentInput = By.id("downPayment");
    private final By fromAccountIdSelect = By.id("fromAccountId");
    private final By applyNowButton = By.cssSelector("input[value='Apply Now']");
    private final By loanStatus = By.id("loanStatus");
    private final By newAccountIdLink = By.id("newAccountId");
    private final By loanResponseHeading = By.cssSelector("div#requestLoanResult h1.title");
    private final By errorMessage = By.cssSelector("div#requestLoanError p.error");

    public LoanRequestPage(WebDriver driver) {
        super(driver);
    }

    public LoanRequestPage applyForLoan(String loanAmount, String downPayment) {
        type(amountInput, loanAmount);
        type(downPaymentInput, downPayment);
        click(applyNowButton);
        return this;
    }

    public LoanRequestPage applyForLoan(String loanAmount, String downPayment, String fromAccount) {
        type(amountInput, loanAmount);
        type(downPaymentInput, downPayment);
        selectByVisibleText(fromAccountIdSelect, fromAccount);
        click(applyNowButton);
        return this;
    }

    public String getLoanStatus() {
        return getText(loanStatus);
    }

    public boolean isLoanApproved() {
        return isElementDisplayed(loanStatus) && "Approved".equalsIgnoreCase(getLoanStatus());
    }

    public boolean isLoanDenied() {
        return isElementDisplayed(loanStatus) && "Denied".equalsIgnoreCase(getLoanStatus());
    }

    public String getNewLoanAccountNumber() {
        return getText(newAccountIdLink);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
