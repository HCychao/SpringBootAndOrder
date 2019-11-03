package com.hhh.wechat_order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信账号配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    //公众平台id
    private String mpAppId;

    //微信开发平台的id
    private String openAppId;

    //公众平台密钥
    private  String mpAppSecret;

    //微信开放平台的密钥
    private  String openAppSecret;

    //商户号
    private String mchId;

    //商户密钥
    private String mchKey;

    //商户证书路径
    private String keyPath;

    //微信支付异步通知地址
    private String notifyUrl;

    //消息推送模板Id
    private Map<String , String> templateId;
}
