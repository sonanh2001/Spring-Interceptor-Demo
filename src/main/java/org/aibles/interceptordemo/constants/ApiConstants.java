package org.aibles.interceptordemo.constants;

public class ApiConstants {
  public static final String API_SOURCES = "api";

  public static final String VERSION = "v1";

  public static final String USER_RESOURCES = "users";

  public static final String USERS_API_URI =
      '/' + API_SOURCES + '/' + VERSION + '/' + USER_RESOURCES;
  public static final String LOGIN_URI = USERS_API_URI + '/' + "login";
}
