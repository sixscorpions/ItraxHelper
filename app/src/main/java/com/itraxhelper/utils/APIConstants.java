package com.itraxhelper.utils;


/**
 * Created by shankar on 4/30/2017.
 */

public class APIConstants {


    public enum REQUEST_TYPE {
        GET, POST, MULTIPART_GET, MULTIPART_POST, DELETE, PUT, PATCH
    }

    private static final String STATUS = "status";
    public final static String SERVER_NOT_RESPONDING = "We are unable to connect the internet. " +
            "Please check your connection and try again.";

    public static String ERROR_MESSAGE = "We could not process your request at this time. Please try again later.";

    public static String BASE_URL = "https://itraxpro.com/api/v1.0/";
    //public static String BASE_URL = "https://icuepro.com/api/v1.0/";
//shankarh/1234
    //phanih/1234

    public static String HELPER_LOGIN = BASE_URL + "helperLogin";
    public static String CREATE_ESCORT_MESS_ATTENDANCE = BASE_URL + "createEscortMessAttendance";

    public static final String GET_APP_UPDATE_INFO = BASE_URL + "/getAppUpdateInfo";
}