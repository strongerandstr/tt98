package com.tt98.server.controller.user;

import com.tt98.pojo.Enum.FollowSelectEnum;
import com.tt98.pojo.Enum.HomeSelectEnum;
import com.tt98.pojo.dto.ArticleDTO;
import com.tt98.pojo.dto.PageParamDTO;
import com.tt98.pojo.dto.TagSelectDTO;
import com.tt98.pojo.dto.UserStatisticInfoDTO;
import com.tt98.pojo.vo.PageListVO;
import com.tt98.server.service.ArticleReadService;
import com.tt98.server.service.ArticleService;
import com.tt98.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.tt98.pojo.vo.UserHomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleReadService articleReadService;

    @GetMapping("/home")
    public String home(@RequestParam(name = "userId") Long userId,
                       @RequestParam(name = "followSelectType", required = false) String followSelectType,
                       @RequestParam(name = "homeSelectType", required = false) String homeSelectType,
                       Model model){
        log.info("查看用户主页: userId = " + userId);
        UserHomeVO vo = new UserHomeVO();
        vo.setHomeSelectType(StringUtils.isBlank(homeSelectType) ? HomeSelectEnum.ARTICLE.getCode() : homeSelectType);
        vo.setFollowSelectType(StringUtils.isBlank(followSelectType) ? FollowSelectEnum.FOLLOW.getCode() : followSelectType);

        vo.setUserHome(userService.queryUserInfoWithStatistic(userId));
        setHomeSelectList(vo, userId);
        model.addAttribute("vo", vo);
        return "views/user/index";
    }

    private void setHomeSelectList(UserHomeVO vo, Long userId) {
        PageParamDTO pageParamDTO = PageParamDTO.newPageInstance();
        HomeSelectEnum select = HomeSelectEnum.fromCode(vo.getHomeSelectType());
        if(select == null){
            return;
        }

        switch (select) {
            case ARTICLE:
            case READ:
            case COLLECTION:
                PageListVO<ArticleDTO> dto = articleReadService.queryArticlesByUserAndType(userId, pageParamDTO, select);
                vo.setHomeSelectList(dto);
            case FOLLOW:
                // 关注用户与被关注用户
                // 获取选择标签
                List<TagSelectDTO> followSelectTags = followSelectTags(vo.getFollowSelectType());
                vo.setFollowSelectTags(followSelectTags);
                initFollowFansList(vo, userId, pageParamDTO);
                return;
            default:
        }
    }

    /**
     * 返回关注用户选择列表标签
     *
     * @param selectType
     * @return
     */
    private List<TagSelectDTO> followSelectTags(String selectType) {
        List<TagSelectDTO> tags = new ArrayList<>();
        followSelectTags.forEach(tag -> {
            TagSelectDTO tagSelectDTO = new TagSelectDTO();
            tagSelectDTO.setSelectType(tag);
            tagSelectDTO.setSelectDesc(FollowSelectEnum.fromCode(tag).getDesc());
            tagSelectDTO.setSelected(selectType.equals(tag));
            tags.add(tagSelectDTO);
        });
        return tags;
    }

    private void initFollowFansList(UserHomeVO vo, long userId, PageParam pageParam) {
        PageListVo<FollowUserInfoDTO> followList;
        boolean needUpdateRelation = false;
        if (vo.getFollowSelectType().equals(FollowTypeEnum.FOLLOW.getCode())) {
            followList = userRelationService.getUserFollowList(userId, pageParam);
        } else {
            // 查询粉丝列表时，只能确定粉丝关注了userId，但是不能反向判断，因此需要再更新下映射关系，判断userId是否有关注这个用户
            followList = userRelationService.getUserFansList(userId, pageParam);
            needUpdateRelation = true;
        }

        Long loginUserId = ReqInfoContext.getReqInfo().getUserId();
        if (!Objects.equals(loginUserId, userId) || needUpdateRelation) {
            userRelationService.updateUserFollowRelationId(followList, loginUserId);
        }
        vo.setFollowList(followList);
    }

}
