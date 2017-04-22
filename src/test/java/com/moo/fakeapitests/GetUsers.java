package com.moo.fakeapitests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetUsers {

    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated
    }

    @Test()
    public void whenSendingAGetRequest_thenUserDetailsAreRetrieved() {
        System.out.println("Fast test");
    }
}
