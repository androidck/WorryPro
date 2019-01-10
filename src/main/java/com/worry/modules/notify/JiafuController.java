package com.worry.modules.notify;

import com.google.gson.Gson;
import com.worry.common.util.*;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * 佳付通
 */
@Controller
@RequestMapping("/jiafu")
@Api(value = "/jiafu", description = "佳付通Controller")
public class JiafuController {


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
    @ApiOperation(value = "佳付通消费交易查询")
    @RequestMapping(value = "/jiafuQuickPay",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    @ApiImplicitParams(
            { @ApiImplicitParam(name = "chMerCode",value = "商户号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "orderCode",value = "订单号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "type",value = "类型：1、消费，2、代付",paramType = "query",required = true,dataType = "String")}
    )
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
    @ApiOperation(value = "佳付通代付")
    @RequestMapping(value = "/payMent",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    @ApiImplicitParams(
            { @ApiImplicitParam(name = "chMerCode",value = "商户号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "amount",value = "订单号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "cardNo",value = "信用卡卡号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "cvv2",value = "签名栏后三位",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "validityDate",value = "有效期mm/yy",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "reserveMobile",value = "银行卡预留手机号",paramType = "query",required = true,dataType = "String")
            }
    )
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



    @ApiOperation(value = "佳付通商户余额查询")
    @RequestMapping(value = "/queryBalance",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    @ApiImplicitParam(name = "chMerCode",value = "商户号",paramType = "query",required = true,dataType = "String")
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


    @ApiOperation(value = "修改用户结算卡信息")
    @RequestMapping(value = "/updateMerBank",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "chMerCode",value = "商户号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "accountNo",value = "银行卡号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "accountName",value = "账户名",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "reservedMobile",value = "预留手机号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "subBankCode",value = "联行号",paramType = "query",required = true,dataType = "String"),
            }
    )
    @ResponseBody
    public String updateMerBank(String chMerCode,String accountNo,String accountName,String reservedMobile,String subBankCode) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("verCode","1001");//固定值：1001
        map.put("chMerCode",chMerCode);//商户唯一标识
        map.put("accountNo",accountNo);//以元为单位，精确到分 如0.5/笔则填0.5
        map.put("accountName",accountName);//参照 6.1业务信息列表
        map.put("reservedMobile",reservedMobile);//预留手机号
        map.put("subBankCode",subBankCode);//如 0.6%笔则填 0.006 小数点后最多不超过 5 位

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
        String response= HttpClient.post(QuickConfig.MER_BANKCARD_MODIFY,map);
        String respEntryData=new Gson().fromJson(response, Response.class).getEncryptData();
        System.out.println("返回数据："+respEntryData);
        byte[]decrypt=AESUtil.decryptAES(respEntryData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt));

        return new String(decrypt,"UTF-8");
    }



    @ResponseBody
    @ApiOperation(value = "开通业务")
    @RequestMapping(value = "/openBusiness",method = {RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=utf-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "chMerCode",value = "商户号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "busCode",value = "业务编码 2001 快捷 3001 代偿",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "drawFee",value = "手续费 已元为单位，精确到分",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "tradeRate",value = "费率 0.5% 传0.005",paramType = "query",required = true,dataType = "String"),
            }
    )
    public String openBusiness(String chMerCode,String busCode,String drawFee,String tradeRate) throws Exception{
        Map<String,String> map=new HashMap<>();
        map.put("verCode","1001");//固定1001
        map.put("chMerCode",chMerCode);//子商户号
        map.put("busCode",busCode);//业务编码
        map.put("drawFee",drawFee);//手续费 已元为单位，精确到分
        map.put("tradeRate",tradeRate);//费率 0.5% 传0.005

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
        System.out.println("解密后数据："+new String(decrypt));
        return new String(decrypt,"UTF-8");
    }


    @ResponseBody
    @ApiOperation(value = "绑定信用卡发送短信")
    @RequestMapping(value = "/bindCardMsg",method = {RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=utf-8"})
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "chMerCode",value = "商户号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "accName",value = "账户名称----真实姓名",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "idCard",value = "身份证号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "accNo",value = "卡号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "cvv2",value = "cvv2",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "validityDate",value = "有效期",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "mobile",value = "预留手机号",paramType = "query",required = true,dataType = "String"),

            }
    )
    public String bindCardMsg(String chMerCode,String accName ,String idCard,String accNo,String cvv2,String validityDate,String mobile) throws Exception{
        Map<String,String> map=new HashMap<>();
        map.put("verCode","1001");//固定值：1001
        map.put("chMerCode",chMerCode);//商户唯一标识
        map.put("accName",accName);//账户名称----真实姓名
        map.put("idCard",idCard);//身份证号
        map.put("accNo",accNo);//卡号
        map.put("cvv2",cvv2);//cvv2
        map.put("validityDate",validityDate);//有效期
        map.put("mobile",mobile);//预留手机号


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
        String response= HttpClient.post(QuickConfig.MER_BIND_CARD_MSG,map);
        String respEntryData=new Gson().fromJson(response, Response.class).getEncryptData();
        System.out.println("返回数据："+respEntryData);
        byte[]decrypt=AESUtil.decryptAES(respEntryData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt));
        return new String(decrypt,"utf-8");
    }

    @ResponseBody
    @ApiOperation(value = "查询商户资料")
    @RequestMapping(value = "/merBerQuery",method = {RequestMethod.POST,RequestMethod.GET},produces = {"application/json;charset=utf-8"})
    @ApiImplicitParam(name = "chMerCode",value = "商户号",paramType = "query",required = true,dataType = "String")
    public String merBerQuery (String chMerCode)throws Exception{
        Map<String,String> map=new HashMap<>();
        map.put("verCode","1001");//固定1001
        map.put("chMerCode",chMerCode);//子商户号

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
        String response= HttpClient.post(QuickConfig.MER_DATE_QUERY,map);
        String respEntryData=new Gson().fromJson(response, Response.class).getEncryptData();
        System.out.println("返回数据："+respEntryData);
        byte[]decrypt=AESUtil.decryptAES(respEntryData.getBytes(), QuickConfig.TEST_AES_KEY.getBytes(), true, "UTF-8");
        System.out.println("解密后数据："+new String(decrypt));
        return new String(decrypt,"utf-8");
    }
}
