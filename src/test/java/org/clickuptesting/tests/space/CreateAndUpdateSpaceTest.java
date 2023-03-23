package org.clickuptesting.tests.space;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.clickuptesting.requests.space.CreateSpaceRequest;
import org.clickuptesting.requests.space.DeleteSpaceRequest;
import org.clickuptesting.requests.space.UpdateSpaceRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;

import java.util.stream.Stream;

class CreateAndUpdateSpaceTest {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CreateAndUpdateSpaceTest.class);

    @Test
    void createAndUpdateSpaceTest() {
        String spaceId;
        JSONObject body = new JSONObject();
        body.put("name", "Test Space");

        final Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(body.get("name"));

        spaceId = json.getString("id");

        // update space
        body.put("name", "Update Test");
        updateSpace(body, spaceId);

        // delete space
        Response deleteResponse = DeleteSpaceRequest.deleteSpaceResponse(spaceId);

        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);


    }

    @DisplayName("Create space with valid name")
    @ParameterizedTest(name = "Space name: {0}")
    @MethodSource("sampleSpaceNameData")
    void createAndUpdateSpaceWithValidName(String name) {
        JSONObject body = new JSONObject();
        body.put("name", name);
        Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(name);

        String spaceId = json.getString("id");

        // update space
        body.put("name", "Update Test");
        updateSpace(body, spaceId);

        // delete space
        Response deleteSpaceResponse = DeleteSpaceRequest.deleteSpaceResponse(spaceId);

        Assertions.assertThat(deleteSpaceResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

    }

    private static Stream<Arguments> sampleSpaceNameData() {
        return Stream.of(
                Arguments.of("NEW SPACE FROM JAVA"),
                Arguments.of("NEW_SPACE_FROM_JAVA"),
                Arguments.of("newSpaceFromJava"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("&"),
                Arguments.of("*"),
                Arguments.of("("),
                Arguments.of(")")
        );
    }

    private void updateSpace(JSONObject body, String spaceId) {
        LOGGER.info("Body in updateSpace method: {}", body);
        Response response = UpdateSpaceRequest.updateSpaceResponse(body, spaceId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonResponse = response.jsonPath();

        Assertions.assertThat(jsonResponse.getString("name")).isEqualTo(body.getString("name"));
    }
}