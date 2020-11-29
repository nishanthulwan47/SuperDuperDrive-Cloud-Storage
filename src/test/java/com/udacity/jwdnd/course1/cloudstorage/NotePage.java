package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {

    @FindBy(css = "#nav-notes-tab")
    private WebElement notesTabField;

    @FindBy(css = "#addNoteButton")
    private WebElement addNote;

    @FindBy(css = "#note-title")
    private WebElement noteTitle;

    @FindBy(css = "#table-noteTitle")
    private WebElement tableNoteTitle;

    @FindBy(css = "#notes-table-body")
    private WebElement notesTable;

    @FindBy(css = "#note-description")
    private WebElement noteDescription;

    @FindBy(css = "#noteSubmit")
    private WebElement noteSubmit;

    @FindBy(css = "editNoteButton")
    private WebElement editNote;

    @FindBy(css = "#deleteNoteButton")
    private WebElement deleteNote;

    private final JavascriptExecutor javascriptExecutor;

    private final WebDriverWait webDriverWait;

    public NotePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        webDriverWait = new WebDriverWait(webDriver, 1000);
        javascriptExecutor = (JavascriptExecutor) webDriver;

    }

    public void openNoteTab() {
        javascriptExecutor.executeScript("arguments[0].click();", notesTabField);
    }

    public void openModal() {
        javascriptExecutor.executeScript("arguments[0].click();", addNote);
    }

    public void createNote(String title, String description) {
        javascriptExecutor.executeScript("arguments[0].value='" + title + "';", noteTitle);
        javascriptExecutor.executeScript("arguments[0].value='" + description + "';", noteDescription);
    }

    public void saveNote() {
        javascriptExecutor.executeScript("arguments[0].click();", noteSubmit);
    }

    public void editNote() {
        javascriptExecutor.executeScript("arguments[0].click();", editNote);
    }

    public void deleteNote() {
        javascriptExecutor.executeScript("arguments[0].click();", deleteNote);
    }

    public String getNoteTitle() {
        return tableNoteTitle.getAttribute("Note title");
    }
  }
