package org.clickuptesting.requests.task;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.clickuptesting.requests.base.BaseRequest;
import org.clickuptesting.urls.UrlBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UpdateTaskRequest.class);

    public static Response updateTaskResponse(JSONObject body, String taskId) {
        LOGGER.info("Body = {}", body);
        return given()
                .spec(BaseRequest.requestSetup())
                .body(body.toString())
                .when()
                .put(UrlBuilder.getTaskUrl(taskId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().ifError()
                .extract()
                .response();
    }
}
