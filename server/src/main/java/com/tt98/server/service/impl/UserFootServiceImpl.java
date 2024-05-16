package com.tt98.server.service.impl;

import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.server.dao.UserFootDAO;
import com.tt98.server.service.UserFootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
