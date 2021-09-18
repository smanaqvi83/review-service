package com.assignment.review.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("description")
    private String description;


    public ErrorResponse(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public ErrorResponse() {
    }

    @JsonProperty("error_code")
    public String getErrorCode() {
        return this.errorCode;
    }

    @JsonProperty("error_code")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public static class ErrorResponseBuilder {
        private String errorCode;
        private String description;


        public ErrorResponse.ErrorResponseBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorResponse.ErrorResponseBuilder description(String description) {
            this.description = description;
            return this;
        }
        public ErrorResponse build() {
            return new ErrorResponse(this.errorCode, this.description);
        }
    }
}
