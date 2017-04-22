package com.moo.fakeapitests.utils;

import com.moo.fakeapitests.UserRequestEntity;

public class UserRequestUtils {

    public static final String USERS_URL = "http://jsonplaceholder.typicode.com/users/";

    public static UserRequestEntity createUserRequest(){
        UserRequestEntity userRequestEntity = new UserRequestEntity();
        userRequestEntity.setName("Sarah Alhawi");
        userRequestEntity.setUsername("sarahal");
        userRequestEntity.setEmail("shawi3333@gmail.com");
        userRequestEntity.setPhone("0545849685");
        userRequestEntity.setWebsite("https://google.co.uk");

        return userRequestEntity;
    }
}
