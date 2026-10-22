package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * OOP Concept: Multi-level Inheritance
 * Extends BasePage to represent shared navigation for all authenticated banking screens.
 * Subclasses: AccountOverviewPage, TransferFundsPage, BillPayPage, LoanRequestPage.
 */
public class BankingNavigationPage extends BasePage {

    // Encapsulated persistent navigation locators
    private final By accountsOverviewLink = By.linkText("Accounts Overview");
    private final By transferFundsLink = By.linkText("Transfer Funds");
    private final By billPayLink = By.linkText("Bill Pay");
    private final By requestLoanLink = By.linkText("Request Loan");
    private final By logOutLink = By.linkText("Log Out");
    private final By welcomeUserBanner = By.cssSelector("p.smallText");

    public BankingNavigationPage(WebDriver driver) {
        super(driver);
    }

    public AccountOverviewPage clickAccountsOverview() {
        click(accountsOverviewLink);
        return new AccountOverviewPage(driver);
    }

    public TransferFundsPage clickTransferFunds() {
        click(transferFundsLink);
        return new TransferFundsPage(driver);
    }

    public BillPayPage clickBillPay() {
        click(billPayLink);
        return new BillPayPage(driver);
    }

    public LoanRequestPage clickRequestLoan() {
        click(requestLoanLink);
        return new LoanRequestPage(driver);
    }

    public LoginPage clickLogout() {
        click(logOutLink);
        return new LoginPage(driver);
    }

    public String getWelcomeUserBannerText() {
        return getText(welcomeUserBanner);
    }
}
