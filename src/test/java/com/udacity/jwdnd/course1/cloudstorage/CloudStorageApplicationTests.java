package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

	public void createNote(NotePage notePage, String title, String description) {
		notePage.openNoteTab();
		notePage.openModal();
		notePage.createNote(title, description);
		notePage.saveNote();
	}

	@Test
	public void createNoteTest() {
		getSignupAndLogin();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "notetitle", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New note added !", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTab();
		Assertions.assertEquals("notetitle", notePage.getNoteTitle());
	}

	@Test
	public void editNoteTest() {
		getSignupAndLogin();
		NotePage notePage = new NotePage(driver);
		ResultPage resultPage = new ResultPage(driver);
		createNote(notePage, "notetitle", "Note description");
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("New note added !", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTab();
		notePage.editNote();
		notePage.createNote("Edited note title", "Edited note description");
		notePage.saveNote();
		Assertions.assertEquals("Result", driver.getTitle());
		Assertions.assertEquals("Note updated!", resultPage.getSuccessMessage());
		driver.get("http://localhost:" + this.port + "/home");
		notePage.openNoteTab();
		Assertions.assertEquals("Edited note title", notePage.getNoteTitle());

	}




}
