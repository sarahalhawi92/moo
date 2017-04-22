package com.moo.fakeapitests;


import com.moo.fakeapitests.utils.UserRequestUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class DeleteUsers extends UserRequestUtils {

    @Test()
    public void whenRequestingToRemoveAValidUser_thenUserIsDeleted() {

        given()
                .delete(USERS_URL + "10")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test()
    public void whenRequestingToRemoveANonExistentUser_thenNotFoundErrorIsReturned() {

        given()
                .delete(USERS_URL + "random")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
