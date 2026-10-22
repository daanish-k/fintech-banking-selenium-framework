package com.parabank.pages;

import com.parabank.models.Customer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * OOP Concept: Inheritance (Extends BasePage)
 * Demonstrates interaction with Encapsulated domain model (Customer POJO)
 */
public class RegistrationPage extends BasePage {

    private final By firstNameField = By.id("customer.firstName");
    private final By lastNameField = By.id("customer.lastName");
    private final By addressField = By.id("customer.address.street");
    private final By cityField = By.id("customer.address.city");
    private final By stateField = By.id("customer.address.state");
    private final By zipCodeField = By.id("customer.address.zipCode");
    private final By phoneField = By.id("customer.phoneNumber");
    private final By ssnField = By.id("customer.ssn");
    private final By usernameField = By.id("customer.username");
    private final By passwordField = By.id("customer.password");
    private final By confirmPasswordField = By.id("repeatedPassword");
    private final By registerButton = By.cssSelector("input[value='Register']");
    private final By successMessage = By.cssSelector("div#rightPanel p");
    private final By welcomeTitle = By.cssSelector("h1.title");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public AccountOverviewPage registerCustomer(Customer customer) {
        type(firstNameField, customer.getFirstName());
        type(lastNameField, customer.getLastName());
        type(addressField, customer.getAddress());
        type(cityField, customer.getCity());
        type(stateField, customer.getState());
        type(zipCodeField, customer.getZipCode());
        type(phoneField, customer.getPhone());
        type(ssnField, customer.getSsn());
        type(usernameField, customer.getUsername());
        type(passwordField, customer.getPassword());
        type(confirmPasswordField, customer.getPassword());
        click(registerButton);
        return new AccountOverviewPage(driver);
    }

    public String getWelcomeTitle() {
        return getText(welcomeTitle);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
