package org.clickuptesting.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}
