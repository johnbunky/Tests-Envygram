package org.selenide.examples;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by johnbunky on 07.10.16.
 */
public class CreateANewPost {
    @Test
    public void createAPost_ImgFromComp_succes(){
        //arrange
        open("http://68.169.52.12/EnvyGram/html/");
        $("#loginDialogButton").click();
        $("#UserLogin_email").setValue("John");
        $("#UserLogin_password").setValue("bn3211").pressEnter();
        $(".badge.createPost").click();
        $("#yw14>li>a").click();
        //act
        $("#PostCreate_header").setValue("Headline");
        $("#PostCreate_description").setValue("Description");
        $("#PostCreate_adType").click();
                 // как выбрать из выпадающего меню??????
                 // допустим выбрал default
        $(".span10.dropdown-toggle.mediaDropdown.btn").shouldHave(text("Image From Computer")); // check a source
        $(".fileField.inputFile").click();
                 // Как выбрать фото из окна не в браузере????
                 // choose photo, click open
        // assert
        $(".fileField.inputFile").shouldHave(text("File selected"));
        $(".create-label").click();
                  //как подождать када стр загрузиться???
        $(byTitle("Headliner")).shouldHave(text("Headliner"));
        $(byTitle("Description")).shouldHave(text("Description"));
        $(".attachment.view-right.text-center>img").shouldBe(visible); // годится такая проверка фото?

    }
}
