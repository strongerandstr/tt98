package com.tt98.server.service;

import com.tt98.pojo.Enum.NotifyTypeEnum;
import com.tt98.pojo.entity.UserFootDO;

public interface NotifyService {
    void saveArticleNotify(UserFootDO foot, NotifyTypeEnum notifyTypeEnum);
}
