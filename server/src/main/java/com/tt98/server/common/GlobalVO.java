package com.tt98.server.common;

import com.tt98.pojo.dto.BaseUserInfoDTO;
import com.tt98.pojo.vo.SeoTagVO;
import com.tt98.pojo.vo.SiteCntVO;
import com.tt98.server.config.GlobalViewConfig;
import lombok.Data;


import java.util.List;

@Data
public class GlobalVO {

    private GlobalViewConfig siteInfo;

    private SiteCntVO siteStatisticInfo;

    private SiteCntVO todaySiteStatisticInfo;

    private String env;

    private Boolean isLogin;

    private BaseUserInfoDTO user;
    private Integer msgNum;

    private Integer onlineCnt;
    private String currentDomain;
    private List<SeoTagVO> ogp;
    private String jsonLd;

}