package com.tt98.server.service.impl;

import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.vo.PageVO;
import com.tt98.server.dao.TagDAO;
import com.tt98.server.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDAO tagDAO;

    @Override
    public PageVO<TagDTO> queryTags(String key, PageParamDTO pageParamDTO) {
        List<TagDTO> tagDTOs = tagDAO.getTags(key, pageParamDTO);
        Integer count = tagDAO.countTag(key);
        return PageVO.build(tagDTOs, pageParamDTO.getPageSize(), pageParamDTO.getPageNum(), count);
    }
}
