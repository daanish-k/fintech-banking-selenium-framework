package com.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * OOP Concept: Inheritance (Parent for all Page classes)
 * OOP Concept: Abstraction (Hides low-level WebDriver synchronization from test classes)
 * OOP Concept: Polymorphism (Compile-time Method Overloading for flexible waits and clicks)
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    // =========================================================================
    // POLYMORPHISM (Method Overloading) - Explicit Waits
    // =========================================================================

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForVisibility(By locator, Duration customTimeout) {
        WebDriverWait customWait = new WebDriverWait(driver, customTimeout);
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForClickability(By locator, Duration customTimeout) {
        WebDriverWait customWait = new WebDriverWait(driver, customTimeout);
        return customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // =========================================================================
    // POLYMORPHISM (Method Overloading) - Click Actions
    // =========================================================================

    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // =========================================================================
    // Input & Inspection Utilities
    // =========================================================================

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            return waitForVisibility(locator, Duration.ofSeconds(5)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void selectByVisibleText(By locator, String visibleText) {
        WebElement element = waitForVisibility(locator);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
