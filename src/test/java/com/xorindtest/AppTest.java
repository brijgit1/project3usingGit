package com.xorindtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AppTest 
{
    @Test
    public void getTest1()
    {
        RestAssured.baseURI="https://reqres.in/api/users?page=2";
        Response resp=RestAssured.get();
        String responseInString=resp.getBody().asString();
        System.out.println(responseInString);
    }
}
