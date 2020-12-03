package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getSignupAndLogin() {
		String username = "nishant";
		String password = "testnishant";
		driver.get("http://localhost:" + port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("nishant", "hulwan", username, password);
		driver.get("http://localhost:" + port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:" + this.port + "/login?logout");
		//Assertions.assertEquals("http://localhost:" + this.port + "/login?logout", driver.getCurrentUrl());
		Assertions.assertEquals("You have been logged out", driver.findElement(By.id("logout-msg")).getText());

	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}


	@Test
	public void getHomePage() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testRandomPage() {
		driver.get("http://localhost:" + this.port + "/test");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	public void createNote(NotePage notePage, String title, String description) {
		notePage.openNoteTabJS();
		notePage.openModalJS();
		notePage.createNoteJS(title, description);
		notePage.saveNoteJS();
	}

	@Test
	public void createNoteTest() {
		getSignupAndLogin();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "Note title", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New note added !", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		Assertions.assertEquals("Note title", notePage.getTableNoteTitle());
	}

	@Test
	public void editNoteTest() {
		getSignupAndLogin();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "Note title", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New note added !", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		notePage.editNoteJS();
		notePage.createNoteJS("noteTitle", "Edited note description");
		notePage.saveNoteJS();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New note added !", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		Assertions.assertEquals("noteTitle", notePage.getTableNoteTitle());
	}

	@Test
	public void deleteNoteTest() {
		getSignupAndLogin();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "Note title", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New note added !", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		notePage.deleteNoteJS();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Note deleted!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTabJS();
		Assertions.assertEquals(false, notePage.hasNotes());

	}

	public void createCredential(CredentialPage credentialPage, String url, String username, String password) {

		credentialPage.openCredentialModal();
		credentialPage.openCredentialTab();
		credentialPage.createCredential(url, username, password);
		credentialPage.saveCredentials();

	}

	@Test
	public void createCredentialTest() {

		getSignupAndLogin();
		CredentialPage credentialPage = new CredentialPage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createCredential(credentialPage, "credential url", "credential username", "credential password");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New credential added!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		Assertions.assertEquals("credential url", credentialPage.getCredentialUrl());
	}

	@Test
	public void editCredentialTest() {
		getSignupAndLogin();
		CredentialPage credentialPage = new CredentialPage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createCredential(credentialPage, "Crendetial url", "credential username", "credential password");
		Assertions.assertEquals("Result", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		credentialPage.editCredential();
		credentialPage.createCredential("Edited credential url", "Edited credential username", "Edited credential password");
		credentialPage.saveCredentials();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New credential added!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		Assertions.assertEquals("Edited credential url", credentialPage.getCredentialUrl());
	}

	@Test
	public void deleteCredentialTest() {
		getSignupAndLogin();
		CredentialPage credentialPage = new CredentialPage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createCredential(credentialPage, "credential url", "credential username", "credential password");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New credential added!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		credentialPage.deleteCredential();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Credentials deleted!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		credentialPage.openCredentialTab();
		Assertions.assertEquals(false, credentialPage.hasCredentials());

	}


}
