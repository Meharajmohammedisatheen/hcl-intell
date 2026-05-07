package com.krce;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class CategoryTest {
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="https://api.escuelajs.co/api/v1";
    }
    @Test
    public void testcreateCategory(){
        String name="user"+System.currentTimeMillis();
        String image="https://google.com";
        Map body= Map.of(
                "name",name,"image",image
        );
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/categories")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", Matchers.equalTo(name));

    }
}