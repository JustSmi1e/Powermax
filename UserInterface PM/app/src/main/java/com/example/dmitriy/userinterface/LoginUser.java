package com.example.dmitriy.userinterface;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


public class LoginUser {
    public String panel_web_name;
    public String user_code;
}


public interface Queries {
    @GET("/rest_api/2.0/dump?par1=val1 HTTP/1.1")
    LoginUser getLoginUser();
}
