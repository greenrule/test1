package tests;
//Домашка 5-3 часть
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class DragAndDropTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
     //работает
    void dragAndDrop() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo("#column-b");
        //проверка
        $("#column-b").$("header").shouldHave(text("A"));
        $("#column-a").$("header").shouldHave(text("B"));
    }

    /* не работает(???)
    void dragAndDropWithActions() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        SelenideElement elementA = $("#column-a");
        SelenideElement elementB = $("#column-b");
        actions().dragAndDrop(elementA, elementB).perform();
        //проверка
        $("#column-b").$("header").shouldHave(text("A"));
    }*/
}
