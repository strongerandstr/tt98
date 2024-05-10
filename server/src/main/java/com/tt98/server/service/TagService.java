package com.tt98.server.service;

import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.dto.TagDTO;
import com.tt98.pojo.vo.PageVO;

public interface TagService {


    PageVO<TagDTO> queryTags(String key, PageParamDTO newPageInstance);
}
