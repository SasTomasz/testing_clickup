package org.clickuptesting.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskResponseDto {
    private String id;
    @JsonProperty("custom_id")
    private String customId;
    private String name;
    @JsonProperty("text_content")
    private String textContent;
    private CreateTaskCreatorResponseDto creator;
    private CreateTaskStatusResponseDto status;

    public String getId() {
        return id;
    }

    public String getCustomId() {
        return customId;
    }

    public String getName() {
        return name;
    }

    public String getTextContent() {
        return textContent;
    }

    public CreateTaskCreatorResponseDto getCreator() {
        return creator;
    }

    public CreateTaskStatusResponseDto getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CreateTaskResponseDto{" +
                "id='" + id + '\'' +
                ", customId='" + customId + '\'' +
                ", name='" + name + '\'' +
                ", textContent='" + textContent + '\'' +
                ", creator=" + creator +
                ", status=" + status +
                '}';
    }
}
