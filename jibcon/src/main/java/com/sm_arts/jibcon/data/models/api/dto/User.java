package com.sm_arts.jibcon.data.models.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2017-04-10.
 */

public class User {
    String email;
    @SerializedName("_id")
    String user_id;
    String social_id;
    //String username;
    String token;
    UserInfo userinfo;
    String last_name;
    String first_name;
    String fcm_token;
    String currentHouse;

    public String getCurrentHouseId() {
        return currentHouse;
    }

    public void setCurrentHouseId(String currentHouseId) {
        this.currentHouse = currentHouseId;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserInfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String usernaame) {
//        this.username = usernaame;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
