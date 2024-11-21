package stepdefinitions;

import com.example.TodoApp.TodoAppApplication;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
public class LoginStepDefinitions {
    private final int port = 8080;
    WebDriver driver;

    @Before
    public void setUp() {
        // run the spring app silently
        SpringApplication.run(TodoAppApplication.class);

        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to the website")
    public void i_navigate_to_the_website() {
        driver.get("http://localhost:" + port);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @Then("I should see no added notes")
    public void i_should_see_no_added_notes() {
        WebElement notes = driver.findElement(By.id("todoList"));
        Assertions.assertEquals(0, notes.findElements(By.cssSelector("li")).size());
    }

    // I add a note with the title "Test"
    @When("I add a note with the text {string}")
    public void i_add_a_note_with_the_title(String title) {
        WebElement input = driver.findElement(By.id("todoInput"));
        input.sendKeys(title);
        driver.findElement(By.cssSelector(".form-container > button:nth-child(2)")).click();
    }

    //Then I should see the added note with the text "Test"
    @Then("I should see the added note with the text {string}")
    public void i_should_see_the_added_note_with_the_title(String title) {
        WebElement notes = driver.findElement(By.id("todoList"));
        Assertions.assertTrue(notes.isDisplayed());
        Assertions.assertEquals(1, notes.findElements(By.cssSelector("li")).size());
        Assertions.assertEquals(title, notes.findElement(By.cssSelector("li")).getText().replace("\"", ""));
    }

    // When I delete the note
    @When("I delete the note")
    public void i_delete_the_note() {
        driver.findElement(By.cssSelector(".form-container > button:nth-child(3)")).click();
    }
}