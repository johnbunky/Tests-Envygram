package org.selenide.examples;


import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by johnbunky on 05.10.16.
 */
public class SignUpWithEmail {

    @Test
    public void SignUpWithEmail_correctCredentials_success() {
        //arrange
        open("http://68.169.52.12/EnvyGram/html/");
        $("#joinEnvygramBtn").click();
        $(".button").click();
        //act
        $("#UserRegister_username").setValue("John");
        $("#UserRegister_email").setValue("johnbunky@mail.ru");
        $("#UserRegister_password").setValue("bn3211");
        $(".btn-primary-normal.btn.btn-large").click();
        //assert
        open("https://mail.ru/");
        $(".w-mailbox__login").setValue("johnbunky");
        $("#mailbox__password").setValue("**********").pressEnter();
        $(".b-datalist__item__addr").shouldBe(text("no-replay@envygram.com"));
        open("http://68.169.52.12/EnvyGram/html/");
        $("#loginDialogButton").click();
        $("#UserLogin_email").setValue("John");
        $("#UserLogin_password").setValue("bn3211").pressEnter();
        $(".username.pull-left").shouldHave(text("John"));





    }

}
