package ch.springframeworkguru.spring6webapp.ui;

import ch.springframeworkguru.spring6webapp.domain.Book;
import ch.springframeworkguru.spring6webapp.domain.Publisher;
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
class BookListPageIT {

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
    void testBeerListPageLoads() {
        webDriver.get("http://localhost:" + port + "/books");
        waitForPageLoad();

        // Wait for the table to be present
        WebElement bookTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bookTable")));
        List<WebElement> bookRows = bookTable.findElements(By.cssSelector("tbody tr"));

        List<Book> books = new ArrayList<>();
        for (WebElement row : bookRows) {
            String id = row.findElement(By.cssSelector("[id^='bookId-']")).getText();
            String title = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bookTitle-" + id))).getText();
            String publisher = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bookPublisher-" + id))).getText();
            books.add(Book.builder()
                .id(Long.parseLong(id))
                    .title(title)
                    .publisher(Publisher.builder()
                        .publisherName(publisher)
                        .build())
                .build());
        }
        
        assertAll(
            () -> assertEquals("Book List", webDriver.getTitle()),
            () -> assertEquals(2, bookRows.size()),
            () -> assertEquals(new HashSet<>(Arrays.asList("Domain Driven Design", "J2EE Development without EJB")),
                               books.stream().map(Book::getTitle).collect(Collectors.toSet()))
        );
    }

    private void waitForPageLoad() {
        WebDriverWait pageWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        pageWait.until((ExpectedCondition<Boolean>) wd ->
            Objects.equals(((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState"), "complete"));
    }
}
