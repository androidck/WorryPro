package com.worry.modules.notify;

import com.google.gson.Gson;
import com.worry.common.util.*;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notify")
@Api(value = "/notify", description = "聚合汇通Controller")
public class JuheQickController {


    @RequestMapping(value = "/rateUpdate",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ApiOperation(value = "代还费率修改")
    @ResponseBody
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "merchantNo",value = "商户号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "config",value = "DHE、DHF",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "settleBankNo",value = "结算卡号",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "cost",value = "手续费以分为单位（如1元传100）",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "type",value = "类型 0 修改结算卡 1修改费率",paramType = "query",required = true,dataType = "String"),
                    @ApiImplicitParam(name = "rate",value = "费率（如0.35%，传0.35即可）",paramType = "query",required = true,dataType = "String")
            }
    )

    public String rateUpdate(String  config,String merchantNo,String settleBankNo,String rate,String cost,String type) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("agentNo", Config.agentNo);//渠道号
        map.put("config", config);//通道参数 这里默认DHE

        map.put("merchantNo",merchantNo);//商户外部唯一标识，什么类型都行，只要保证唯一
        map.put("settleBankNo",settleBankNo);//结算卡号
        //map.put("settleType","00");//卡折类型
        map.put("rate",rate);//费率
        map.put("cost",cost);//提现手续费
        map.put("type",type);//修改类型

        String a= SignUtils.payParamsToString(map);
        System.out.println("签名前："+a);
        a=a+Config.key;
        String sign= MD5.md5Str(a);
        map.put("sign",sign);
        System.out.println("签名："+sign);

        String response= HttpClient.post(Config.url+"/dh/update",map);
        System.out.println("返回数据："+response);
        return response;
    }
}
