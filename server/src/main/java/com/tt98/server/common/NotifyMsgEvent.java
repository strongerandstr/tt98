package com.tt98.server.common;

import com.tt98.pojo.Enum.NotifyTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class NotifyMsgEvent<T> extends ApplicationEvent {

    private NotifyTypeEnum notifyType;

    private T content;


    public NotifyMsgEvent(Object source, NotifyTypeEnum notifyType, T content) {
        super(source);
        this.notifyType = notifyType;
        this.content = content;
    }


}