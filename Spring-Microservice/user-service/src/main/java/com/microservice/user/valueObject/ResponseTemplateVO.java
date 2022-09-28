package com.microservice.user.valueObject;

import com.microservice.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Users users;
    private Department department;
    public static String message;
}
