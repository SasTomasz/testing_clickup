package org.clickuptesting.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskStatusResponseDto {
    private String id;
    private String status;

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CreateTaskStatusResponseDto{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
