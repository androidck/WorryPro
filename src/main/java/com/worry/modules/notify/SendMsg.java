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
        map.put("mobile","13969096706");
        map.put("content","您好吴栋林用户，您在本平台制定的日期还款计划，刷卡时间为2018年1月9日，刷卡金额为3000元，计划正常进行中，请知悉。【民麦金服】");
        map.put("action","send");

        String response= HttpClient.post("http://114.115.151.170/smsJson.aspx",map);
        System.out.println("返回数据："+response);

    }
}
