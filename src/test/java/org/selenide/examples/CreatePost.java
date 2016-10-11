package org.selenide.examples;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String headliner = "headliner" + format.format(date);
        String description = "description" + format.format(date);

        // arrange
        open(g_baseUrl);
        login("John", "bn3211");
        openCreateNewPostWindow();

        // act
        fillRequiredFields(headliner, description);

        // assert
        open(g_baseUrl + "home");
        SelenideElement newPost = $(By.xpath("(.//*[@class='span6 offset3 frame'])[1]"));
        newPost.$(byTitle(headliner)).shouldBe();
        newPost.$(byTitle(description)).shouldBe();
        newPost.$(By.xpath("(.//*[@alt=\"post\"])[1]")).isImage();

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
        $(".fileField.inputFile").uploadFile(new File("src/test/java/org/selenide/TestData/createPost_WithImaga_Created.jpg"));
        $(withText("Create Envygram")).click();
    }

}
