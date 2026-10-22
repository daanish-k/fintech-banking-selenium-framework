package com.parabank.tests;

import com.parabank.models.Customer;
import com.parabank.pages.AccountOverviewPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import com.parabank.pages.TransferFundsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FundTransferTest extends BaseTest {

    @Test(description = "Verify that a customer can successfully transfer funds between accounts",
          groups = {"smoke", "regression"})
    public void testFundTransferBetweenAccounts() {
        LoginPage loginPage = new LoginPage(getDriver());
        RegistrationPage registrationPage = loginPage.clickRegister();

        // 1. Setup authenticated customer
        String timestamp = String.valueOf(System.currentTimeMillis());
        Customer customer = new Customer(
                "Taylor", "Swift", "789 Broadway", "Nashville", "TN", "37203",
                "6155550188", "555-12-3456", "taylor_" + timestamp, "TransferPass!1"
        );
        AccountOverviewPage overviewPage = registrationPage.registerCustomer(customer);

        // 2. Navigate to Transfer Funds
        TransferFundsPage transferPage = overviewPage.clickTransferFunds();

        // 3. Execute transfer
        String transferAmount = "150.00";
        transferPage.transferFunds(transferAmount);

        // 4. Assertions
        Assert.assertTrue(transferPage.isTransferComplete(),
                "Fund transfer confirmation heading was not displayed.");

        String message = transferPage.getConfirmationMessage();
        Assert.assertTrue(message.contains("$" + transferAmount) || message.contains(transferAmount),
                "Transfer confirmation does not reflect the transferred amount. Message: " + message);
    }
}
