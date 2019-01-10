package com.worry.modules.notify;

import com.worry.common.util.HttpClient;
import com.worry.common.util.MD5;

import java.util.HashMap;
import java.util.Map;

public class SendMsg {

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("userid","500197");
        map.put("account","500197");
        map.put("password", MD5.md5Str("500197@0108").toUpperCase());
        map.put("mobile","15628960805");
        map.put("content","您好王祥意用户，您已成功在本平台为尾号为5069的卡制定了还款计划，因刷卡持卡人身份信息、手机号或CVN2不匹配，计划执行失败，有疑问请联系客服110。【民麦金服】");
        map.put("action","send");

        String response= HttpClient.post("http://114.115.151.170/smsJson.aspx",map);
        System.out.println("返回数据："+response);

    }
}
