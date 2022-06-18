package guru.qa;

import com.codeborne.selenide.Selenide;
import guru.qa.enums.AvitoMenu;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Homework {

    @EnumSource(AvitoMenu.class)
    @ParameterizedTest
    void enumTest(AvitoMenu menu) {
        Selenide.open("https://www.avito.ru/");
        $$("[data-marker=\"navigation/link\"]").findBy(text(menu.desc)).shouldBe(visible);
    }

    @ValueSource(strings = {"Xiaomi Mi Max 3", "Кухонный нож"})
    @ParameterizedTest(name = "При поиске на Авито по запросу {0} текст запроса отображается в заголовке")
    void avitoTestComplex(String searchData) {
        Selenide.open("https://www.avito.ru/");
        $("[data-marker=\"search-form/suggest\"]").setValue(searchData);
        $("button[data-marker=\"search-form/submit-button\"]").click();
        $$("[data-marker=\"page-title/text\"]").contains(searchData);
    }

    @CsvSource(value = {
            "Авто, Транспорт, Транспорт и запчасти в",
            "Недвижимость, Недвижимость, Недвижимость в",
            "Работа, Работа, Работа в"
    })

    @ParameterizedTest(name = "При клике по разделу {0} на Авито текст {0} отображается в строке поиска и заголовоке")
    void avitoMenuItems(String menuItem, String categoryName, String categoryHeader) {
        Selenide.open("https://www.avito.ru/");
        $$("[data-marker=\"navigation/link\"]").findBy(text(menuItem)).click();
        $$("[data-marker=\"search-form/category\"]").contains(categoryName);
        $$("[data-marker=\"page-title/text\"]").contains(categoryHeader);
    }
}
