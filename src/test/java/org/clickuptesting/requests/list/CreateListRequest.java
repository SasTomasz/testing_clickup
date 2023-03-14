package org.clickuptesting.requests.list;

import io.restassured.response.Response;
import org.clickuptesting.requests.base.BaseRequest;
import org.clickuptesting.urls.UrlBuilder;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateListRequest {
    public static Response createListResponse(String spaceId, JSONObject body) {
        return given()
                .spec(BaseRequest.requestSetup())
                .body(body.toString())
                .when()
                .post(UrlBuilder.getListsUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
