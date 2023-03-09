package org.clickuptesting.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.clickuptesting.requests.space.CreateSpaceRequest;
import org.clickuptesting.requests.space.DeleteSpaceRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class CreateSpaceTest {
    @Test
    void createSpaceTest() {
        String spaceId;
        JSONObject body = new JSONObject();
        body.put("name", "Test Space");

        final Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(body.get("name"));

        spaceId = json.getString("id");

        // delete space
        Response deleteResponse = DeleteSpaceRequest.deleteSpaceResponse(spaceId);

        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);


    }
}
