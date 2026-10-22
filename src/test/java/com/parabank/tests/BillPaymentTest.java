package com.parabank.tests;

import com.parabank.models.Customer;
import com.parabank.models.Payee;
import com.parabank.pages.AccountOverviewPage;
import com.parabank.pages.BillPayPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPaymentTest extends BaseTest {

    @Test(description = "Verify that a customer can successfully pay a utility bill through online banking",
          groups = {"regression"})
    public void testOnlineBillPayment() {
        LoginPage loginPage = new LoginPage(getDriver());
        RegistrationPage registrationPage = loginPage.clickRegister();

        // 1. Setup authenticated customer
        String timestamp = String.valueOf(System.currentTimeMillis());
        Customer customer = new Customer(
                "Morgan", "Davis", "321 Tech Blvd", "Austin", "TX", "78701",
                "5125550177", "444-22-9988", "morgan_" + timestamp, "BillPay#2026"
        );
        AccountOverviewPage overviewPage = registrationPage.registerCustomer(customer);

        // 2. Navigate to Bill Pay
        BillPayPage billPayPage = overviewPage.clickBillPay();

        // 3. Setup Payee entity
        Payee utilityPayee = new Payee(
                "City Electric Utility",
                "500 Power Grid Rd",
                "Austin",
                "TX",
                "78702",
                "5125559000",
                "9876543210"
        );

        // 4. Execute Payment
        String billAmount = "85.50";
        billPayPage.payBill(utilityPayee, billAmount);

        // 5. Verification
        Assert.assertTrue(billPayPage.isBillPaymentComplete(),
                "Bill payment confirmation banner was not displayed.");

        String confirmationMsg = billPayPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMsg.contains(utilityPayee.getName()),
                "Confirmation message does not mention payee name. Message: " + confirmationMsg);
    }
}
