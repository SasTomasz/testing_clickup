package org.clickuptesting.requests.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
        requestBuilder.addFilter(new AllureRestAssured());
        return requestBuilder.build();
    }

    public static RequestSpecification requestSetupWithLogs() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(UrlBuilder.getBaseUrl());
        requestBuilder.addHeader("Authorization", PropertiesFinder.getAPIToken());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        requestBuilder.addFilter(new AllureRestAssured());
        return requestBuilder.build();
    }
}
