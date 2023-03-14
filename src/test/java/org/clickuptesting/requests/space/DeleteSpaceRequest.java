package org.clickuptesting.requests.space;

import io.restassured.response.Response;
import org.clickuptesting.requests.base.BaseRequest;
import org.clickuptesting.urls.UrlBuilder;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {
    public static Response deleteSpaceResponse(String spaceId) {
        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .delete(UrlBuilder.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
