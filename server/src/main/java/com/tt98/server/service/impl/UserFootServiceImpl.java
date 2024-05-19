package com.tt98.server.service.impl;

import com.tt98.pojo.Enum.DocumentTypeEnum;
import com.tt98.pojo.Enum.OperateTypeEnum;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.entity.UserFootDO;
import com.tt98.server.dao.UserFootDAO;
import com.tt98.server.service.UserFootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class UserFootServiceImpl implements UserFootService {
    @Autowired
    private UserFootDAO userFootDAO;

    @Override
    public List<Long> queryUserReadArticleList(Long userId, PageParamDTO pageParamDTO) {
        return userFootDAO.listReadArticleByUserId(userId, pageParamDTO);
    }

    @Override
    public List<Long> queryUserCollectionArticleList(Long userId, PageParamDTO pageParamDTO) {
        return userFootDAO.listCollectedArticlesByUserId(userId, pageParamDTO);
    }

    @Override
    public UserFootDO saveOrUpdateUserFoot(DocumentTypeEnum documentType, Long documentId, Long authorId, Long userId, OperateTypeEnum operate) {
        // 查询是否有该足迹， 有则更新，没有则插入
        UserFootDO readUserFootDO = userFootDAO.getByDocumentAndUserId(documentId, documentType.getCode(), userId);
        if(readUserFootDO == null){
            readUserFootDO = new UserFootDO();
            readUserFootDO.setUserId(userId);
            readUserFootDO.setDocumentId(documentId);
            readUserFootDO.setDocumentType(documentType.getCode());
            readUserFootDO.setDocumentUserId(authorId);
            setUserFootStat(readUserFootDO, operate);
            userFootDAO.save(readUserFootDO);
        } else if(setUserFootStat(readUserFootDO, operate)){
            readUserFootDO.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            userFootDAO.updateById(readUserFootDO);
        }
        return readUserFootDO;
    }

    private boolean setUserFootStat(UserFootDO userFootDO, OperateTypeEnum operate) {
        switch (operate){
            case READ:
                userFootDO.setReadStat(1);
                return true;
            case PRAISE:
            case CANCEL_PRAISE:
                return compareAndUpdate(userFootDO::getPraiseStat, userFootDO::setPraiseStat, operate.getDbStatCode());
            case COLLECTION:
            case CANCEL_COLLECTION:
                return compareAndUpdate(userFootDO::getCollectionStat, userFootDO::setCollectionStat, operate.getDbStatCode());
            case COMMENT:
            case DELETE_COMMENT:
                return compareAndUpdate(userFootDO::getCommentStat, userFootDO::setCommentStat, operate.getDbStatCode());
            default:
                return false;
        }
    }

    private <T> boolean compareAndUpdate(Supplier<T> supplier, Consumer<T> consumer, T input) {
        if(Objects.equals(supplier.get(), input)){
            return false;
        }
        consumer.accept(input);
        return true;
    }
}
