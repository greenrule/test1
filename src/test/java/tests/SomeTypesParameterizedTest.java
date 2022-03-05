package tests;
//домашка 6

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SomeTypesParameterizedTest {

    //выполняется перед каждым тестом, если BeforeAll перед всеми тестами
    @BeforeEach
    void precondition() {
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    /*@ValueSource используются, если в тесте передается одно значение,
    можно использовать не только строку, но и другие значения*/
    @ValueSource(strings = {"Origin", "Use"})
    @ParameterizedTest(name = "Проверка, что работает переход по табам на странице \"{0}\"")
    public void switchOverTabs(String testData) {
        open("https://demoqa.com/tabs");
        $(byText(testData)).click();
    }

    /*@CsvSource используются, если в тесте передается ключ -> значение*/
    @CsvSource(value = {
            "Origin|Contrary to popular",
            "Use| It is a long established"
    }, delimiter = '|')
    @ParameterizedTest(name = "Проверка отображения текста на вкладках \"{0}\"")
    void textShowsOnTabs(String testData, String expectedText) {
        open("https://demoqa.com/tabs");
        $(byText(testData)).click();
        $$("#demo-tabpane-"+testData.toLowerCase()).shouldHave(texts(expectedText));
    }


    //@MethodSource используется для более сложных данных, списки, массивы
    static Stream<Arguments> PlanetsProperties() {
        return Stream.of(
                Arguments.of("меркурий", List.of(5,2,1)),
                Arguments.of("венера", List.of(5,4,4))
        );
    }

    @MethodSource(value = "PlanetsProperties")
    @ParameterizedTest(name = "Проверка свойств планеты \"{0}\"")
    void planetHaveSomeProperties(String firstArg, List<Integer> secondArg) {
        System.out.println("планета:" + firstArg + " вес: " + secondArg.get(0).toString() +
                " радус: " + secondArg.get(1).toString() + " плотность: " + secondArg.get(2).toString());
    }
}
