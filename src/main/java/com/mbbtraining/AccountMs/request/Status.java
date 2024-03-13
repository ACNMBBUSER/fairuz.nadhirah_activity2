package com.mbbtraining.AccountMs.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    @JsonProperty("Code")
    public String Code;

    @JsonProperty("Description")
    public String Description;

    @JsonProperty("Message")
    public String Message;

    @JsonProperty("Timestamp")
    public long Timestamp;

    @JsonProperty("Errors")
    public List<String> Errors;

    public Status(String code, String description, String message, long timestamp, String error) {
        Code = code;
        Description = description;
        Message = message;
        Timestamp = timestamp;
        Errors = new ArrayList<>();
        Errors.add(error);
    }

    public Status(String code, String description, String message, long timestamp, List<String> Errors) {
        Code = code;
        Description = description;
        Message = message;
        Timestamp = timestamp;
        Errors = Errors;
    }
}
