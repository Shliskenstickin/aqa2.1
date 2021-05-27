package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.*;

public class AppOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void SetUp() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void FormTest01() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Радаев Иван");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79990009900");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(className("button__text")).click();
        assertTrue(!driver.findElements(cssSelector("[data-test-id=\"order-success\"]")).isEmpty(),
                "Проверка валидных данных");
    }

    @Test
    void FormTest02() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Radaev Ivan");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79990009900");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(className("button__text")).click();
        assertTrue(!driver.findElements(cssSelector("[class=\"input input_type_text input_view_default input_size_m input_width_available input_has-label input_has-value input_invalid input_theme_alfa-on-white\"]")).isEmpty(),
                "Проверка Латиницы");
    }

    @Test
    void FormTest03() {
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79990009900");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(className("button__text")).click();
        assertTrue(!driver.findElements(cssSelector("[class=\"input input_type_text input_view_default input_size_m input_width_available input_has-label input_invalid input_theme_alfa-on-white\"]")).isEmpty(),
                "Не вводим имя фамилию");
    }

    @Test
    void FormTest04() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Иван");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79990009900");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(className("button__text")).click();
        assertTrue(!driver.findElements(cssSelector("[data-test-id=\"order-success\"]")).isEmpty(),
                "Воодим только имя");
    }

    @Test
    void FormTest05() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Радаев Иван");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(className("button__text")).click();
        assertTrue(!driver.findElements(cssSelector("[class=\"input input_type_tel input_view_default input_size_m input_width_available input_has-label input_invalid input_theme_alfa-on-white\"]")).isEmpty(),
                "Не вводим телефон");
    }

    @Test
    void FormTest06() {
        driver.findElement(className("button__text")).click();
        assertTrue(!driver.findElements(cssSelector("[class=\"input input_type_text input_view_default input_size_m input_width_available input_has-label input_invalid input_theme_alfa-on-white\"]")).isEmpty(),
                "Отправка пустой формы");
    }

    @Test
    void FormTest07() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("Радаев Иван");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79990009900");
        driver.findElement(className("button__text")).click();
        assertTrue(!driver.findElements(cssSelector("[class=\"checkbox checkbox_size_m checkbox_theme_alfa-on-white input_invalid\"]")).isEmpty(),
                "Не нажали галку");
    }
}

