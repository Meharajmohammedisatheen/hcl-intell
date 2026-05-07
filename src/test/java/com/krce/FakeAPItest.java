package com.krce;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FakeAPItest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://api.escuelajs.co/api/v1";
    }

    @Test
    public void testGetProducts() {
        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }

    @Test
    public void testFilterProductsByPrice() {
        RestAssured.given()
                .queryParam("price", 100)
                .when()
                .get("/products/")
                .then()
                .statusCode(200)
                .body("[0].price", Matchers.equalTo(100));
    }

    @Test
    public void testGetCategories() {
        RestAssured.given()
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("$", Matchers.instanceOf(List.class));


    }
    @Test
    public void testGetCategoriesById(){
        RestAssured.given()
                .pathParam("id",1)
                .when()
                .get("/categories/{id}")
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(1));
        //list of key price and price as value
    }
    @Test
    public void testFilterProductsByTitle(){
        RestAssured.given()
                .queryParam("title","Generic")
                .when()
                .get("/products/")
                .then()
                .statusCode(200)
                .body("$",Matchers.instanceOf(List.class));

    }
    @Test
    public void testFilterProductsByPriceRange(){
        RestAssured.given()
                .queryParam("price_min","900")
                .queryParam("price_max",1000)
                .when()
                .get("/products/")
                .then()
                .statusCode(200)
                .body("$",Matchers.instanceOf(List.class));

    }
    @Test
    public void testFilterProductsBycategoryslung(){
        RestAssured.given()
                .queryParam("slung","clothes")
               // .queryParam("price_max",1000)
                .when()
                .get("/products/")
                .then()
                .statusCode(200)
                .body("$",Matchers.instanceOf(List.class));

    }
    @Test
    public void testGetuser(){
        RestAssured.given()
                .pathParam("id",1)
                .when()
                .get("/categories/{id}")
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
        //list of key price and price as value
    }
    @Test
    public void testGetsingleuser(){
        RestAssured.given()
                .pathParam("id",1)
                .when()
                .get("/categories/{id}")
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(1));
        //list of key price and price as value
    }
    public void createCategory() {
        String body = """
                  {
                  "name": "Yuhann",
                  "image": "https://placeimg.com/640/480/any"
                }
                
               """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/categories")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", Matchers.equalTo("Yuhann"))
                .body("image", Matchers.equalTo("https://placeimg.com/640/480/any"));



    }
}

