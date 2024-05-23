package com.tt98.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@TableName("notify_msg")
public class NotifyMsgDO {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 消息关联的主体
     * 如 文章收藏、评论、回复评论、点赞消息，这里存文章ID
     * 如系统通知消息时，这里存的是系统通知消息正文主键，也可以是0
     * 如关注，这里就是0
     */
    private Long relatedId;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 消息通知的用户id
     */
    private Long notifyUserId;

    /**
     * 触发这个消息的用户id
     */
    private Long operateUserId;

    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 0 未查看 1 已查看
     */
    private Integer state;

    private Timestamp createTime;

    private Timestamp updateTime;
}
