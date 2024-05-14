package com.tt98.pojo.Enum;

import lombok.Getter;

@Getter
public enum PushStatusEnum {

    OFFLINE(0, "未发布"),
    ONLINE(1,"已发布"),
    REVIEW(2, "审核");

    private final int code;
    private final String desc;

    PushStatusEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static PushStatusEnum fromCode(int code){
        for(PushStatusEnum yesOrNoEnum : PushStatusEnum.values()){
            if(yesOrNoEnum.getCode() == code){
                return yesOrNoEnum;
            }
        }
        return PushStatusEnum.OFFLINE;
    }
}
