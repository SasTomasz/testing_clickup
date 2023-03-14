package org.clickuptesting.tests.space;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.clickuptesting.requests.space.CreateSpaceRequest;
import org.clickuptesting.requests.space.DeleteSpaceRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    @DisplayName("Create space with valid name")
    @ParameterizedTest(name = "Space name: {0}")
    @MethodSource("sampleSpaceNameData")
    void createSpaceWithValidName(String name) {
        JSONObject body = new JSONObject();
        body.put("name", name);
        Response response = CreateSpaceRequest.createSpaceRequest(body);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(name);

        String spaceId = json.getString("id");

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
}
