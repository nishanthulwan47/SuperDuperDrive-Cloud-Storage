package com.udacity.jwdnd.course1.cloudstorage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(xpath = "/html/body/div/form/div[1]/div[1]/input")
    private WebElement firstNameField;

    @FindBy(xpath = "/html/body/div/form/div[1]/div[2]/input")
    private WebElement lastNameField;

    @FindBy(xpath = "/html/body/div/form/div[2]/div[1]/input")
    private WebElement usernameField;

    @FindBy(xpath = "/html/body/div/form/div[2]/div[2]/input")
    private WebElement passwordField;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
    }


}
