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
    private WebElement noteTitleField;

    @FindBy(css = "#table-noteTitle")
    private WebElement tableNoteTitle;

    @FindBy(css = "#notes-table-body")
    private WebElement notesTable;

    @FindBy(css = "#note-description")
    private WebElement noteDescriptionField;

    @FindBy(css = "#noteSubmit")
    private WebElement saveNote;

    @FindBy(css = "#editNoteButton")
    private WebElement editNote;

    @FindBy(css = "#deleteNoteButton")
    private WebElement deleteNote;

    private final JavascriptExecutor javascriptExecutor;

    private final WebDriverWait wait;

    public NotePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 1000);
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    public void openNoteTabJS() {
        javascriptExecutor.executeScript("arguments[0].click();", notesTabField);
    }

    public void openModalJS() {
        javascriptExecutor.executeScript("arguments[0].click();", addNote);
    }

    public void createNoteJS(String title, String description) {
        javascriptExecutor.executeScript("arguments[0].value='" + title + "';", noteTitleField);
        javascriptExecutor.executeScript("arguments[0].value='" + description + "';", noteDescriptionField);
    }

    public void saveNoteJS() {
        javascriptExecutor.executeScript("arguments[0].click();", saveNote);
    }

    public void editNoteJS() {
        javascriptExecutor.executeScript("arguments[0].click();", editNote);
    }

    public void deleteNoteJS() {
        javascriptExecutor.executeScript("arguments[0].click();", deleteNote);
    }

    public String getTableNoteTitle() {
        return tableNoteTitle.getAttribute("innerHTML");
    }

//    public Boolean hasNotes() {
//        List<WebElement> notesList = notesTable.findElements(By.tagName("th"));
//        try {
//            for (int i = 0; i < notesList.size(); i++) {
//                WebElement element = notesList.get(i);
//                element.findElement(By.id("table-noteTitle"));
//            }
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//
//        return true;
//    }
}

