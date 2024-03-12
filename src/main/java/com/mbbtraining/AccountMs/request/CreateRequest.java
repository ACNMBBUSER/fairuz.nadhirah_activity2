package com.mbbtraining.AccountMs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRequest {

    private String id;
    private String name;
    private String accountType;

}
