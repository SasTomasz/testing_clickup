package org.clickuptesting.requests.task;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.clickuptesting.dto.request.CreateTaskRequestDto;
import org.clickuptesting.dto.response.CreateTaskResponseDto;
import org.clickuptesting.requests.base.BaseRequest;
import org.clickuptesting.urls.UrlBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CreateTaskRequest.class);
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

    public static CreateTaskResponseDto createTaskResponse(String listId, CreateTaskRequestDto body) {
        LOGGER.info("Body: {}", body);
        return given()
                .spec(BaseRequest.requestSetup())
                .body(body)
                .when()
                .post(UrlBuilder.getTasksUrl(listId))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().ifError()
                .extract()
                .response()
                .as(CreateTaskResponseDto.class);
    }
}
