package com.hhh.wechat_order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目中的Url
 */
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {

    //微信公众平台授权url
    private String wechatMpAuthorize;

    //微信开放平台授权的url
    private String wechatOpenAuthorize;

    //项目的url
    private String sell;
}
