package tests.allure;
//домашка 7. Часть 1. Чистый Selenide (с Listener), в этом случае
// добавляется сценарий теста + скриншот места падения
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GithubContentIssue {


    @Test
    @DisplayName("Проверяем, что issues отображаются")
    void contentIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue("greenrule/test1").pressEnter();
        $(By.linkText("greenrule/test1")).click();
        $("#issues-tab").shouldHave(text("Issues"));
    }
}

