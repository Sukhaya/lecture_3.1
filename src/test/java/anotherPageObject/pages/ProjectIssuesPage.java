package anotherPageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectIssuesPage {
    private static final By TASKS = By.xpath(".//*[@class='ghx-issue-content']");
    private static final By SOFTWARE_VERSION = By.xpath(".//span[@id='fixfor-val']");
    private static final By TASK_STATUS = By.xpath(".//span[@id='status-val']");
    private static final By TASK_LINK = By.xpath(".//*[@id='issuekey-val']");

    @Step("Переходим в задачу: {0}")
    public ProjectIssuesPage openTask(String taskName) {
        $$(TASKS).filter(text(taskName))
                .shouldHave(sizeGreaterThan(0))
                .first()
                .click();
        return this;
    }

    @Step("Открываем страницу задачи")
    public TaskPage openCurrentTaskPage() {
        $(TASK_LINK).shouldBe(visible.because("Нет ссылки в текущей задаче для открытия страницы задачи")).click();
        return new TaskPage();
    }

    @Step("Проверяем статус задачи и выводим привязку к версии")
    public void checkStatusAndVersion() {
        String svText = $(SOFTWARE_VERSION).shouldBe(visible.because("Нет поля Software version")).getText();
        String taskStatusText = $(TASK_STATUS).shouldBe(visible.because("Нет поля Статус")).getText();
        System.out.println("Software version: " + svText + "\nTask status: " + taskStatusText);
    }
}
