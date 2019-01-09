package com.worry.common.util;

/**
 * 参数
 */
public class QuickConfig {

  /*  #测试信息
    测试地址：http://test.man-opaydev.ncfgroup.com
    MD5密钥: EIFB1W3Z0OKQV6GX
    AES密钥：30OG89ILUBRM0DWE
    机构号：MMWL001
    登录号：15275139017
    密码：139017*/
    /**********************************测试信息**********************************/

    public static String TEST_MD5_KEY="CYR1E0CDG0MJ4NN3";

    public static String TEST_AES_KEY="263YI2R7ZAQH56QK";

    public static String TEST_MECH_NO="MMWL001";

    //测试url
    // public static String BASEURL="http://test.man-opaydev.ncfgroup.com/";

    // 正式URL
    public static String BASEURL="https://client-api.verajft.com/";

    /** 商户注册 **/
    public static String MER_REGISTER=BASEURL+"fusionPosp/interface/memberReg";

    /** 商户业务开通 **/
    public static String MER_BUSINESS=BASEURL+"fusionPosp/interface/memberBus";

    /** 商户费率修改 **/
    public static String MER_RATE_UPDATE=BASEURL+"fusionPosp/interface/rateModify";

    /** 快捷支付 **/
    public static String MEY_QUICK_PAY=BASEURL+"fusionPosp/interface/quickPay";

    /** 结算卡修改 **/
    public static String MER_BALANCE_CARD_UPDATE="fusionPosp/interface/rateModify";

    /** 代付 **/
    public static String MER_PAYMENT=BASEURL+"fusionPosp/interface/pay";

    /** 绑卡发短信 **/
    public static String MER_BIND_CARD_MSG=BASEURL+"fusionPosp/interface/bindCardApply";

    /** 绑卡确认 **/
    public static String MER_BIND_CARD_CONFIRM=BASEURL+"fusionPosp/interface/bindCardConfirm";

    /** 余额查询 **/
    public static String MER_BALANCE=BASEURL+"fusionPosp/interface/balance";

    /** 商户资料查询 **/
    public static String MER_DATE_QUERY=BASEURL+"fusionPosp/interface/memberQuery";

    /** 结算卡修改 **/
    public static String MER_BANKCARD_MODIFY=BASEURL+"fusionPosp/interface/bankCardModify";

    /** 代还消费查询 **/
    public static String MER_PAYMENY_PAY=BASEURL+"fusionPosp/interface/tradeQuery";

    /** 代还支付查询 **/
    public static String MER_PAY_QUERY=BASEURL+"fusionPosp/interface/payQuery";
}
