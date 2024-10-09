package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTests {

    @Test
    void downloadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File file = $("[data-testid='raw-button']").download();

        try(InputStream is = new FileInputStream(file)) {
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Official CI build server for JUnit 5. Used to perform quick checks on submitted"));
        }
    }

    @Test
    void uploadFileTest() {
        open("https://tus.io/demo");
        $("input[type='file']").uploadFromClasspath("car.jpg/");
    }

}
