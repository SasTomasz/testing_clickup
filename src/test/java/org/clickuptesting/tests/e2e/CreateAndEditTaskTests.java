package org.clickuptesting.tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.clickuptesting.requests.list.CreateListRequest;
import org.clickuptesting.requests.space.CreateSpaceRequest;
import org.clickuptesting.requests.space.DeleteSpaceRequest;
import org.clickuptesting.requests.task.CreateTaskRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateAndEditTaskTests {
    private static String spaceId;
    private static String listId;
    private static String taskId;

    @Test
    @Order(1)
    void createSpaceTest() {
        JSONObject body = new JSONObject();
        body.put("name", "Test Space");

        Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(body.getString("name"));

        spaceId = json.getString("id");
    }

    @Test
    @Order(2)
    void createListTest() {
        JSONObject body = new JSONObject();
        body.put("name", "Test List");

        Response response = CreateListRequest.createListResponse(spaceId, body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(body.getString("name"));

        listId = json.getString("id");
    }

    @Test
    @Order(3)
    void createTaskTest() {
        JSONObject body = new JSONObject();
        body.put("name", "Test Task");

        Response response = CreateTaskRequest.createTaskResponse(listId, body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(body.getString("name"));

        taskId = json.getString("id");


    }

    @Test
    @Order(4)
    void deleteSpaceTest() {
        Response response = DeleteSpaceRequest.deleteSpaceResponse(spaceId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

}
