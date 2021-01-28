package com.example.app.API.Rest;

/**
 * This class is going to help to create the URL from the API exposed
 * with API Gateway.
 */
public class APIConstants {

    private APIConstants() { }

    public static final String PROTOCOL = "https";
    public static final String BASE_URL = "API-GATEWAY-URL";
    public static final String USER_PROFILE = "/profile";
    public static final String EXAMPLE = "/example";
    public static final String SUBJECTS_MAIN = "/subjects";

    private static String baseAPIURLPath(){
        return PROTOCOL + "://" + BASE_URL;
    }

    public static String getAPIPersonalInfo(){
        return baseAPIURLPath() + USER_PROFILE;
    }

    public static String getAPISubjects(){
        return baseAPIURLPath() + SUBJECTS_MAIN;
    }

}
