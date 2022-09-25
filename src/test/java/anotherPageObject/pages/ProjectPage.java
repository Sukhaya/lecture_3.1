package anotherPageObject.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {
    private static final By PROJECT_SIDEBAR = By.xpath(".//*[@class='aui-sidebar-wrapper']");
    private static final By PROJECT_TITLE = By.xpath(".//*[@class='aui-item project-title']");
    private static final String TASKS_MENU = "Задачи";
    private static final String ISSUES_MENU = "Список задач";

    @Step("Открываем страницу задач")
    public ProjectTasksPage openTasksPage() {
        openMenu(TASKS_MENU);
        return new ProjectTasksPage();
    }

    @Step("Открываем страницу списка задач")
    public ProjectIssuesPage openIssuesPage() {
        openMenu(ISSUES_MENU);
        return new ProjectIssuesPage();
    }

    @Step("Проверяем появилась ли боковая панель проекта")
    public boolean isProjectSidebarAppears() {
        return $(PROJECT_SIDEBAR).is(appear);
    }

    @Step("Получаем название текущего открытого проекта")
    public String getProjectTitle() {
        return $(PROJECT_TITLE).shouldBe(exist.because("Нет названия текущего открытого проекта")).innerText();
    }

    private ProjectPage openMenu(String menuTitle) {
        menuButton(menuTitle).shouldBe(visible.because("Нет меню с таким заголовком")).click();
        return this;
    }

    private SelenideElement menuButton(String menuTitle) {
        return $(By.xpath("//a[contains(@class, 'aui-nav-item')]//*[text()='" + menuTitle + "']")).parent();
    }
}
