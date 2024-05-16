package com.tt98.server.controller.user;


import com.tt98.pojo.Enum.StatusEnum;
import com.tt98.pojo.Result;
import com.tt98.pojo.req.UserInfoSaveReq;
import com.tt98.server.common.ReqContext;
import com.tt98.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("user/api")
@Slf4j
public class UserRestController {
    @Autowired
    private UserService userService;

    /**
     * 保存用户详情
     *
     * @param req
     * @return
     * @throws Exception
     */
    @PostMapping(path = "saveUserInfo")
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> saveUserInfo(@RequestBody UserInfoSaveReq req) {
        if (req.getUserId() == null || !Objects.equals(req.getUserId(), ReqContext.getCurrentId())) {
            // 不能修改其他用户的信息
            return Result.fail(StatusEnum.FORBID_ERROR_MIXED, "无权修改");
        }
        userService.saveUserInfo(req);
        return Result.success(true);
    }
}
