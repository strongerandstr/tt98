package com.tt98.server.service.impl;

import com.tt98.pojo.Enum.NotifyStatEnum;
import com.tt98.pojo.Enum.NotifyTypeEnum;
import com.tt98.pojo.entity.NotifyMsgDO;
import com.tt98.pojo.entity.UserFootDO;
import com.tt98.server.dao.NotifyMsgDAO;
import com.tt98.server.service.NotifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Resource
    private NotifyMsgDAO notifyMsgDAO;

    @Override
    public void saveArticleNotify(UserFootDO foot, NotifyTypeEnum notifyTypeEnum) {
        NotifyMsgDO msg = new NotifyMsgDO().setRelatedId(foot.getDocumentId())
                .setNotifyUserId(foot.getDocumentUserId())
                .setOperateUserId(foot.getUserId())
                .setType(notifyTypeEnum.getType())
                .setState(NotifyStatEnum.UNREAD.getStat())
                .setMsg("");
        NotifyMsgDO record = notifyMsgDAO.getByUserIdRelatedIdAndType(msg);
        if(record == null){
            // 若之前已经有对应的通知，则不重复记录；因为一个用户对一篇文章，可以重复的点赞、取消点赞，但是最终我们只通知一次
            notifyMsgDAO.save(msg);
        }
    }
}
