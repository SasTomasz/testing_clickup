package org.clickuptesting.requests.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.clickuptesting.properties.PropertiesFinder;
import org.clickuptesting.urls.UrlBuilder;

public class BaseRequest {
    public static RequestSpecification requestSetup() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(UrlBuilder.getBaseUrl());
        requestBuilder.addHeader("Authorization", PropertiesFinder.getAPIToken());
        requestBuilder.setContentType(ContentType.JSON);
        return requestBuilder.build();
    }
}
