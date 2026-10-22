package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * OOP Concept: Multi-level Inheritance (Extends BankingNavigationPage -> BasePage)
 * Handles customer balance overview and account reconciliation.
 */
public class AccountOverviewPage extends BankingNavigationPage {

    private final By pageTitle = By.cssSelector("h1.title");
    private final By accountRows = By.cssSelector("table#accountTable tbody tr");
    private final By firstAccountLink = By.cssSelector("table#accountTable tbody tr:first-child td a");
    private final By firstAccountBalance = By.cssSelector("table#accountTable tbody tr:first-child td:nth-child(2)");
    private final By totalBalance = By.cssSelector("table#accountTable tr td b.total, table#accountTable tbody tr:last-child td:nth-child(2)");

    public AccountOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeading() {
        return getText(pageTitle);
    }

    public boolean isAccountOverviewLoaded() {
        return isElementDisplayed(pageTitle) && isElementDisplayed(firstAccountLink);
    }

    public String getFirstAccountNumber() {
        return getText(firstAccountLink);
    }

    public String getFirstAccountBalance() {
        return getText(firstAccountBalance);
    }

    public int getAccountCount() {
        List<WebElement> rows = driver.findElements(accountRows);
        // Exclude the footer row (Total)
        return Math.max(0, rows.size() - 1);
    }
}
