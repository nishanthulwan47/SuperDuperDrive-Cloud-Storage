package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {

    @FindBy (css = "#nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy (css = "#addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy (css = "#credential-url")
    private WebElement credentialUrl;

    @FindBy (css = "#table-credentialUrl")
    private WebElement tableCredentialUrl;

    @FindBy (css = "#credential-table-body")
    private WebElement credentialsTable;

    @FindBy (css = "#credential-username")
    private WebElement credentialUsername;

    @FindBy (css = "#credential-password")
    private WebElement credentialPassword;

    @FindBy (css = "#credentialSubmit")
    private WebElement saveCredentials;

    @FindBy (css = "#editCredentialButton")
    private WebElement editCredential;

    @FindBy (css = "#deleteCredential")
    private WebElement deleteCredential;

    private final JavascriptExecutor javascriptExecutor;

    private final WebDriverWait webDriverWait;

    public CredentialPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        webDriverWait = new WebDriverWait(webDriver, 1000);
        javascriptExecutor = (JavascriptExecutor) webDriver;

    }

    public void openCredentialTab() {
        javascriptExecutor.executeScript("arguments[0].click();", navCredentialsTab);
    }

    public void openCredentialModal() {
        javascriptExecutor.executeScript("arguments[0].click();", addCredentialButton);
    }

    public void createCredential(String url, String username, String password) {
        javascriptExecutor.executeScript("arguments[0].value='" + url + "';", credentialUrl);
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", credentialUsername);
        javascriptExecutor.executeScript("arguments[0].value='" + password + "';", credentialPassword);
    }

    public void saveCredentials() {
        javascriptExecutor.executeScript("arguments[0].click();", saveCredentials);
    }

    public void editCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", editCredential);
    }

    public void deleteCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", deleteCredential);
    }

    public String getCredentialUrl() {
        return tableCredentialUrl.getAttribute("Credential title");
    }

}
