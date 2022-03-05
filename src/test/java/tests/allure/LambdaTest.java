package tests.allure;
//домашка 7. Часть 2. Лямбда шаги через step (name, () -> {})

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class LambdaTest {
    private static String REPOSITORY = "greenrule/test1";
    private static String tabName = "Issues";

    @Test
    @DisplayName("Steps Test, Проверяем, что issues отображаются")
    void testLambdaSteps() {
        //динамическая анотация, остальные в LabelsTest
        Allure.label("owner", "greenrule");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.feature("Задачи про Allure");
        Allure.story("Allure test");
        Allure.getLifecycle().updateTestCase(testCase -> {
            testCase.setName("Проверка названия Issue - 2");
        });
        Allure.description("тот тест проверят названия вкладки Issue");
        Allure.link("Testing", "https://github.com");
        Allure.parameter("REPOSITORY", "reenrule/test1");
        Allure.parameter("tabName", "Issues");

        SelenideLogger.addListener("allure", new AllureSelenide());



        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });

        step("Ищем репозитроий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY).pressEnter();
            //добавление скриншота
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });

        step("Открываем репозитроий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Проверяем название " + tabName, () -> {
            $(By.linkText(tabName)).click();
        });
    }
}
