package anotherPageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TaskPage {
    private static final By TO_DO_BUTTON = By.xpath(".//*[@id='action_id_11']");
    private static final By IN_PROGRESS_BUTTON = By.xpath(".//*[@id='action_id_21']");
    private static final By RESOLVED_BUTTON = By.xpath(".//*[@class='issueaction-workflow-transition']//*[text()='Выполнено']");
    private static final By WORK_FLOW_BUTTON = By.xpath(".//*[@id='opsbar-transitions_more']");
    private static final By STATUS_FIELD = By.xpath(".//*[@id='status-val']");
    private static final By NOTIFICATION_POPUP = By.xpath(".//*[@id='aui-flag-container']");
    private static final By CLOSE_NOTIFICATION_BUTTON = By.xpath(".//*[@class='aui-close-button']");

    @Step("Меняем статус задачи - Нужно сделать")
    public TaskPage changeStatusTodo() {
        $(TO_DO_BUTTON).shouldBe(visible.because("Нет кнопки Нужно сделать")).click();
        $(NOTIFICATION_POPUP).should(appear.because("Не появилось уведомление о смене статуса"));
        $(CLOSE_NOTIFICATION_BUTTON).shouldBe(visible.because("Нет кнопки закрытия уведомления")).click();
        return this;
    }

    @Step("Меняем статус задачи - В работе")
    public TaskPage changeStatusInProgress() {
        $(IN_PROGRESS_BUTTON).shouldBe(visible.because("Нет кнопки В работе")).click();
        $(NOTIFICATION_POPUP).should(appear.because("Не появилось уведомление о смене статуса"));
        $(CLOSE_NOTIFICATION_BUTTON).shouldBe(visible.because("Нет кнопки закрытия уведомления")).click();
        return this;
    }

    @Step("Меняем статус задачи - Выполнено")
    public TaskPage changeStatusResolved() {
        $(WORK_FLOW_BUTTON).shouldBe(visible.because("Нет кнопки Бизнес-процессы")).click();
        $(RESOLVED_BUTTON).shouldBe(visible.because("Нет кнопки Выполнено")).click();
        $(NOTIFICATION_POPUP).should(appear.because("Не появилось уведомление о смене статуса"));
        $(CLOSE_NOTIFICATION_BUTTON).shouldBe(visible.because("Нет кнопки закрытия уведомления")).click();
        return this;
    }

    @Step("Проверяем смену статуса")
    public void checkTaskStatus(String taskStatus) {
        $(STATUS_FIELD).shouldHave(text(taskStatus).because("Не сменился статус"));
    }
}
