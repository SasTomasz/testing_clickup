package org.clickuptesting.requests.space;

import io.restassured.response.Response;
import org.clickuptesting.requests.base.BaseRequest;
import org.clickuptesting.urls.UrlBuilder;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UpdateSpaceRequest {
    public static Response updateSpaceResponse(JSONObject body, String spaceId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .body(body.toString())
                .when()
                .put(UrlBuilder.getSpaceUrl(spaceId))
                .then()
                .extract()
                .response();
    }
}
