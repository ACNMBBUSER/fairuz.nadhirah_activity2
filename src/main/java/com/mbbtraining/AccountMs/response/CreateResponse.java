package com.mbbtraining.AccountMs.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateResponse {

//    @JsonProperty("answer")
    private String answer;

    public CreateResponse() {
    }

    public CreateResponse(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
