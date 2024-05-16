package com.tt98.pojo;

import com.tt98.pojo.Enum.StatusEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Status status;

    private T result;      // 返回的实体结果

    public Result(){}

    public Result(Status status){
        this.status = status;
    }

    public Result(T t){
        status = Status.newStatus(StatusEnum.SUCCESS);
        this.result = t;
    }


    public static <T> Result<T> success(T t){
        return new Result(t);
    }


    public static <T> Result<T> fail(String msg){
        Status status = Status.newStatus(1, msg);
        return new Result(status);
    }

    public static <T> Result<T> fail(StatusEnum status, Object... args) {
        return new Result<>(Status.newStatus(status, args));
    }
    public static <T> Result<T> fail(Status status) {
        return new Result<>(status);
    }
}
