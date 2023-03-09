package org.clickuptesting.properties;

import java.util.PropertyResourceBundle;

public class PropertiesFinder {
    private static final String TEAM_ID = "clickup.teamId";
    private static final String API_TOKEN = "clickup.apiToken";

    private static String getProperty(String property) {
        return PropertyResourceBundle.getBundle("clickup").getString(property);
    }

    public static String getTeamId() {
        return getProperty(TEAM_ID);
    }

    public static String getAPIToken() {
        return getProperty(API_TOKEN);
    }
}
