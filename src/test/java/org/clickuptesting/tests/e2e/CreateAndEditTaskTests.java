package org.clickuptesting.tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.clickuptesting.dto.request.CreateTaskRequestDto;
import org.clickuptesting.dto.response.CreateTaskResponseDto;
import org.clickuptesting.requests.list.CreateListRequest;
import org.clickuptesting.requests.space.CreateSpaceRequest;
import org.clickuptesting.requests.space.DeleteSpaceRequest;
import org.clickuptesting.requests.task.CreateTaskRequest;
import org.clickuptesting.requests.task.UpdateTaskRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateAndEditTaskTests {
    private static String spaceId;
    private static String listId;
    private static String taskId;
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CreateAndEditTaskTests.class);

    @Test
    @Order(1)
    void createSpaceTest() {
        JSONObject body = new JSONObject();
        body.put("name", "Test Space");

        Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        LOGGER.info("Space with ID: {} created", json.getString("id"));

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
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName("Test Task");

        CreateTaskResponseDto response = CreateTaskRequest.createTaskResponse(listId, taskDto);
        LOGGER.info("Created task response: {}", response);

        Assertions.assertThat(response.getName()).isEqualTo(taskDto.getName());
        Assertions.assertThat(response.getCreator().getUsername()).isEqualTo("Tomasz Sas");

        taskId = response.getId();


    }

    @Test
    @Order(4)
    void editTaskTest() {
        JSONObject jsonTask = new JSONObject();
        jsonTask.put("name", "Edit Test");
        jsonTask.put("description", "Example Test Description");

        Response response = UpdateTaskRequest.updateTaskResponse(jsonTask, taskId);
        JsonPath jsonResponse = response.jsonPath();

        LOGGER.info("Task updated with data: {}", response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);


        Assertions.assertThat(jsonResponse.getString("name")).isEqualTo(jsonTask.getString("name"));
        Assertions.assertThat(jsonResponse.getString("description")).isEqualTo(jsonTask.getString("description"));

    }

    @Test
    @Order(5)
    void closeTaskTest() {
        JSONObject jsonTask = new JSONObject();
        jsonTask.put("status", "complete");

        Response response = UpdateTaskRequest.updateTaskResponse(jsonTask, taskId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonResponse = response.jsonPath();

        Assertions.assertThat(jsonResponse.getString("status.status")).isEqualTo(jsonResponse.getString("status.status"));
    }


    @Test
    @Order(6)
    void deleteSpaceTest() {
        Response response = DeleteSpaceRequest.deleteSpaceResponse(spaceId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

}