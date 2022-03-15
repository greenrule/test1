package tests.allure;
//домашка 7 часть 3б. Берем степы из WebSteps

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class testAnnotatedSteps {
    private static String REPOSITORY = "greenrule/test1";
    private static String tabName = "Issues";

    @Test
    //анотация, остальные в LabelsTest
    @Owner("greenrule")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Задачи про Allure")
    @Story("Allure test")
    @DisplayName("Проверка названия Issue - 1")
    @Description(
            "Этот тест проверят названия вкладки Issue"
    )
    @Link(value = "Testing", url = "https://github.com")

        void testAnnotated() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebStepsTest steps = new WebStepsTest();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.textIssueTab(tabName);
        steps.takeScreenshot();
    }
}
