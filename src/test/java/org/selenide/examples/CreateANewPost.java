package org.selenide.examples;

import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
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
        login("John", "bn3211");
        openCreateNewPostWindow();
        //act
        fillRequiredFields("Headline", "Description", "Default", "IMG_20161006_123821.jpg");
        //assert
        Results();

    }

    private void Results() {
        $(byTitle("Headliner")).shouldHave(text("Headliner"));
        $(byTitle("Description")).shouldHave(text("Description"));
        $(".attachment.view-right.text-center>img").isImage();
    }

    private void fillRequiredFields(String Headline, String Description, String AdType, String photo) {
        $("#PostCreate_header").setValue(Headline);
        $("#PostCreate_description").setValue(Description);
        $("#PostCreate_adType").selectOption(AdType);
        $(".fileField.inputFile").uploadFile( new File(photo));
        $(".create-label").click();
    }

    private void openCreateNewPostWindow() {
        $(".badge.createPost").click();
        $("#yw14>li>a").click();
    }

    private void login(String name, String password) {
        $("#loginDialogButton").click();
        $("#UserLogin_email").setValue(name);
        $("#UserLogin_password").setValue(password).pressEnter();
    }
}
