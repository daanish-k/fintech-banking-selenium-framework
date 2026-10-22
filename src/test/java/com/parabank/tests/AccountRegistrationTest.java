package com.parabank.tests;

import com.parabank.models.Customer;
import com.parabank.pages.AccountOverviewPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountRegistrationTest extends BaseTest {

    @Test(description = "Verify that a new customer can successfully register an online banking account",
          groups = {"smoke", "regression"})
    public void testCustomerRegistrationSuccessful() {
        LoginPage loginPage = new LoginPage(getDriver());
        RegistrationPage registrationPage = loginPage.clickRegister();

        // Create a unique customer entity to avoid username conflicts
        String timestamp = String.valueOf(System.currentTimeMillis());
        Customer newCustomer = new Customer(
                "Alex",
                "Morgan",
                "100 Financial Way",
                "Charlotte",
                "NC",
                "28202",
                "7045550199",
                "123-45-" + timestamp.substring(timestamp.length() - 4),
                "finuser_" + timestamp,
                "SecureBank@123"
        );

        AccountOverviewPage overviewPage = registrationPage.registerCustomer(newCustomer);

        // Verification assertions
        String welcomeHeading = registrationPage.getWelcomeTitle();
        Assert.assertTrue(welcomeHeading.contains("Welcome " + newCustomer.getUsername()),
                "Expected welcome heading with username, but got: " + welcomeHeading);

        String successMessage = registrationPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("Your account was created successfully"),
                "Registration success message was not displayed properly");
    }
}
