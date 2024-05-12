package com.tt98.pojo.Enum;

import lombok.Getter;

@Getter
public enum FollowSelectEnum {
    FOLLOW("follow", "关注列表"),
    FANS("fans", "粉丝列表");

    private final String code;
    private final String desc;

    FollowSelectEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static FollowSelectEnum fromCode(String code){
        for(FollowSelectEnum value : FollowSelectEnum.values()){
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return FollowSelectEnum.FOLLOW;
    }

}
