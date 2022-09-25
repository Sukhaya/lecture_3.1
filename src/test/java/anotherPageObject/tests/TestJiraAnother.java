package anotherPageObject.tests;

import anotherPageObject.pages.AuthPage;
import anotherPageObject.pages.CreateTaskPage;
import anotherPageObject.pages.MainPage;
import anotherPageObject.pages.ProjectIssuesPage;
import anotherPageObject.pages.ProjectPage;
import anotherPageObject.pages.ProjectTasksPage;
import anotherPageObject.pages.TaskPage;
import hooks.WebHooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Configuration.getConfigurationValue;

public class TestJiraAnother extends WebHooks {
    private static final String PROJECT = "Test";
    private static final String TEST_BUG = "TestSelenium_bug";
    private static final String CREATE_TASK_TYPE = "Ошибка";
    private static final String CREATE_TASK_SUMMARY = "Нет поиска по задачам";
    private static final String CREATE_TASK_DESCRIPTION = "Шаги \n # открыть jira \n открыть проект";
    private static final String STATUS_TODO = "СДЕЛАТЬ";
    private static final String STATUS_IN_PROGRESS = "В РАБОТЕ";
    private static final String STATUS_RESOLVED = "ГОТОВО";
    private MainPage mainPage;

    @BeforeEach
    public void open() {
        mainPage = new AuthPage().openUrl(getConfigurationValue("jiraUrl"))
                .doAuthorization(getConfigurationValue("login"), getConfigurationValue("password"));
    }


    @Test
    @DisplayName("Проверка авторизации")
    public void Test_Login() {
        assertTrue(mainPage.isUserProfileIconVisible());
        assertEquals(getConfigurationValue("login"), mainPage.getCurrentUsername());
    }

    @Test
    @DisplayName("Переходим в проект TestProject")
    public void Test_ChoosingProject() {
        ProjectPage projectPage = mainPage.openProjectsMenu()
                .openProject(PROJECT);
        assertTrue(projectPage.isProjectSidebarAppears());
        assertEquals(PROJECT, projectPage.getProjectTitle());
    }

    @Test
    @DisplayName("Проверка количества задач в проекте")
    public void Test_CountTasks() {
        ProjectPage projectPage = mainPage.openProjectsMenu()
                .openProject(PROJECT);
        ProjectTasksPage projectTasksPage = projectPage.openTasksPage();
        projectTasksPage.getCountOfExistsTask();
    }

    @Test
    @DisplayName("Проверка статуса и привязки")
    public void Test_StatusAndVersion() {
        ProjectPage projectPage = mainPage.openProjectsMenu()
                .openProject(PROJECT);
        projectPage.openIssuesPage()
                .openTask(TEST_BUG)
                .checkStatusAndVersion();
    }

    @Test
    @DisplayName("Проверка заведения баг-репорта")
    public void Test_CreateTask() {
        CreateTaskPage createTaskPage = mainPage.openCreateTask();
        mainPage = createTaskPage.setType(CREATE_TASK_TYPE)
                .setSummary(CREATE_TASK_SUMMARY)
                .setDescription(CREATE_TASK_DESCRIPTION)
                .clickAccept();
        assertTrue(mainPage.getSuccessMessageText().contains(CREATE_TASK_SUMMARY));
    }

    @Test
    @DisplayName("Проверка смена статусов задачи")
    public void Test_MoveTaskToResolved() {
        CreateTaskPage createTaskPage = mainPage.openCreateTask();
        mainPage = createTaskPage.setType(CREATE_TASK_TYPE)
                .setSummary(CREATE_TASK_SUMMARY)
                .setDescription(CREATE_TASK_DESCRIPTION)
                .clickAccept();
        ProjectIssuesPage projectIssuesPage = mainPage.openProjectsMenu()
                .openProject(PROJECT)
                .openIssuesPage();
        TaskPage taskPage = projectIssuesPage.openTask(CREATE_TASK_SUMMARY)
                .openCurrentTaskPage();
        taskPage.changeStatusTodo()
                .checkTaskStatus(STATUS_TODO);
        taskPage.changeStatusInProgress()
                .checkTaskStatus(STATUS_IN_PROGRESS);
        taskPage.changeStatusResolved()
                .checkTaskStatus(STATUS_RESOLVED);
    }
}
