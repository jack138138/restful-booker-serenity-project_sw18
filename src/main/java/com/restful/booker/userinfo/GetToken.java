package com.restful.booker.userinfo;

import com.restful.booker.model.LoginPojo;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class GetToken {
    public String getToken() {
        LoginPojo loginPojo = new LoginPojo();
        loginPojo.setUsername("admin");
        loginPojo.setPassword("password123");

        Response response = given().log().all()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .when()
                .body(loginPojo)
                .post("/auth");
        response.then().log().all().statusCode(200);
        String token = response.jsonPath().getString("token");
        return token;
    }
}
