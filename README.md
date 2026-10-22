# ParaBank FinTech & Banking Test Automation Framework

An enterprise-grade test automation framework built using **Java 17**, **Selenium WebDriver 4**, and **TestNG**, implementing the **Page Object Model (POM)** design pattern with strong adherence to **Object-Oriented Programming (OOP)** principles.

Automating realistic banking workflows on [ParaBank](https://parabank.parasoft.com/parabank/index.htm) by Parasoft.

---

## 🏛️ Framework Architecture & Design Patterns

* **Page Object Model (POM):** Decouples test logic from UI locators and DOM structure.
* **OOPs Implementation:**
  * **Encapsulation:** WebElements and `By` locators are declared `private`; UI interactions and validations are exposed via public page methods. Sensitive banking entities are modeled as strongly typed POJOs (`Customer`, `Payee`).
  * **Inheritance:** `BasePage` provides reusable explicit wait and interaction mechanisms. `BaseTest` manages browser setup and teardown.
  * **Polymorphism:** Method overloading for dynamic element lookups and transaction searches; TestNG `ITestListener` method overriding for automatic failure screenshot capture.
  * **Abstraction:** Hides raw Selenium driver mechanics and synchronization waits behind high-level business actions.
* **Data-Driven Testing (DDT):** TestNG `@DataProvider` for parameterized multi-case validations.
* **Reporting:** ExtentReports for interactive HTML test execution dashboards with embedded failure screenshots.

---

## 🧪 Automated Banking Scenarios

1. **Authentication & Profile Management:**
   * Customer Registration & Valid Login.
   * Negative login validation with parameterized invalid credentials.
2. **Account Balances & Ledger Reconciliation:**
   * Checking dynamic balances across Checking and Savings accounts.
3. **Inter-Account Fund Transfers:**
   * Transferring funds between accounts and verifying balance deductions and credits.
4. **Bill Payment Operations:**
   * Full payee validation, address details, phone, and account number reconciliation.
5. **Automated Loan Request Logic:**
   * Validation of automated loan approval vs. denial logic based on down payment and current balance.

---

## 📁 Project Directory Layout

```text
├── src
│   ├── main/java/com/parabank
│   │   ├── models/            # Banking Data POJOs (Customer, Payee, TransferDetails)
│   │   ├── pages/             # Page Object classes (LoginPage, TransferPage, etc.)
│   │   └── utils/             # Framework utilities (DriverManager, ScreenshotUtil, ExtentManager)
│   └── test/java/com/parabank
│       ├── listeners/         # TestNG Listeners for reporting & failure screenshots
│       └── tests/             # Automated TestNG Test classes
│   └── test/resources/
│       └── testng.xml         # Test suite execution suite
├── pom.xml                    # Maven configuration and dependencies
└── README.md                  # Project documentation
```

---

## 🚀 How to Run the Tests

```bash
# Execute the complete test suite
mvn clean test

# Execute with local repository on D: drive (space saving)
mvn clean test -Dmaven.repo.local=D:\.m2\repository
```
