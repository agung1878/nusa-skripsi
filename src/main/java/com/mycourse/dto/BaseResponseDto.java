package com.mycourse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseDto {
    private String responseCode;
    private String responseMessage;
    private Object data;
}

