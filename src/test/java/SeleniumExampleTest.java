import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExampleTest {
    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Инициализация хром драйвера
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test1OpenWikipedia() {

        // Открываем сайт.
        driver.get("https://ru.wikipedia.org");

        // Создаем переменную searchField и проверяем доступно ли поле поиска.
        WebElement searchField = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/form/div/input[1]"));
        Assertions.assertTrue(searchField.isEnabled(), "Search field is not clickable.");

        // Вставляем текст в поле поиск и нажимаем на значок поиска.
        WebElement btnSearch = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/form/div/input[4]"));
        searchField.sendKeys("Греция");
        btnSearch.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2LinkAvailability() {

        WebElement element = driver.findElement(By.id("n-mainpage-description"));
        // Берем ссылку из элемента через tagName
        WebElement tagName = element.findElement(By.tagName("a"));

        // Берем ссылку из элемента если она есть.
        String linkValue = null;
        if (tagName != null)
        {
            linkValue = tagName.getAttribute("href");
        }

        // Если ссылка есть, принтим ее в консоль.
        if (linkValue != null && !linkValue.isEmpty()) {
            System.out.println("Link value is available: " + linkValue);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3ParCheckAvailability() {

        // Берем содержание статьи из которой будем доставать <p>.
        WebElement article1 = driver.findElement(By.id("bodyContent"));
        // Находим первый <p> внутри этой статьи.
        WebElement firstParagraph1 = article1.findElement(By.tagName("p"));
        // Принтим контент самого первого <p> в консоль для проверки.
        System.out.println("First paragraph text: \n" + firstParagraph1.getText());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCheckAnyArticleContent(){
        // Заново декларируем поле поиска и "лупу" поиска.
        // Вставляем другое слово и нажимаем на "лупу" поиска.
        WebElement searchField = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/form/div/input[1]"));
        WebElement btnSearch = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/div/div/form/div/input[4]"));
        searchField.sendKeys("Америка");
        btnSearch.click();

        // Берем статью и принтим первый <p>,
        // чтобы удостовериться что есть содержание.
        WebElement article2 = driver.findElement(By.id("bodyContent"));
        WebElement firstParagraph2 = article2.findElement(By.tagName("p"));
        System.out.println("Any article paragraph: \n" + firstParagraph2.getText());

    }

    @Test
    public void test5ChangeLanguage() {

//        // Скроллим вниз, чтобы позднее изменить язык на странице.
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0, 500);");

        // Декларируем переменную нажимаем на элемент для смены языка.
        WebElement linkContainer = driver.findElement(By.id("mw-panel"));
        WebElement linkValue = linkContainer.findElement(By.xpath("//li[contains(@class, 'interlanguage-link') and contains(@class, 'interwiki-en')]/a"));
        String linkUrl = linkValue.getAttribute("href");
        System.out.println("English link value: \n" + linkUrl);
        linkValue.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
