package com.xhwl.recruitment.controller.admin;


import com.xhwl.recruitment.bean.PhoneCaptchaResponseBean;
import com.xhwl.recruitment.dao.DwPersonalInformationRepository;
import com.xhwl.recruitment.dao.PersonalInformationRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.domain.*;
import com.xhwl.recruitment.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @Author: kepiao
 * @Description: 管理员发送短信的api
 * @Date: Create in 上午11:33 2018/5/8
 **/
@RestController
public class AdminNoteController {
    @Autowired
    DwPersonalInformationRepository dwPersonalInformationRepository;
    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;

    @PostMapping("/admin/onNote/{resumeId}")//发送通过短信
    public PhoneCaptchaResponseBean sendOnNoteByResumeId(@PathVariable("resumeId") Long resumeId, @RequestParam("month") String month,
                                                         @RequestParam("day")String day,@RequestParam("hour")String hour,@RequestParam("minute")String minute){
        ResumeDeliverEntity resumeDelieverEntity=resumeDeliverRepository.findById(resumeId);
        Long dw_resume_id=resumeDelieverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity=dwPersonalInformationRepository.findByResumeId(dw_resume_id);
        String name=dwPersonalInformationEntity.getName();
        String phone=dwPersonalInformationEntity.getTelephone();
        Random ran = new Random();
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        messageConverters.add(converter);
        messageConverters.add(formHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("cmd", "send");
        requestParam.set("eprId", "619");
        requestParam.set("userId", "xhwlxyzp");
        requestParam.set("timestamp", String.valueOf(System.currentTimeMillis()));
        requestParam.set("key", encryption("619" + "xhwlxyzp" + "xyzp00" + requestParam.getFirst("timestamp")));
        requestParam.set("msgId", String.valueOf(ran.nextInt()));
        requestParam.set("format", "1");
        requestParam.set("mobile", phone);

        requestParam.set("content", name+"您好，恭喜您成功通过本公司的笔试及面试环节！请您于"+month+"月"+day+"日"+hour+"时"+minute+"分准时到岗，期待您的加入" );
        HttpHeaders requestHeaders = new HttpHeaders();

        /**存短信验证码到redis
         String key = username + "_PhoneCaptcha";
         operations.set(key, String.valueOf(phoneCaptcha));
         **/

        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestParam, requestHeaders);
        ResponseEntity<PhoneCaptchaResponseBean> responseEntity = restTemplate.postForEntity("http://client.sms10000.com/api/webservice", httpEntity, PhoneCaptchaResponseBean.class);
        return responseEntity.getBody();
    }


    @PostMapping("/admin/onNoteWithoutDate/{resumeId}")//发送不带参数的通过短信
    public PhoneCaptchaResponseBean sendOnNoteWithoutDateByResumeId(@PathVariable("resumeId") Long resumeId){
        ResumeDeliverEntity resumeDelieverEntity=resumeDeliverRepository.findById(resumeId);
        Long dw_resume_id=resumeDelieverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity=dwPersonalInformationRepository.findByResumeId(dw_resume_id);
        String name=dwPersonalInformationEntity.getName();
        String phone=dwPersonalInformationEntity.getTelephone();
        Random ran = new Random();
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        messageConverters.add(converter);
        messageConverters.add(formHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("cmd", "send");
        requestParam.set("eprId", "619");
        requestParam.set("userId", "xhwlxyzp");
        requestParam.set("timestamp", String.valueOf(System.currentTimeMillis()));
        requestParam.set("key", encryption("619" + "xhwlxyzp" + "xyzp00" + requestParam.getFirst("timestamp")));
        requestParam.set("msgId", String.valueOf(ran.nextInt()));
        requestParam.set("format", "1");
        requestParam.set("mobile", phone);

        requestParam.set("content", name+"您好，恭喜您成功通过本公司的笔试及面试环节！期待您的加入" );
        HttpHeaders requestHeaders = new HttpHeaders();

        /**存短信验证码到redis
         String key = username + "_PhoneCaptcha";
         operations.set(key, String.valueOf(phoneCaptcha));
         **/

        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestParam, requestHeaders);
        ResponseEntity<PhoneCaptchaResponseBean> responseEntity = restTemplate.postForEntity("http://client.sms10000.com/api/webservice", httpEntity, PhoneCaptchaResponseBean.class);
        return responseEntity.getBody();
    }
    @GetMapping("/admin/offNote/{resumeId}")//发送拒绝短信
    public PhoneCaptchaResponseBean sendOffNoteByResumeId(@PathVariable("resumeId") Long resumeId){
        ResumeDeliverEntity resumeDelieverEntity=resumeDeliverRepository.findById(resumeId);
        Long dw_resume_id=resumeDelieverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity=dwPersonalInformationRepository.findByResumeId(dw_resume_id);
        String name=dwPersonalInformationEntity.getName();
        String phone=dwPersonalInformationEntity.getTelephone();
        Random ran = new Random();
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        messageConverters.add(converter);
        messageConverters.add(formHttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("cmd", "send");
        requestParam.set("eprId", "619");
        requestParam.set("userId", "xhwlxyzp");
        requestParam.set("timestamp", String.valueOf(System.currentTimeMillis()));
        requestParam.set("key", encryption("619" + "xhwlxyzp" + "xyzp00" + requestParam.getFirst("timestamp")));
        requestParam.set("msgId", String.valueOf(ran.nextInt()));
        requestParam.set("format", "1");
        requestParam.set("mobile", phone);
        requestParam.set("content", name+"您好，很遗憾您没有通过该岗位的招聘，希望未来会有合作的机会，祝您今后求职顺利");
        HttpHeaders requestHeaders = new HttpHeaders();

        /**存短信验证码到redis
         String key = username + "_PhoneCaptcha";
         operations.set(key, String.valueOf(phoneCaptcha));
         **/

        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestParam, requestHeaders);
        ResponseEntity<PhoneCaptchaResponseBean> responseEntity = restTemplate.postForEntity("http://client.sms10000.com/api/webservice", httpEntity, PhoneCaptchaResponseBean.class);
        return responseEntity.getBody();
    }
    /**
     * 引入md5加密
     *
     * @param text
     * @return
     */
    public String encryption(String text) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5.toUpperCase();
    }
}
