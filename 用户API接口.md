# xhwlRecruitment
## 用户登录 post {{8080}}/login 
```
    【form】 username=admin
    【form】 password=123456

正常返回
{
    "code": 200,
    "msg": "Login success",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjM3MDEwODQsInVzZXJuYW1lIjoiYWRtaW4ifQ.kTJxhGmQL8mj6q50IGOq7EeMcaf1e10WDNFtpBiix0Q"
}
用户不存在返回
{
    "code": 500,
    "msg": null,
    "data": null
}
密码错误返回
{
    "code": 401,
    "msg": "Unauthorized",
    "data": null
}
```

## put {{8080}}/resume?form=1  新建简历表 id为简历类型
```
正常创建返回
{
    "id": 10,
    "userId": 4,
    "selfAssessment": null,
    "uploadResumePath": null,
    "supportDetailPath": null,
    "photoPath": null,
    "resumesForm": 1
}
重复创建返回
{
    "code": 500,
    "msg": "已经创建过该用户的简历",
    "data": null
}
```

## get {{8080}}/resume 获得简历表
```
正常返回
{
    "id": 10,
    "userId": 4,
    "selfAssessment": null,
    "uploadResumePath": null,
    "supportDetailPath": null,
    "photoPath": null,
    "resumesForm": 1
}
```

## post {{8080}}/resume 修改简历表
```
【body】
{
    "id": 10,
    "userId": 4,
    "selfAssessment": null,
    "uploadResumePath": null,
    "supportDetailPath": null,
    "photoPath": null,
    "resumesForm": 1
}
正常返回
{
    "id": 10,
    "userId": 4,
    "selfAssessment": null,
    "uploadResumePath": null,
    "supportDetailPath": null,
    "photoPath": null,
    "resumesForm": 1
}
无修改权限（userId 不是自己的id）
{
    "code": 500,
    "msg": "无修改权限",
    "data": null
}
```

## 个人信息表

## get {{8080}}/person 获得个人信息表
```
正常返回
{
    "id": 5,
    "resumeId": 5,
    "name": "张三丰",
    "sex": 2,
    "idType": 1,
    "idNumber": "350124199601052343",
    "birthday": "1996-12-05",
    "email": "32453424534@qq.com",
    "telephone": "15980245342",
    "maritalStatus": 1,
    "workSeniority": "5",
    "politicalStatus": "团员",
    "presentAddress": "福建省厦门市思明区厦大学生公寓",
}
```
## post {{8080}}/person 上传个人信息表（新建或修改）
```
时间格式为 "2015-01-02"
【body】
{
    "name": "张三丰",
    "sex": 2,
    "idType": 1,
    "idNumber": "350124199601052343",
    "birthday": "1996-12-05",
    "email": "32453424534@qq.com",
    "telephone": "15980245342",
    "maritalStatus": 1,
    "workSeniority": "5",
    "politicalStatus": "团员",
    "presentAddress": "福建省厦门市思明区厦大学生公寓",
}
正常返回
{
    "id": 3,
    "resumeId": 4,
    "name": "张三丰",
    "sex": 2,
    "idType": 1,
    "idNumber": "350124199601052343",
    "birthday": "1996-12-05",
    "email": "32453424534@qq.com",
    "telephone": "15980245342",
    "maritalStatus": 1,
    "workSeniority": "5",
    "politicalStatus": "团员",
    "presentAddress": "福建省厦门市思明区厦大学生公寓",
}
```

## 教育经历表

## get {{8080}}/education 获取教育经历表
```
正常返回
[
    {
        "id": 9,
        "resumeId": 10,
        "startTime": null,
        "endTime": null,
        "school": null,
        "speciality": null,
        "educationHistory": null,
        "rank": null
    }
]
无教育经历
[]
```



## post {{8080}}/education 新建修改一条教育经历
```
传id为修改，不传id为新建
【body】
{
    "id": 9,
    "startTime": "2015-01-01",
    "endTime": "2016-02-03",
    "school": "厦门大学",
    "speciality": "计算机科学",
    "educationHistory": 1,
    "rank": null
}
正常返回
{
    "id": 9,
    "resumeId": 10,
    "startTime": "2015-01-01",
    "endTime": "2016-02-03",
    "school": "厦门大学",
    "speciality": "计算机科学",
    "educationHistory": 1,
    "rank": null
}
```

## delete {{8080}}/education/9 删除id为9的教育经历
```
正常返回
    无返回
    
修改不是自己的教育经历
{
    "code": 500,
    "msg": "无修改权限",
    "data": null
}
```

## 培训经历
## get {{8080}}/training 获取培训经历
```
返回
[
    {
        "id": 5,
        "resumeId": 10,
        "startTime": "2015-07-12",
        "endTime": "2016-05-13",
        "trainingInstitutions": "安大法",
        "trainingContent": "搭建一个深度学习框架",
        "description": "强的一批"
    },
    {
        "id": 7,
        "resumeId": 10,
        "startTime": null,
        "endTime": null,
        "trainingInstitutions": null,
        "trainingContent": null,
        "description": null
    }
]
```

## post {{8080}}/training 新建或修改培训经历
```
【body】带id为修改，不带id为新建
{
    "id": 5,
    "resumeId": 10,
    "startTime": "2015-07-12",
    "endTime": "2016-05-13",
    "trainingInstitutions": "安大法",
    "trainingContent": "搭建一个深度学习模板",
    "description": "强的一批"
}
返回
{
    "id": 5,
    "resumeId": 10,
    "startTime": "2015-07-12",
    "endTime": "2016-05-13",
    "trainingInstitutions": "安大法",
    "trainingContent": "搭建一个深度学习模板",
    "description": "强的一批"
}
```

## delete {{8080}}/training/7 删除id为7培训经历字段
```
正确无结果
错误返回
{
    "code": 500,
    "msg": null,
    "data": null
}
```

## 项目经历

## get {{8080}}/project 获取项目经历
```
[
    {
        "id": 3,
        "resumeId": 10,
        "projectName": "创客空间管理系统",
        "projectRole": "后端程序员",
        "projectDescription": "给大佬端茶送水"
    },
    {
        "id": 4,
        "resumeId": 10,
        "projectName": "xhwl",
        "projectRole": "后端程序员",
        "projectDescription": "做了一点微小的贡献"
    }
]
```

## post http://119.29.16.250:8080/project 新建或修改项目经历
```
[body] 无id 为新建，有id为修改
{
	"id":6,
    "projectName": "xhwl",
    "projectRole": "后端程序员",
    "projectDescription":"做了一点微小的贡献"
}
正常返回
{
    "id": 6,
    "resumeId": 10,
    "projectName": "xhwl",
    "projectRole": "后端程序员",
    "projectDescription": "做了一点微小的贡献"
}
```

## delete /project/6 删除项目经历 （提交与返回同其他删除）

## 工作经历

## get {{8080}}/work 获取工作经历
```
[
    {
        "id": 2,
        "resumeId": 10,
        "startTime": "2015-01-01",
        "endTime": "2016-12-12",
        "company": "兴海物联",
        "position": "机器学习工程师",
        "description": "复制编写机器学习框架"
    },
    {
        "id": 3,
        "resumeId": 10,
        "startTime": "2015-01-01",
        "endTime": "2016-12-12",
        "company": "京东",
        "position": "后端工程师",
        "description": "干苦力"
    },
    {
        "id": 4,
        "resumeId": 10,
        "startTime": "2015-01-01",
        "endTime": "2016-12-12",
        "company": "华为",
        "position": "后端工程师",
        "description": "干苦力"
    }
]
```

## post {{8080}}/work 新建或修改工作经历
```
【body】无id 为新建，有id为修改
    {
        "id": 3,
        "startTime": "2015-01-01",
        "endTime": "2016-12-12",
        "company": "京东",
        "position": "后端工程师",
        "description": "干苦力"
    }
返回
{
    "id": 3,
    "resumeId": 10,
    "startTime": "2015-01-01",
    "endTime": "2016-12-12",
    "company": "京东",
    "position": "后端工程师",
    "description": "干苦力"
}
```

## delete {{8080}}/work/4 删除工作经历 （同其他delete）


## 实习经历
## get {{8080}}/internship 获取实习经历
```
返回
[
    {
        "id": 2,
        "resumeId": 10,
        "startTime": null,
        "endTime": null,
        "company": null,
        "position": null,
        "description": null
    },
    {
        "id": 3,
        "resumeId": 10,
        "startTime": "2015-01-02",
        "endTime": null,
        "company": null,
        "position": null,
        "description": null
    }
]
```


## post {{8080}}/internship 新建或修改实习经历
```
【body】无id 为新建，有id为修改
{
        "id": 3,
        "startTime": "2015-01-02",
        "endTime": "2016-01-02",
        "company": "京东方",
        "position": "电子工程师",
        "description": "焊接电路"
}
返回
{
    "id": 3,
    "resumeId": 10,
    "startTime": "2015-01-02",
    "endTime": "2016-01-02",
    "company": "京东方",
    "position": "电子工程师",
    "description": "焊接电路"
}
```


## delete {{8080}}/internship/2 删除实习经历


## 奖励情况
## get {{8080}}/award 获取奖励情况
```
[
    {
        "id": 1,
        "resumeId": 10,
        "awardName": null,
        "dateOfAward": null
    },
    {
        "id": 2,
        "resumeId": 10,
        "awardName": "acm程序设计大赛",
        "dateOfAward": "2015-01-12"
    }
]
```

## post {{8080}}/award 新建或修改奖励
```
【body】无id 为新建，有id为修改
{
    "id": 2,
    "resumeId": 10,
    "awardName": "acm程序设计大赛",
    "dateOfAward": "2015-01-12"
}
返回
{
    "id": 2,
    "resumeId": 10,
    "awardName": "acm程序设计大赛",
    "dateOfAward": "2015-01-12"
}
```

## delete {{8080}}/award/3 删除奖励

## 求职意向

## get {{8080}}/intention 获取求职意向
```
返回
{
    "id": 3,
    "resumeId": 10,
    "workPlace": "深圳",
    "salary": 10000,
    "expectedTimeForDuty": "2018-06-02"
}
```

## post http://119.29.16.250:8080/intention 新建或修改求职意向
```
【body】工作地点和期待时间必填，否则会抛出异常
{
    "workPlace": "深圳",
    "salary": 10000,
    "expectedTimeForDuty": "2018-06-02"
}
返回
{
    "id": 3,
    "resumeId": 10,
    "workPlace": "深圳",
    "salary": 10000,
    "expectedTimeForDuty": "2018-06-02"
}
```

## 自我评价
## get {{8080}}/selfAssessment 获取自我评价
```
返回一个字符串
```

## post {{8080}}/selfAssessment 修改自我评价
```
{
	"selfAssessment":"一般般的自我介绍"
}
返回一个字符串
```

## 修改简历类型 put {{8080}}/resumesForm/1 需要header 1.校园招聘 2.社会招聘 3.实习生招聘
```
返回
{
    "resumesForm": "1"
}
```
## token状态查看 get {{8080}}/tokenCheck
```
正常 200状态码
错误 或过期 404状态码
{
    "timestamp": 1524561297658,
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/401"
}

```

## 获取图形验证码 get {{8080}}/createPictureCaptcha
```
获取图形验证码的通用接口 返回uuid和byte[]格式的验证码图片
uuid用与验证码建立唯一关系

返回
{
    "uuid": "b2c0da2c-cbbe-4c42-8969-fb5f854a0f15",
    "picture": "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"
}
```

## 注册时 获取短信验证码 post {{8080}}/createPhoneCaptchaForLogin
```
表单提交
【username】手机号
【captcha】图片验证码结果，忽略大小写
【uuid】获取图片验证码时获得的uuid

正确返回 200状态码
{
    "result": "1",
    "tips": "提交成功"
}

错误返回 500状态码
图形验证码输入错误
{
    "code": 423,
    "msg": "图形验证码输入错误",
    "data": null
}
{
    "code": 432,
    "msg": "用户已存在",
    "data": null
}
```

## 重置密码时 获取短信验证码 post {{8080}}/createPhoneCaptchaResetPassword
```
表单提交
【username】手机号
【captcha】图片验证码结果，忽略大小写
【uuid】获取图片验证码时获得的uuid

正确返回 200状态码
{
    "result": "1",
    "tips": "提交成功"
}

错误返回 500状态码
图形验证码输入错误
{
    "code": 423,
    "msg": "图形验证码输入错误",
    "data": null
}
{
    "code": 426,
    "msg": "用户未注册",
    "data": null
}
```

## 用户注册 post {{8080}}/register
```
表单提交
【username】手机号
【password】密码
【phoneCaptcha】手机验证码

正确返回 200状态码
{
    "code": 200,
    "msg": "register success",
    "data": null
}
错误返回 500状态码
{
    "code": 423,
    "msg": "该手机已被注册",
    "data": null
}
{
    "code": 424,
    "msg": "手机验证码输入错误",
    "data": null
}
```
## 用户重置密码 post {{8080}}/resetPassword
```
获取图形验证码和短信验证码的流程与api和注册相同

表单提交
【username】手机号
【password】密码
【phoneCaptcha】手机验证码

正确返回 200状态码
{
    "code": 200,
    "msg": "resetPassword success",
    "data": null
}
错误返回 500状态码
{
    "code": 426,
    "msg": "用户未注册",
    "data": null
}
{
    "code": 424,
    "msg": "手机验证码输入错误",
    "data": null
}
```

## 用户登录，带验证码版 post {{8080}}/loginWithCaptcha
```
验证码的获取同注册 {{8080}}/createPictureCaptcha
表单提交
【username】手机号
【password】密码
【captcha】图片验证码结果，忽略大小写
【uuid】获取图片验证码时获得的uuid

正确返回 200状态码
{
    "code": 200,
    "msg": "Login success",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MjYwOTA1NjAsInVzZXJuYW1lIjoiMTU5ODAyNDUzNzIifQ.bUuUgl388vPZ5nFS320mkSeXyKzJJgI1LkIlACmIE2k"
}

验证码错误 400状态码
{
    "code": 423,
    "msg": "图形验证码输入错误",
    "data": null
}

用户名不存在 500状态码
{
    "code": 500,
    "msg": null,
    "data": null
}

密码错误 401状态码
{
    "code": 401,
    "msg": "Unauthorized",
    "data": null
}

```

## 用户按招聘类型获取岗位 get {{8080}}/positions/1?page=1&size=10&workplace=深圳&positionType=研发&positionName=测试
```
添加了模糊搜索的功能，？后面的内容在搜索时输入
eg：{{8080}}/positions/1?workPlace=深圳
返回
{
    "content": [
        {
            "id": "12",
            "positionName": "测试岗位,误删~",
            "positionType": "研发",
            "workSeniority": "不限",
            "workPlace": "深圳",
            "salary": null,
            "recruitingNumbers": "10",
            "publishDate": "2018-05-03",
            "department": "7",
            "jobResponsibilities": "在这里，你将从事IT应用层软件、分布式云化软件、互联网软件等的设计开发，可以采用敏捷、Devops、开源等先进的软件设计开发模式，接触最前沿的产品和软件技术，成为一个软件大牛；你将参与华为产品的软件研发工作，包括但不限于：\n（1）完成从客户需求到软件产品定义、架构设计、开发实现、再到上线运营维护等产品生命周期中的各个环节；\n（2）创造性解决产品在实现过程中的技术难题，应用前沿技术提升产品的核心竞争力，如分布式系统、性能调优、可靠性、数据库等；\n（3）有机会参与业界前沿技术研究和规划，参与开源社区运作，与全球专家一起工作、交流，构建华为在业界影响力。",
            "jobRequirements": "专业知识要求：\n1、计算机、软件、通信等相关专业本科及以上学历；\n2、热爱编程，基础扎实，熟悉掌握但不限于JAVA/C++/Python/JS/HTML/GO等编程语言中的一种或数种，有良好的编程习惯；\n3、具备独立工作能力和解决问题的能力、善于沟通，乐于合作，热衷新技术，善于总结分享，喜欢动手实践；   \n4、对数据结构、算法有一定了解；\n5、优选条件：\n（1）熟悉TCP/IP协议及互联网常见应用和协议的原理；\n（2）有IT应用软件、互联网软件、IOS/安卓等相关产品开发经验，不满足于课堂所学，在校期间积极参加校内外软件编程大赛或积极参于编程开源社区组织；\n（3）熟悉JS/AS/AJAX/HTML5/CSS等前端开发技术。"
        },
        {
            "id": "24",
            "positionName": "测试工程师",
            "positionType": "研发",
            "workSeniority": "不限",
            "workPlace": "广州",
            "salary": null,
            "recruitingNumbers": "3",
            "publishDate": "2018-05-16",
            "department": "7",
            "jobResponsibilities": "测试",
            "jobRequirements": "测试"
        }
    ],
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "sort": null,
    "first": true,
    "numberOfElements": 2,
    "size": 10,
    "number": 0
}
```
## 用户进行投递 put {{8080}}/deliver/1 传入岗位的id （需要header）
```
成功返回   投递结果的数据库记录id
失败返回
{
    "code": 500,
    "msg": "重复投递",
    "data": null
}
或
{
    "code": 500,
    "msg": "简历类型不符",
    "data": null
}
```
## 获得用户的投递情况（需要header） get {{8080}}/deliver
recruitmentType 招聘类型 1校园招聘 2社会招聘 3.实习生招聘    
recruitmentState 应聘情况 0.待审核1.简历审核通过2.部门笔试通过3.部门面试通过 4.已录用 -1.已回绝    
deleteAuth: 是否有删除权限 0不可删除 1可删除
```
[
    {
        "id": "38",
        "positionName": "测试岗位,误删~",
        "recruitmentType": "1",
        "recruitmentState": "2",
        "deleteAuth": "0"
    }, 
    {
        "id": "70",
        "positionName": "企业客户销售客户代表",
        "recruitmentType": "1",
        "recruitmentState": "-2",
        "deleteAuth": "0"
    }
]
```

## 用户删除投递 delete {{8080}}/deliver/45 数字为投递记录的id

## 用户下载自己上传的简历附件 get {{8080}}/downloadResume

## 用户下载自己上传的辅助材料 get {{8080}}/downloadSupportDetail

## 用户删除自己上传的简历 delete {{8080}}/deleteResume

## 用户删除自己上传的支持材料 delete {{8080}}/deleteSupportDetail

