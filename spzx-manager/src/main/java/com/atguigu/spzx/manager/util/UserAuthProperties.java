package com.atguigu.spzx.manager.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserAuthProperties {
    private List<String> noAuthUrls;

}
