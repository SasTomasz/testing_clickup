package org.clickuptesting.urls;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.clickuptesting.properties.PropertiesFinder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlBuilder {
    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM = "/team";
    private static final String SPACE = "/space";
    private static final String LIST = "/list";
    private static final String TASK = "/task";

    public static String getBaseUrl() {
        return BASE_URL;
    }
    public static  String getSpacesUrl() {
        String teamId = PropertiesFinder.getTeamId();
        return TEAM + "/" + teamId + SPACE;
    }

    public static String getSpaceUrl(String spaceId) {
        return SPACE + "/" + spaceId;
    }

    public static String getListsUrl(String spaceId) {
        return getSpaceUrl(spaceId) + LIST;
    }

    public static String getListUrl(String listId) {
        return LIST + "/" + listId;
    }

    public static String getTasksUrl(String listId) {
        return getListUrl(listId) + TASK;
    }

    public static String getTaskUrl(String taskId) {
        return TASK + "/" + taskId;
    }
}



