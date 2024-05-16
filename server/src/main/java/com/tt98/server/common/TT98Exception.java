package com.tt98.server.common;

import com.tt98.pojo.Enum.StatusEnum;
import com.tt98.pojo.Status;
import lombok.Getter;

public class TT98Exception extends RuntimeException{
    @Getter
    private Status status;

    public TT98Exception(Status status) {
        this.status = status;
    }

    public TT98Exception(int code, String msg) {
        this.status = Status.newStatus(code, msg);
    }

    public TT98Exception(StatusEnum statusEnum, Object... args) {
        this.status = Status.newStatus(statusEnum, args);
    }

}
