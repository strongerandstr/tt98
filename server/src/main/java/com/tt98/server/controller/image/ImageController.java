//package com.tt98.server.controller.image;
//
//
//import com.tt98.pojo.Enum.StatusEnum;
//import com.tt98.pojo.Result;
//import com.tt98.pojo.vo.ImageVO;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//
//
//@RequestMapping(path = {"image/", "admin/image/", "api/admin/image/",})
//@RestController
//@Slf4j
//public class ImageController {
//
//    @Autowired
//    private ImageService imageService;
//
//    /**
//     * 图片上传
//     *
//     * @return
//     */
//
//    @RequestMapping(path = "upload")
//    public Result<ImageVO> upload(HttpServletRequest request) {
//        ImageVO imageVO = new ImageVO();
//        try {
//            String imagePath = imageService.saveImg(request);
//            imageVO.setImagePath(imagePath);
//        } catch (Exception e) {
//            log.error("save upload file error!", e);
//            return Result.fail(StatusEnum.UPLOAD_PIC_FAILED);
//        }
//        return Result.success(imageVO);
//    }
//
//    /**
//     * 转存图片
//     *
//     * @param imgUrl
//     * @return
//     */
//    @RequestMapping(path = "save")
//    public ResVo<ImageVo> save(@RequestParam(name = "img", defaultValue = "") String imgUrl) {
//        ImageVo imageVo = new ImageVo();
//        if (StringUtils.isBlank(imgUrl)) {
//            return ResVo.ok(imageVo);
//        }
//
//        String url = imageService.saveImg(imgUrl);
//        imageVo.setImagePath(url);
//        return ResVo.ok(imageVo);
//    }
//}
