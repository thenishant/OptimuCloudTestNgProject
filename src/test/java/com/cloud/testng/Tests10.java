package com.cloud.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tests10 extends Driver{

    @FindBy(id = "amr")
    private WebElement welcomeText;

    @FindBy(id = "aso")
    private WebElement todaysText;

    @FindBy(id = "bw")
    private WebElement addNewTaskButton;

    @FindBy(id = "ne")
    private WebElement firstTodo;

    @FindBy(className = "androidx.recyclerview.widget.RecyclerView")
    private WebElement todoList;

    @FindBy(id = "a5f")
    private WebElement calender;

    @FindBy(id = "f5")
    private WebElement onBoardButton;

    @FindBy(id = "fi")
    private WebElement skipButton;

    public void navigateTillHomePage() {
        waitForElementsToBeVisible(onBoardButton);
        onBoardButton.click();
        waitForElementsToBeVisible(skipButton);
        skipButton.click();
    }

    @Test
    public void iShouldBeAbleToSeeTheWelcomeScreen() throws InterruptedException {
        navigateTillHomePage();
        waitForElementsToBeVisible(welcomeText);
        assertEquals(welcomeText.getText(), "\uD83D\uDC4BWelcome");
    }

    @Test
    public void iShouldBeAbleToSeeListOFItems() throws InterruptedException {
        navigateTillHomePage();

        waitForElementsToBeVisible(todoList);
        assertEquals(todoList.findElements(By.className("android.view.View")).size(), 7);
    }

    @Test
    public void iShouldBeAbleToSeeAddTaskButton() throws InterruptedException {
        navigateTillHomePage();
        waitForElementsToBeVisible(addNewTaskButton);
        assertTrue(addNewTaskButton.isDisplayed());
    }

    @Test
    public void iShouldBeAbleToSelectFirstItem() throws InterruptedException {
        navigateTillHomePage();

        waitForElementsToBeVisible(todoList);
        List<WebElement> listOfTasks = todoList.findElements(By.className("android.view.View"));
        listOfTasks.stream().findFirst().get().click();
        waitForElementsToBeVisible(firstTodo);
        assertEquals(firstTodo.getText(), "Create a new task by tapping ➕");
    }

    @Test
    public void iShouldBeAbleToSeeFreeCalender() throws InterruptedException {
        navigateTillHomePage();
        waitForElementsToBeVisible(calender);
        calender.click();
        waitForElementsToBeVisible(todaysText);
        assertEquals(todaysText.getText(), "You have a free day.");
    }
}
