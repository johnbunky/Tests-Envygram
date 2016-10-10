package org.selenide.examples;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


/**
 * Created by johnbunky on 07.10.16.
 */
public class CreatePost {

    private String g_baseUrl = "http://68.169.52.12/EnvyGram/html/";


    @Test
    public void createPost_WithImage_Created() {

        String headliner = "headliner";
        String description = "description";

        // arrange
        open(g_baseUrl);
        login("John", "bn3211");
        openCreateNewPostWindow();

        // act
        fillRequiredFields(headliner, description);

        // assert
        open(g_baseUrl + "home");
        $(byTitle(headliner)).shouldBe();
        $(byTitle(description)).shouldBe();
        $(By.xpath("(.//*[@alt=\"post\"])[1]")).isImage();

    }

    private void login(String name, String password) {
        $("#loginDialogButton").click();
        $("#UserLogin_email").setValue(name);
        $("#UserLogin_password").setValue(password).pressEnter();
    }

    private void openCreateNewPostWindow() {
        $(".username.pull-left").click();
        $(".btn-primary-small.createNewPostButton.padding.left10.right10.btn.btn-small").click();
    }

    private void fillRequiredFields(String headline, String description) {
        $(By.name("PostCreate[header]")).setValue(headline);
        $(By.name("PostCreate[description]")).setValue(description);
        SelenideElement dropMenu = $("select[name=\"PostCreate[adType]\"]");
        dropMenu.click();
        dropMenu.$("option[value=\"1\"]").scrollTo().click();
        $(".fileField.inputFile").uploadFile(new File("src/test/java/org/selenide/TestData/createPost_WithImage_Created.jpg"));
        $(withText("Create Envygram")).click();
    }

}
