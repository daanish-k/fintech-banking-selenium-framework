package com.parabank.tests;

import com.parabank.models.Customer;
import com.parabank.pages.AccountOverviewPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] getInvalidCredentials() {
        return new Object[][]{
                {"invalidUser999", "wrongPassword", "The username and password could not be verified."},
                {"", "emptyUsernamePassword", "Please enter a username and password."},
                {"admin", "", "Please enter a username and password."}
        };
    }

    @Test(description = "Verify that invalid login attempts display appropriate banking error notices",
          dataProvider = "invalidLoginCredentials",
          groups = {"regression"})
    public void testNegativeLoginScenarios(String username, String password, String expectedErrorSnippet) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure(username, password);

        String actualError = loginPage.getErrorMessage();
        Assert.assertFalse(actualError.isEmpty(), "Expected an error message for invalid login, but found none.");
        Assert.assertTrue(actualError.contains(expectedErrorSnippet) || actualError.contains("could not be verified"),
                "Error message did not match expected pattern. Actual: " + actualError);
    }

    @Test(description = "Verify that a registered user can log in and view their account summary",
          groups = {"smoke", "regression"})
    public void testSuccessfulLoginAndLogout() {
        LoginPage loginPage = new LoginPage(getDriver());

        // Pre-condition: Register a new customer
        RegistrationPage registrationPage = loginPage.clickRegister();
        String timestamp = String.valueOf(System.currentTimeMillis());
        Customer customer = new Customer(
                "Jordan", "Lee", "456 Wall St", "New York", "NY", "10005",
                "2125550144", "987-65-4321", "jordan_" + timestamp, "FintechPass@2026"
        );
        AccountOverviewPage overviewPage = registrationPage.registerCustomer(customer);

        // Perform Logout
        LoginPage returnedLoginPage = overviewPage.clickLogout();
        Assert.assertTrue(returnedLoginPage.isLoginFormDisplayed(),
                "User was not redirected to login page after logging out.");

        // Re-login with the created customer credentials
        AccountOverviewPage reLoggedInPage = returnedLoginPage.loginAs(customer.getUsername(), customer.getPassword());
        Assert.assertTrue(reLoggedInPage.isAccountOverviewLoaded(),
                "Accounts overview page did not load after valid customer login.");
    }
}
