package com.tt98.server.service;

import javax.servlet.http.HttpServletRequest;

public interface ImageService {
    /**
     * 图片转存
     * @param content
     * @return
     */
    String mdImgReplace(String content);


    /**
     * 外网图片转存
     *
     * @param img
     * @return
     */
    String saveImg(String img);

    /**
     * 保存图片
     *
     * @param request
     * @return
     */
    String saveImg(HttpServletRequest request);
}
