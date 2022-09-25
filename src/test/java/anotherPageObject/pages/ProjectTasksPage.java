package anotherPageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class ProjectTasksPage {
    private static final By TASKS_COUNTER = By.xpath(".//*[@class='showing']");

    @Step("Получаем количество заведенных задач")
    public void getCountOfExistsTask() {
        String taskCounter = $(TASKS_COUNTER).shouldBe(visible.because("Нет количнества задач")).getText();
        System.out.println("Количество заведенных задач: " + taskCounter);
    }
}
