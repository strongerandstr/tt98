package com.tt98.server.service.impl;

import com.tt98.pojo.converter.Converter;
import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.pojo.dto.UserLoginDTO;
import com.tt98.pojo.dto.UserStatisticInfoDTO;
import com.tt98.pojo.dto.YearArticleDTO;
import com.tt98.pojo.entity.UserDO;
import com.tt98.pojo.entity.UserInfoDO;
import com.tt98.pojo.entity.UserRelationDO;
import com.tt98.pojo.req.UserInfoSaveReq;
import com.tt98.server.common.ReqContext;
import com.tt98.server.dao.ArticleDAO;
import com.tt98.server.dao.UserInfoDAO;
import com.tt98.server.dao.UserRelationDAO;
import com.tt98.server.mapper.UserInfoMapper;
import com.tt98.server.mapper.UserMapper;
import com.tt98.server.service.CountService;
import com.tt98.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoDAO userInfoDAO;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CountService countService;
    @Autowired
    private UserRelationDAO userRelationDAO;
    @Autowired
    private ArticleDAO articleDAO;

    @Override
    public Long login(UserLoginDTO userLoginDTO) {
        // 检查数据库有没有这个用户

        UserDO user = userMapper.getUserByName(userLoginDTO.getUsername());
        long id = -1L;
        if(user == null){
            // 注册一个账户
            id = saveUser(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        } else if(user.getPassword().equals(userLoginDTO.getPassword())){
            id = user.getId();
        } else {
            // 密码不对
        }
        return id > 0 ? Long.valueOf(id) : null;
    }

    @Override
    public BaseUserInfoDTO queryBasicUserInfo(Long userId) {
        UserInfoDO user = userInfoDAO.getByUserId(userId);
//        if (user == null) {
//            throw ExceptionUtil.of(StatusEnum.USER_NOT_EXISTS, "userId=" + userId);
//        }
        return Converter.toDTO(user);
    }

    @Override
    public UserStatisticInfoDTO queryUserInfoWithStatistic(Long userId) {
        UserStatisticInfoDTO userStatisticInfoDTO = new UserStatisticInfoDTO();
        BaseUserInfoDTO userInfoDTO = queryBasicUserInfo(userId);
        UserStatisticInfoDTO userHomeDTO = countService.queryUserStaticInfo(userId);
        userHomeDTO = Converter.toUserHomeDTO(userHomeDTO, userInfoDTO);

        // TODO: 2024/5/15   用户资料完整度
        userHomeDTO.setInfoPercent(98);

        // 是否关注
        Long followUserId = ReqContext.getCurrentId();
        if(followUserId != null){
            UserRelationDO userRelationDO = userRelationDAO.getUserRelationByUserId(userId, followUserId);
            userHomeDTO.setFollowed((userRelationDO != null));
        } else {
            userHomeDTO.setFollowed(false);
        }

        // 加入天数
        int joinDayCount = (int) ((System.currentTimeMillis()-userHomeDTO.getCreateTime().getTime()))/ (1000 * 3600 * 24);
        userHomeDTO.setJoinDayCount(joinDayCount);

        // 创作历程
        List<YearArticleDTO> yearArticleDTOs = articleDAO.listYearArticleByUserId(userId);
        userHomeDTO.setYearArticleList(yearArticleDTOs);

        return userHomeDTO;


    }

    @Override
    public void saveUserInfo(UserInfoSaveReq req) {
        UserInfoDO userInfoDO = Converter.toDO(req);
        userInfoDAO.updateUserInfo(userInfoDO);
    }

    // 新增用户
    @Transactional
    public long saveUser(String name, String password){
        UserDO user = UserDO.builder()
                .userName(name)
                .password(password)
                .build();

        UserInfoDO userInfoDO = new UserInfoDO();

        userMapper.insert(user);
        Long id = user.getId();

        userInfoDO.setUserId(id);
        userInfoDO.setUserName(name);
        userInfoMapper.insert(userInfoDO);

        return id;
    }
}
