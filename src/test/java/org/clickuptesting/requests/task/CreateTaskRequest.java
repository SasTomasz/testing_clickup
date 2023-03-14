package org.clickuptesting.requests.task;

import io.restassured.response.Response;
import org.clickuptesting.requests.base.BaseRequest;
import org.clickuptesting.urls.UrlBuilder;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {
    public static Response createTaskResponse(String listId, JSONObject body) {
        return given()
                .spec(BaseRequest.requestSetup())
                .body(body.toString())
                .when()
                .post(UrlBuilder.getTasksUrl(listId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
