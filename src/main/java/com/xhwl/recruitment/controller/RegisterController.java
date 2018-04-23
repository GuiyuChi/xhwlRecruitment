package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.bean.ResponseBean;
import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.domain.UserEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;
import org.springframework.web.bind.annotation.*;
import com.xhwl.recruitment.util.VerifyUtil;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

@RestController
public class RegisterController {
    private String Captcha = null;//图片验证码的字符串
    private String PhoneCaptcha = "1";//手机验证码
    @Autowired
    private UserRepository userRepository;
    private UserEntity userEntity;

    @GetMapping("/register")
    public String getCaptchaImage() throws IOException//生成验证码
    {
        Object[] objs = VerifyUtil.createImage();
        BufferedImage image = (BufferedImage) objs[1];
        Captcha = (String) objs[0];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);//写入流中
        byte[] bytes = baos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        return png_base64;
    }
    //objs[1]为验证码字符串，objs[2]为验证码图片


//    @PostMapping("/createphoneCaptcha")
//    public void sendPhoneCaptcha(@RequestParam("username") String username, @RequestParam("captcha") String captcha) throws ClientException//发送验证码
//    {
//        /** if(!this.Captcha.equals(captcha))
//         throw new MException("验证码与图片不符");
//         else {
//         PhoneCaptcha=Integer.toString((int)(Math.random()+9999)+1000);//生成手机验证码
//         //设置超时时间-可自行调整
//         System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//         System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//         //初始化ascClient需要的几个参数
//         final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
//         final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//         //替换成你的AK
//         final String accessKeyId = "yourAccessKeyId";//你的accessKeyId,参考本文档步骤2
//         final String accessKeySecret = "yourAccessKeySecret";//你的accessKeySecret，参考本文档步骤2
//         //初始化ascClient,暂时不支持多region（请勿修改）
//         IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
//         accessKeySecret);
//         DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//         IAcsClient acsClient = new DefaultAcsClient(profile);
//         //组装请求对象
//         SendSmsRequest request = new SendSmsRequest();
//         //使用post提交
//         request.setMethod(POST);
//         //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
//         request.setPhoneNumbers(username);
//         //必填:短信签名-可在短信控制台中找到
//         request.setSignName("云通信");
//         //必填:短信模板-可在短信控制台中找到
//         request.setTemplateCode("SMS_1000000");
//         //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//         //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//         request.setTemplateParam("{\"name\":\""+username+"\", \"code\":\""+PhoneCaptcha+"\"}");
//         //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
//         //request.setSmsUpExtendCode("90997");
//         //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//         request.setOutId("yourOutId");
//         //请求失败这里会抛ClientException异常
//         SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//         if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//         //请求成功
//         }
//         else{}//请求失败
//         }**/
//    }

    @PostMapping("/register")
    public ResponseBean register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("phoneCaptcha") String phoneCaptcha)//注册
    {
        if (username == null || password == null | phoneCaptcha == null)
            throw new MException("信息填写不全");
        if (!phoneCaptcha.equals(this.PhoneCaptcha))
            throw new MException("验证码输入错误");
        else if (userRepository.findByUsername(username) != null)
            throw new MException("该手机号已被注册");
        else {
            userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userRepository.save(userEntity);
            return new ResponseBean(200, "register success", null);
        }

    }
}
