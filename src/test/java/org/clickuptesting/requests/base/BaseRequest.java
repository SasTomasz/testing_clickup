package org.clickuptesting.requests.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.clickuptesting.properties.PropertiesFinder;

public class BaseRequest {
    public static RequestSpecification requestSetup() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.addHeader("Authorization", PropertiesFinder.getAPIToken());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addFilter(new ResponseLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        return requestBuilder.build();
    }
}
