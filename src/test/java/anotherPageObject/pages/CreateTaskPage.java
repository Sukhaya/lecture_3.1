package anotherPageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CreateTaskPage {
    private static final By ISSUE_TYPE_DROPDOWN = By.xpath(".//*[@id='issuetype-single-select']//input");
    private static final By ISSUE_SUMMARY_INPUT = By.xpath(".//*[@id='summary']");
    private static final By ISSUE_DESCRIPTION_TOGGLER_TO_TEXT = By.xpath(".//*[@data-mode='source']");
    private static final By ISSUE_DESCRIPTION_INPUT = By.xpath(".//*[@id='description-wiki-edit']//textarea");
    private static final By CREATE_ISSUE_SUBMIT_BUTTON = By.xpath(".//*[@id='create-issue-submit']");

    @Step("Установим для задачи тип: {0}")
    public CreateTaskPage setType(String type) {
        $(ISSUE_TYPE_DROPDOWN).shouldBe(visible.because("Нет дропдауна выбора типа создаваемой задачи"))
                .setValue(type)
                .pressEnter();
        return this;
    }

    @Step("Установим для задачи тему: {0}")
    public CreateTaskPage setSummary(String theme) {
        $(ISSUE_SUMMARY_INPUT).shouldBe(visible.because("Нет поля ввода темы задачи"))
                .setValue(theme);
        return this;
    }

    @Step("Установим для задачи описание: {0}")
    public CreateTaskPage setDescription(String description) {
        $(ISSUE_DESCRIPTION_TOGGLER_TO_TEXT).shouldBe(exist.because("Нет кнопки переключения на текстовый формат")).click();
        $(ISSUE_DESCRIPTION_INPUT).shouldBe(exist.because("Нет поля ввода текста описания")).sendKeys(description);
        return this;
    }

    @Step("Подтверждаем создание задачи")
    public MainPage clickAccept() {
        $(CREATE_ISSUE_SUBMIT_BUTTON).shouldBe(visible.because("Нет кнопки подтверждения создания задачи")).click();
        return new MainPage();
    }
}
