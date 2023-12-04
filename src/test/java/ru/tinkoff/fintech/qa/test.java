package ru.tinkoff.fintech.qa;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.fintech.qa.controllers.models.DefaultResponse;
import ru.tinkoff.fintech.qa.controllers.models.UserEntity;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class test {
    UserEntity userEntity;
    @BeforeEach
    public void init(){
        userEntity = new UserEntity();
        userEntity.setId(12345);
        userEntity.setFio("Иванов Иван Иванович");
        userEntity.setPasswordNumber(142536);
        userEntity.setPasswordSeries(2531);
        userEntity.setPhone("89153015625");
    }

    @Test
    public void addTest() {
        DefaultResponse defaultResponse =
        given()
                .body(userEntity)
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/api/add").then().statusCode(200)
                .extract()
                .response()
                .as(DefaultResponse.class);
        assertEquals("Check adding user", "Done", defaultResponse.value());
    }

    @Test
    public void getCreatedUser() {
        String url = "http://localhost:8080/api/get/" + userEntity.getId();
        UserEntity userEntityGet = given()
                .contentType(ContentType.JSON)
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(UserEntity.class);
        Assertions.assertTrue(userEntity.equals(userEntityGet));
    }

    @Test
    public void updateUser() {
        userEntity.setFio("Updated fio");
        DefaultResponse userEntityUpdateResponse = given()
                .contentType(ContentType.JSON)
                .body(userEntity)
                .post("http://localhost:8080/api/update")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(DefaultResponse.class);
        Assertions.assertEquals("Done", userEntityUpdateResponse.value(), "Check updating user");
    }

    @Test
    public void deleteTest(){
        DefaultResponse response = RestAssured.given()
                .contentType(ContentType.JSON)
                .post("http://localhost:8080/api/delete/123")
                .then().statusCode(200)
                .extract()
                .response()
                .as(DefaultResponse.class);
        Assertions.assertEquals("Done", response.value(), "delete method test");
    }
}
