import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v109.network.Network;
import java.util.Optional;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExampleTest {
    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testOpenWikipedia() {
        // Open Wikipedia website
        driver.get("https://ru.wikipedia.org");

        // Find and click on search field.
        WebElement searchField = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/form/div/input[1]"));
        Assertions.assertTrue(searchField.isEnabled(), "Search field is not clickable.");

        // Input text into a search field and click search.
        searchField.sendKeys("Греция");
        WebElement btnSearch = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/form/div/input[4]"));
        btnSearch.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLinkAvailability() {

        // Enabling dev tools
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        // Dev tools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Finding the needed link
        boolean isLinkFound = driver.findElement(By.partialLinkText("/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0")).isDisplayed();
        Assertions.assertTrue(isLinkFound);
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
