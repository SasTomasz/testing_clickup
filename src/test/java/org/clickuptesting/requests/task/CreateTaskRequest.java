package org.clickuptesting.requests.task;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.clickuptesting.dto.request.CreateTaskRequestDto;
import org.clickuptesting.dto.response.CreateTaskResponseDto;
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

    public static CreateTaskResponseDto createTaskResponse(String listId, CreateTaskRequestDto body) {
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
