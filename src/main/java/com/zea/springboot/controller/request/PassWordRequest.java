package com.zea.springboot.controller.request;

import lombok.Data;

@Data
public class PassWordRequest {
    private String username;
    private String password;
    private String newPW;
}
