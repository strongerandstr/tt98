//package com.tt98.server.service.impl;
//
//import com.tt98.server.service.ImageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Service
//public class ImageServiceImpl implements ImageService {
//    @Autowired
//    private ImageUploader imageUploader;
//
//    @Override
//    public String mdImgReplace(String content) {
//        return null;
//    }
//
//    /**
//     * 外网图片转存
//     * @param img
//     * @return
//     */
//    @Override
//    public String saveImg(String img) {
//        if(imageUploader.uploadIgnore(img)){
//            // 已经转存过，不需要再次转存
//            return img;
//        }
//        try {
//            String ans =
//        }
//    }
//
//    @Override
//    public String saveImg(HttpServletRequest request) {
//        return null;
//    }
//}
