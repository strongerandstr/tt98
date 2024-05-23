package com.tt98.server.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tt98.pojo.entity.NotifyMsgDO;
import com.tt98.server.mapper.NotifyMsgMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class NotifyMsgDAO extends ServiceImpl<NotifyMsgMapper, NotifyMsgDO> {

    /**
     * 查询消息记录， 用于幂等过滤
     * @param msg
     * @return
     */
    public NotifyMsgDO getByUserIdRelatedIdAndType(NotifyMsgDO msg) {
        List<NotifyMsgDO> list = lambdaQuery().eq(NotifyMsgDO::getNotifyUserId, msg.getNotifyUserId())
                .eq(NotifyMsgDO::getOperateUserId, msg.getOperateUserId())
                .eq(NotifyMsgDO::getType, msg.getType())
                .eq(NotifyMsgDO::getRelatedId, msg.getRelatedId())
                .page(new Page<>(0, 1))
                .getRecords();
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
