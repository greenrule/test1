package docs;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class Snippets {

    void browser_command_examples() {

        open("https://google.com");
        open("/customer/orders");     // -Dselenide.baseUrl=http://123.23.23.1

        Selenide.back(); //назад
        Selenide.refresh();// обновить

        Selenide.clearBrowserCookies();//очистить куки
        Selenide.clearBrowserLocalStorage();//очистить локал сторедж

        Selenide.confirm(); // OK in alert dialogs
        Selenide.dismiss(); // Cancel in alert dialogs

        Selenide.closeWindow(); // закрыть активное окно
        Selenide.closeWebDriver(); // закрыть все окна

        Selenide.switchTo().frame("new"); //переход в iframe
        Selenide.switchTo().defaultContent(); //выйти из iframe, чтобы дальше продолжить поиск

        //перейти в новое открытое окно и работать в нем
        Selenide.switchTo().window("The Internet");
    }

    void selectors_examples() {
        $("div").click();
        element("div").click(); //можно использовать вместо $(для языка Котлин)

        $("div", 2).click(); //ищем 3 div

        $x("//h1/div").click(); //
        $(byXpath("//h1/div")).click();//x-path элемент

        $(byText("full text")).click();//ищет элемент по тексту
        $(withText("ull tex")).click();//ищет по вхождению в текст

        $("").parent(); //родитель
        //ищет следующий вниз элемент не проваливаясь в дерево
        $("").sibling(1);
        $("").preceding(1);
        //ближайший элемент вверх
        $("").closest("div");
        //аналог closest
        $("").ancestor("div"); // the same as closest
        //работает невсегда
        $("div:last-child");
        //селекторы можно вкладывать
        $("div").$("h1").find(byText("abc")).click();

        // аналог ., # и т.д. можно не использовать
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click();
        $(byId("mytext")).click();
        $("#mytext").click();
        $(byClassName("red")).click();
        $(".red").click();
        $(By.linkText("eroshenkoam/allure-example")).click(); //поиск по ссылке на странице
    }

    void actions_examples() {
        $("").click();
        $("").doubleClick(); //двойной клик
        $("").contextClick(); //клик правой кнопкой мыши

        $("").hover(); //ховер

        $("").setValue("text"); //очищает поле и вписывает текст
        $("").append("text");//дописывает тест
        $("").clear();
        $("").setValue(""); // аналог clear



        $("div").sendKeys("c"); // с помощью клавиш
        actions().sendKeys("c").perform(); //hotkey c on whole application
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));//аналог Ctrl + F

        $("").pressEnter();//нажить соотв-щие клавиши
        $("").pressEscape();
        $("").pressTab();
        $("").submit(); // подтвердить форму


        // цепочка действий с мышью и клавиатурой
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        // только для старых приложений, на новых не работает
        $("").selectOption("dropdown_option");
        $("").selectRadio("radio_options");

    }

    void assertions_examples() {
        $("").shouldBe(visible);//это используется из-за англ. грамматики
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);


        //свой таймаут, проверка,  в течение какого времени д. появится
        $("").shouldBe(visible, Duration.ofSeconds(30));


    }

    void conditions_examples() {

        $("").shouldBe(visible);
        $("").shouldBe(hidden);
        $("").shouldHave(text("abc"));
        //точный текст
        $("").shouldHave(exactText("abc"));
        //чувствителен регистру
        $("").shouldHave(textCaseSensitive("abc"));
        //
        $("").shouldHave(exactTextCaseSensitive("abc"));
        //регуляторное выражение
        $("").should(matchText("[0-9]abc$"));

        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssValue("font-size", "12"));

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked); // for checkboxes

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        $("").should(exist);

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }

    void collections_examples() {
//коллекция элементов
        $$("div"); // does nothing!

        // фильтр произойдет только после действия, например, проверки размера
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));

        $$("div").first().click();
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0)
        $("div", 1).click(); //аналог
        $$("div").findBy(text("123")).click(); //  сразу ищет первый

        // assertions
        $$("").shouldHave(size(0)); //пустая коллекция
        $$("").shouldBe(CollectionCondition.empty); // аналог

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); // содержит текст
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        $$("").shouldHave(itemWithText("Gamma")); // ищет только 1 текст

        $$("").shouldHave(sizeGreaterThan(0)); //размер больше, меньше и т.д.
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));


    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links (для старых файлов)
        // скачивание файла по кнопке, but may have problems with Grid/Selenoid
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));
        //загружаем файл и не забываем нажать клик по кнопке - подтвердить
        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");
        //передать значения
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        //что-то вернуть
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }
}

