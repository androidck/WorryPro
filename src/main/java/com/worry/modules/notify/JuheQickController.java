package com.worry.modules.notify;

import com.google.gson.Gson;
import com.worry.common.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notify")
public class JuheQickController {


    @RequestMapping("/register")
    @ResponseBody
    public String register() throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("verCode","1001");//固定1001
        map.put("chMerCode","207974763298");//子商户号
        map.put("busCode","3001");//业务编码
        map.put("drawFee","0.50");//手续费 已元为单位，精确到分
        map.put("tradeRate","0.0042");//费率 0.5% 传0.005

        map.put("orgCode", QuickConfig.TEST_MECH_NO);//机构编号
        String encryptData=new Gson().toJson(map);
        System.out.println("加密数据前："+encryptData);
        byte[]bytes= AESUtil.encryptAES(encryptData.getBytes("UTF-8"),QuickConfig.TEST_AES_KEY.getBytes(),true,"UTF-8");
        System.out.println("AES加密："+ new String(bytes));

        String a= SignUtils.payParamsToString(map);
        a=a+"&md5key="+QuickConfig.TEST_MD5_KEY;
        System.out.println("拼接签名："+a);

        map.put("signData", MD5.md5Str(a).toUpperCase());//数据签名
        map.put("encryptData",new String(bytes));//加密报文
        System.out.println("签名："+map.get("signData"));
        String response= HttpClient.post(QuickConfig.MER_BUSINESS,map);
        String respEntryData=new Gson().fromJson(response, Response.class).getEncryptData();
        System.out.println("返回数据："+respEntryData);
        byte[]decrypt=AESUtil.decryptAES(respEntryData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt,"utf-8"));
        return new String(decrypt,"utf-8");
    }
}
