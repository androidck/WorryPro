package com.worry.modules.notify;

import com.google.gson.Gson;
import com.worry.common.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 佳付通
 */
@Controller
@RequestMapping("/jiafu")
public class JiafuController {

    @RequestMapping("/notifyUrl")
    @ResponseBody
    public String notifyUrl(String orgCode,String signData ,String encryptData) throws Exception {
        System.out.println("返回订单号："+orgCode);
        System.out.println("返回签名："+signData);
        byte[]decrypt= AESUtil.decryptAES(encryptData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt));
        return "SUCCESS";
    }

    /**
     * 佳付通消费交易查询
     * @param chMerCode
     * @param orderCode
     * @return
     * @throws Exception
     */
    @RequestMapping("/jiafuQuickPay")
    @ResponseBody
    public String jiafuQuickPay(String chMerCode,String orderCode,String type) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("verCode","1001");//固定值：1001
        map.put("chMerCode",chMerCode);//商户唯一标识
        map.put("orderCode",orderCode);//订单号

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
        String response;
        System.out.println("交易类型："+type);
        if ("1".equals(type)){
            response= HttpClient.post(QuickConfig.MER_PAYMENY_PAY,map);
        }else  {
            response= HttpClient.post(QuickConfig.MER_PAY_QUERY,map);
        }
        String respEntryData=new Gson().fromJson(response, Response.class).getEncryptData();
        System.out.println("返回数据："+respEntryData);
        byte[]decrypt=AESUtil.decryptAES(respEntryData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt));
        return new String(decrypt,"utf-8");
    }


    /**
     * 佳付通代付
     * @param chMerCode
     * @param amount
     * @param cardNo
     * @param cvv2
     * @param validityDate
     * @param reserveMobile
     * @return
     * @throws Exception
     */
    @RequestMapping("/payMent")
    @ResponseBody
    public String payMent(String chMerCode,String amount,String cardNo,String cvv2,String validityDate,String reserveMobile) throws Exception {
        String orderId=OrderUtil.getOrderIdOne();
        System.out.println("订单号："+orderId);
        Map<String,String> map=new HashMap<>();
        map.put("chMerCode",chMerCode);//商户号
        map.put("busCode","3001");//业务编码
        map.put("verCode","1001");//接口版本号
        map.put("orderCode",orderId);//商户付款流水号 商户平台唯一,同一单号不允许重复提交
        map.put("amount",amount);//付款金额
        map.put("dfType","0");//代付类型 0—	付款到同一张卡 1—付款到另一张卡（目前只支持付款到同一张卡）
        map.put("settleAccNo",cardNo);//收款卡号
        map.put("cvv2",cvv2);//CVV2
        map.put("validityDate",validityDate);//有效期
        map.put("reserveMobile",reserveMobile);//收款银行卡预留手机号
        map.put("trxreserve","大辣条交易");//交易备注
        map.put("callBackUrl","http://www.baidu.com");//交易结果通知地址

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
        String response=HttpClient.post(QuickConfig.MER_PAYMENT,map);

        String respEntryData=new Gson().fromJson(response, Response.class).getEncryptData();
        System.out.println("返回数据："+respEntryData);
        byte[]decrypt=AESUtil.decryptAES(respEntryData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt));

        return new String(decrypt,"UTF-8");
    }

    @RequestMapping("/queryBalance")
    @ResponseBody
    public String queryBalance(String chMerCode) throws Exception{
        Map<String,String> map=new HashMap<>();
        map.put("verCode","1001");//固定值：1001
        map.put("chMerCode",chMerCode);//商户唯一标识

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
        String response= HttpClient.post(QuickConfig.MER_BALANCE,map);
        String respEntryData=new Gson().fromJson(response, Response.class).getEncryptData();
        System.out.println("返回数据："+respEntryData);
        byte[]decrypt=AESUtil.decryptAES(respEntryData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt));
        return new String(decrypt,"UTF-8");
    }
}
