package com.tt98.pojo.converter;

import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.pojo.dto.CategoryDTO;
import com.tt98.pojo.dto.UserStatisticInfoDTO;
import com.tt98.pojo.entity.CategoryDO;
import com.tt98.pojo.entity.UserInfoDO;
import com.tt98.pojo.req.UserInfoSaveReq;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供一些将DTO和DO互相转换的方法
 */

public class Converter {

    public static CategoryDTO toDTO(CategoryDO categoryDO){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory(categoryDO.getCategoryName());
        categoryDTO.setCategoryId(categoryDO.getId());
        categoryDTO.setStatus(categoryDO.getStatus());
        categoryDTO.setRank(categoryDO.getRank());
        // TODO: 2024/5/13 categoryDTO.selected是什么
        categoryDTO.setSelected(Boolean.FALSE);

        return categoryDTO;
    }

    public static List<CategoryDTO> toDTOList(List<CategoryDO> categoryDOList){
        List<CategoryDTO> categoryDTOList = new ArrayList<>(categoryDOList.size());

        for (CategoryDO categoryDO: categoryDOList) {
            categoryDTOList.add(toDTO(categoryDO));
        }

        return categoryDTOList;
    }


    public static BaseUserInfoDTO toDTO(UserInfoDO userInfoDO){
        BaseUserInfoDTO dto = new BaseUserInfoDTO();
        dto.setId(userInfoDO.getId());
        dto.setDeleted(userInfoDO.getDeleted());
        dto.setExtend(userInfoDO.getExtend());
        dto.setPhoto(userInfoDO.getPhoto());
        dto.setPosition(userInfoDO.getPosition());
        dto.setProfile(userInfoDO.getProfile());
//        dto.setRegion(userInfoDO.getRe);
//        dto.setRole(userInfoDO.get);
        dto.setCompany(userInfoDO.getCompany());
        dto.setUserId(userInfoDO.getUserId());
        dto.setUserName(userInfoDO.getUserName());
        dto.setCreateTime(userInfoDO.getCreateTime());
        dto.setUpdateTime(userInfoDO.getUpdateTime());
        return dto;
    }

    public static UserStatisticInfoDTO toUserHomeDTO(UserStatisticInfoDTO userHomeDTO, BaseUserInfoDTO baseUserInfoDTO) {
        if(baseUserInfoDTO == null){
            return null;
        }
        BeanUtils.copyProperties(baseUserInfoDTO, userHomeDTO);
        return userHomeDTO;
    }

    public static UserInfoDO toDO(UserInfoSaveReq req) {
        if (req == null) {
            return null;
        }
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserId(req.getUserId());
        userInfoDO.setUserName(req.getUserName());
        userInfoDO.setPhoto(req.getPhoto());
        userInfoDO.setPosition(req.getPosition());
        userInfoDO.setCompany(req.getCompany());
        userInfoDO.setProfile(req.getProfile());
        return userInfoDO;
    }
}
