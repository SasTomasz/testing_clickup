package org.clickuptesting.requests.space;

import io.restassured.response.Response;
import org.clickuptesting.requests.base.BaseRequest;
import org.clickuptesting.urls.UrlBuilder;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateSpaceRequest {
    public static Response createSpaceRequest(JSONObject body) {
        return given()
                .spec(BaseRequest.requestSetup())
                .body(body.toString())
                .when()
                .post(UrlBuilder.getSpacesUrl())
                .then()
                .extract()
                .response();
    }

}
