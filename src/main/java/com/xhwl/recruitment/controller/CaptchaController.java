package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.bean.PhoneCaptchaResponseBean;
import com.xhwl.recruitment.exception.CaptchaException;
import com.xhwl.recruitment.util.UUIDUtil;
import com.xhwl.recruitment.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Author: guiyu
 * @Description: 图像验证码和短信验证码相关
 * @Date: Create in 上午8:37 2018/5/12
 **/
@RestController
public class CaptchaController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取图像验证码
     *
     *
     * @return 验证码的图形
     */
    @GetMapping("/createPictureCaptcha")
    public HashMap<String, Object> getCaptchaImage() {
        HashMap<String, Object> hashMap = new LinkedHashMap<>();

        Object[] objs = VerifyUtil.createImage();
        String uuid = UUIDUtil.getUUID();
        hashMap.put("uuid", uuid);
        //操作redis
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(uuid, String.valueOf(objs[0]));
        try {
            BufferedImage image = (BufferedImage) objs[1];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);//写入流中
            byte[] bytes = baos.toByteArray();

            hashMap.put("picture", bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hashMap;
    }

    /**
     * 获取短信验证码
     *
     * @param username
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/createPhoneCaptcha")
    public PhoneCaptchaResponseBean sendPhoneCaptcha(@RequestParam("username") String username, @RequestParam("captcha") String captcha, @RequestParam("uuid") String uuid) throws URISyntaxException {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String savedCaptcha = operations.get(uuid);
        if (!captcha.equalsIgnoreCase(savedCaptcha)) {
            throw new CaptchaException("图形验证码输入错误");
        } else {
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
            requestParam.set("mobile", username);
            int phoneCaptcha = ran.nextInt(899999) + 100000;
            requestParam.set("content", "欢迎使用兴海物联招聘网站，您的注册验证码为" + Integer.toString(phoneCaptcha)+",如果不是本人操作，请忽略这条信息");
            HttpHeaders requestHeaders = new HttpHeaders();

            //存短信验证码到redis
            String key = username + "_PhoneCaptcha";
            operations.set(key, String.valueOf(phoneCaptcha));


            requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestParam, requestHeaders);
            ResponseEntity<PhoneCaptchaResponseBean> responseEntity = restTemplate.postForEntity("http://client.sms10000.com/api/webservice", httpEntity, PhoneCaptchaResponseBean.class);
            return responseEntity.getBody();
        }
    }


    /**
     * 获取图像验证码内容的测试接口
     *
     * @param uuid
     * @return
     */
    @PostMapping("/captchaTest")
    public String captchaTest(@RequestParam("uuid") String uuid) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(uuid);
    }

    /**
     * 获取短信验证码内容的测试接口
     *
     * @param username
     * @return
     */
    @PostMapping("/phoneCaptchaTest")
    public String phoneCaptchaTest(@RequestParam("username") String username) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String key = username + "_PhoneCaptcha";
        return operations.get(key);
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
