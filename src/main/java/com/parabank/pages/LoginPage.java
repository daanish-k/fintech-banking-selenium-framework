package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * OOP Concept: Inheritance (Extends BasePage)
 * OOP Concept: Encapsulation (Private locators, public business actions)
 * Design Pattern: Page Chaining (login returns AccountOverviewPage)
 */
public class LoginPage extends BasePage {

    // Encapsulated private locators
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("input[value='Log In']");
    private final By registerLink = By.linkText("Register");
    private final By errorMessage = By.cssSelector("p.error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public AccountOverviewPage loginAs(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return new AccountOverviewPage(driver);
    }

    public LoginPage loginExpectingFailure(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return this;
    }

    public RegistrationPage clickRegister() {
        click(registerLink);
        return new RegistrationPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isLoginFormDisplayed() {
        return isElementDisplayed(usernameField) && isElementDisplayed(passwordField);
    }
}
