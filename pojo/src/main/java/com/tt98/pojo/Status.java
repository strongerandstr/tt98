package com.tt98.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private int code;
    private String msg;

    public static Status newStatus(int code, String msg){
        return new Status(code, msg);
    }
}
