package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(xpath = "/html/body/div/div/h1")
    private WebElement successMessage;

    @FindBy(xpath = "/html/body/div/div[2]/h1")
    private WebElement errorMessage;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getSuccessMessage() {
        return successMessage.getAttribute("Success");
    }

    public String getErrorMessage() {
        return errorMessage.getAttribute("Error");
    }
}
