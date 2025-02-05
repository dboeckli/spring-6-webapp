package ch.springframeworkguru.spring6webapp.ui;

import ch.springframeworkguru.spring6webapp.domain.Author;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class AuthorListPageIT {

    @LocalServerPort
    private int port;

    private WebDriver webDriver;

    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Run in headless mode
        webDriver = new ChromeDriver(options);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @Order(0)
    void testAuthorListPageLoads() {
        webDriver.get("http://localhost:" + port + "/authors");
        waitForPageLoad();

        // Wait for the table to be present
        WebElement authorTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("authorTable")));
        List<WebElement> authorRows = authorTable.findElements(By.cssSelector("tbody tr"));

        List<Author> authors = new ArrayList<>();
        for (WebElement row : authorRows) {
            String id = row.findElement(By.cssSelector("[id^='authorId-']")).getText();
            String firstName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("authorFirstName-" + id))).getText();
            String lastName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("authorLastName-" + id))).getText();
            authors.add(Author.builder()
                .id(Long.parseLong(id))
                    .firstName(firstName)
                    .lastName(lastName)
                .build());
        }

        assertAll(
            () -> assertEquals("Author List", webDriver.getTitle()),
            () -> assertEquals(2, authorRows.size()),
            () -> assertEquals(new HashSet<>(Arrays.asList("Eric", "Rod")),
                authors.stream().map(Author::getFirstName).collect(Collectors.toSet()))
        );
    }

    private void waitForPageLoad() {
        WebDriverWait pageWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        pageWait.until((ExpectedCondition<Boolean>) wd ->
            Objects.equals(((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState"), "complete"));
    }
}
