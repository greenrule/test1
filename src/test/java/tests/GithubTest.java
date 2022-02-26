package tests;
//Домашка 5 - 2 часть

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void PagesContentSoftAssertionsAndJUnit5() {
        //Откройте страницу selenide
        open("/selenide/selenide");
        // Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
        //в списке страниц (Pages) ищем SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
        //кликаем по SoftAssertions
        $$(".filterable-active a").findBy(text("SoftAssertions")).click();
        // ищем кусок кода для JUnit5
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
    }

}
