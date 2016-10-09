package org.selenide.examples;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


/**
 * Created by johnbunky on 07.10.16.
 */
public class CreatePost {

    @Test
    public void createPost_WithImage_Created(){

        //System.setProperty("selenide.browser", "chrome");
        // arrange
        open("http://68.169.52.12/EnvyGram/html/");
        login("John", "bn3211");
        openCreateNewPostWindow();
        // act
        fillRequiredFields("headline", "description");
        // assert
        Assert();

    }

    private void Assert() {
        $(byTitle("Headliner")).shouldHave(text("Headliner"));
        $(byTitle("Description")).shouldHave(text("Description"));
        $(".attachment.view-right.text-center>img").isImage();
    }

    private void fillRequiredFields(String headline, String description) {
        $(By.name("PostCreate[header]")).setValue(headline);
        $(By.name("PostCreate[description]")).setValue(description);
        SelenideElement dropMenu = $("select[name=\"PostCreate[adType]\"]");
        dropMenu.click();
        dropMenu.$("option[value=\"1\"]").scrollTo().click();
        $(".fileField.inputFile").uploadFile(new File("/home/Desktop/IMG_20161006_123821.jpg"));
        $(withText("Create Envygram")).click();
    }

    private void openCreateNewPostWindow() {
        $(".username.pull-left").click();
        $(".btn-primary-small.createNewPostButton.padding.left10.right10.btn.btn-small").click();
    }

    private void login(String name, String password) {
        $("#loginDialogButton").click();
        $("#UserLogin_email").setValue(name);
        $("#UserLogin_password").setValue(password).pressEnter();

    }
}
