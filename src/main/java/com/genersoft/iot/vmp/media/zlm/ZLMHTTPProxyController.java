//package com.genersoft.iot.vmp.media.zlm;
//
//import com.genersoft.iot.vmp.conf.MediaConfig;
//import com.genersoft.iot.vmp.storager.IRedisCatchStorage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping("/zlm")
//public class ZLMHTTPProxyController {
//
//
//    // private final static Logger logger = LoggerFactory.getLogger(ZLMHTTPProxyController.class);
//
//    @Autowired
//    private IRedisCatchStorage redisCatchStorage;
//
//    @Autowired
//    private MediaConfig mediaConfig;
//
//    @ResponseBody
//    @RequestMapping(value = "/**/**/**", produces = "application/json;charset=UTF-8")
//    public Object proxy(HttpServletRequest request, HttpServletResponse response){
//
//        if (redisCatchStorage.getMediaInfo() == null) {
//            return "未接入流媒体";
//        }
//        ZLMServerConfig mediaInfo = redisCatchStorage.getMediaInfo();
//        String requestURI = String.format("http://%s:%s%s?%s&%s",
//                mediaInfo.getLocalIP(),
//                mediaConfig.getHttpPort(),
//                request.getRequestURI().replace("/zlm",""),
//                mediaInfo.getHookAdminParams(),
//                request.getQueryString()
//        );
//        // 发送请求
//        RestTemplate restTemplate = new RestTemplate();
//        //将指定的url返回的参数自动封装到自定义好的对应类对象中
//        Object result = null;
//        try {
//            result = restTemplate.getForObject(requestURI,Object.class);
//
//        }catch (HttpClientErrorException httpClientErrorException) {
//            response.setStatus(httpClientErrorException.getStatusCode().value());
//        }
//        return result;
//    }
//}
