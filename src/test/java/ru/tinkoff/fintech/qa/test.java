package ru.tinkoff.fintech.qa;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tinkoff.fintech.qa.controllers.models.UserEntity;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void addTest(){
        RestAssured.given()
                .body(userEntity)
                .contentType(ContentType.JSON)
                .post()
                .as()
    }
}
