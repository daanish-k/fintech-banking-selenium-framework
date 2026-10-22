package com.parabank.tests;

import com.parabank.models.Customer;
import com.parabank.pages.AccountOverviewPage;
import com.parabank.pages.LoanRequestPage;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoanApplicationTest extends BaseTest {

    @Test(description = "Verify that a viable loan request is automatically approved by the banking engine",
          groups = {"smoke", "regression"})
    public void testLoanApprovalWorkflow() {
        LoginPage loginPage = new LoginPage(getDriver());
        RegistrationPage registrationPage = loginPage.clickRegister();

        // 1. Setup authenticated customer
        String timestamp = String.valueOf(System.currentTimeMillis());
        Customer customer = new Customer(
                "Casey", "Smith", "600 Financial Park", "Chicago", "IL", "60601",
                "3125550133", "666-33-1122", "casey_" + timestamp, "LoanPass@123"
        );
        AccountOverviewPage overviewPage = registrationPage.registerCustomer(customer);

        // 2. Navigate to Loan Request
        LoanRequestPage loanPage = overviewPage.clickRequestLoan();

        // 3. Apply for a reasonable loan ($1000 loan with $200 down payment)
        loanPage.applyForLoan("1000", "200");

        // 4. Assert loan approval
        Assert.assertTrue(loanPage.isLoanApproved(),
                "Expected loan status to be 'Approved', but was: " + loanPage.getLoanStatus());

        String newAccountId = loanPage.getNewLoanAccountNumber();
        Assert.assertFalse(newAccountId.isEmpty(),
                "Approved loan should generate a new account number, but none was found.");
    }
}
