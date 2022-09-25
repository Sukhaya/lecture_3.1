package anotherPageObject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class MainPage {
    private static final By USER_PROFILE_ICON = By.xpath(".//a[@id='header-details-user-fullname']");
    private static final By PROJECTS_MENU = By.xpath(".//a[@href='/browse/TEST']");
    private static final By SUCCESS_MESSAGE = By.xpath(".//*[contains(@class, 'aui-message-success')]");
    private static final By PROJECT_ITEM = By.xpath(".//*[@id='admin_main_proj_link']");
    private static final By CREATE_TASK_BUTTON = By.xpath(".//*[@id='create_link']");
    private static final String DATA_USERNAME_ATTR = "data-username";

    @Step("Открываем список проектов")
    public MainPage openProjectsMenu() {
        $(PROJECTS_MENU).shouldBe(visible.because("Нет меню всех проектов юзера")).click();
        return this;
    }

    @Step("Переходим в проект TestProject")
    public ProjectPage openProject(String projectName) {
        $$(PROJECT_ITEM).filter(text(projectName))
                .shouldHaveSize(1)
                .first()
                .click();
        return new ProjectPage();
    }

    @Step("Переходим к созданию задачи")
    public CreateTaskPage openCreateTask() {
        $(CREATE_TASK_BUTTON).shouldBe(visible.because("Нет кнопки создания задачи")).click();
        return new CreateTaskPage();
    }

    @Step("Получаем текст сообщения об успешном создании таски")
    public String getSuccessMessageText() {
        return $(SUCCESS_MESSAGE).shouldBe(appear.because("Нет сообщения об успешном действии")).getText();
    }

    @Step("Проверяем есть ли иконка юзера")
    public boolean isUserProfileIconVisible() {
        return $(USER_PROFILE_ICON).is(visible);
    }

    @Step("Получаем имя текущего юзера")
    public String getCurrentUsername() {
        return $(USER_PROFILE_ICON).shouldHave(attribute(DATA_USERNAME_ATTR).because("нет ожидаемого аттрибута с именем пользователя"))
                .attr(DATA_USERNAME_ATTR);
    }

}
