package com.mbbtraining.AccountMs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mbbtraining.AccountMs.request.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Component
@NoArgsConstructor
public class ResponseHandler<T> {


    @JsonProperty("Status")
    public Status Status;

    @JsonProperty("Data")
    public T Data;

    public ResponseHandler(Status status){
        this.Status = status;
    }

    public ResponseHandler(Status status, T data){
        this.Status = status;
        this.Data = data;
    }

    public ResponseHandler generateFailResponse(String error){
        Status status = new Status("SF001", "Generic Fail", "Operation Failed", Instant.now().toEpochMilli(), error);
        return new ResponseHandler(status, (Object) null);
    }

    public ResponseHandler generateSuccessResponse(Object data){
        Status status = new Status("SU001", "Success", "Operation Completed Successfully", Instant.now().toEpochMilli(), (List) null);
        return new ResponseHandler(status, data);
    }



}
