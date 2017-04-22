package com.moo.fakeapitests;

import com.jayway.restassured.RestAssured;
import com.moo.fakeapitests.utils.UserRequestUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUsers extends UserRequestUtils {

    @Test()
    public void whenSendingAGetRequestForAValidUser_thenUserDetailsAreRetrieved() {

        given()
                .get(USERS_URL + "1")
                .then()
                .statusCode(HttpStatus.SC_OK).and()
                .body("name", equalTo("Leanne Graham"))
                .body("email", equalTo("Sincere@april.biz"))
                .body("phone", equalTo("1-770-736-8031 x56442"))
                .body("website", equalTo("hildegard.org"));
    }

    @Test()
    public void whenSendingAGetRequestForAnInvalidUser_thenNotFoundErrorIsReturned() {


        given()
                .get(USERS_URL + "blah")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);

        // wanted to check that body is empty

        Hashtable<String,Object> returnValue = RestAssured.get(USERS_URL + "blah").as(Hashtable.class);
        equalTo(returnValue.size() == 0);

    }

    @Test()
    public void whenSendingAGetRequestForAllUsers_thenTheCorrectHeadersAreDisplayed() {

        given()
                .get(USERS_URL)
                .then()
                .statusCode(HttpStatus.SC_OK).and()
                .assertThat().headers("Cache-Control", "public, max-age=14400")
                .assertThat().headers("Connection", "keep-alive")
                .assertThat().headers("Content-Type", "application/json; charset=utf-8");
    }

    @Test()
    public void whenSendingAGetRequestForAllUsers_thenDetailsOfAllUsersAreRetrieved() {

        given()
                .get(USERS_URL)
                .then()
                .statusCode(HttpStatus.SC_OK).and()
                .assertThat().headers("Cache-Control", "public, max-age=14400")
                .assertThat().headers("Connection", "keep-alive")
                .assertThat().headers("Content-Type", "application/json; charset=utf-8");

        UserRequestEntity[] users = RestAssured.get(USERS_URL).as(UserRequestEntity[].class);
        UserRequestEntity firstUser = users[0];
        UserRequestEntity lastUser = users[users.length -1];
        Assert.assertEquals(firstUser.getName(), "Leanne Graham");
        Assert.assertEquals(lastUser.getName(), "Clementina DuBuque");
    }
}
