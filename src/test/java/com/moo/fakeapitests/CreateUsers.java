package com.moo.fakeapitests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.http.ContentType;
import com.moo.fakeapitests.utils.UserRequestUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class CreateUsers extends UserRequestUtils {

    @DataProvider
    public Object[][] createUserRequests(){
        return new Object[][]{
                {UserRequestUtils.createUserRequest()}
        };
    }

    @Test()
    public void whenCreatingAUserWithNoSpecificDetails_thenUserIsCreated() {

        given().contentType(ContentType.JSON)
                .post(USERS_URL)
                .prettyPeek().andReturn()
                .then()
                .statusCode(HttpStatus.SC_CREATED).and()
                .body("id", equalTo(11));
    }

    @Test(dataProvider = "createUserRequests")
    public void whenCreatingAUserWithSpecificDetails_thenUserIsCreated(UserRequestEntity userRequestEntity) throws JsonProcessingException {
        given()
                .body(userRequestEntity)
                .with().contentType("application/json; charset=UTF-8")
                .log().all()
                .when()
                .post(USERS_URL)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED).and()
                .body("id", equalTo(11))
                .body("name", equalTo("Sarah Alhawi"))
                .body("username", equalTo("sarahal"))
                .body("email", equalTo("shawi3333@gmail.com"));
    }
}
