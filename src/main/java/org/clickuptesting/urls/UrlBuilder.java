package org.clickuptesting.urls;

import org.clickuptesting.properties.PropertiesFinder;

public class UrlBuilder {
    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAMS = "team";
    private static final String SPACE = "space";

    public static  String getSpacesUrl() {
        String teamId = PropertiesFinder.getTeamId();
        System.out.println("Send request to: " + BASE_URL + "/" + TEAMS + "/" + teamId + "/" + SPACE);
        return BASE_URL + "/" + TEAMS + "/" + teamId + "/" + SPACE;
    }

    public static String getSpaceUrl(String spaceId) {
        return BASE_URL + "/" + SPACE + "/" + spaceId;
    }

}



