package com.atguigu.spzx.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.atguigu.spzx.feign.user.UserFeignClient;
import com.atguigu.spzx.model.entity.user.UserInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Lazy
    @Autowired
    UserFeignClient userFeignClient;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        RequestPath path = request.getPath();
        URI uri = request.getURI();
        HttpHeaders headers = request.getHeaders();
        List<String> tokens = headers.get("token");

        if (tokens != null && tokens.size() > 0) {
            String token = tokens.get(0);
            if (StringUtils.hasText(token)) {
                System.out.println("验证token: " + token);
                // todo 验证token
                UserInfo userInfo = null;
                CompletableFuture<UserInfo> completableFuture = CompletableFuture.supplyAsync(() -> {
                    return userFeignClient.checkToken(token);
                });
                userInfo = completableFuture.get();
                System.out.println(userInfo);
                // 传递鉴权结果
                if (userInfo != null) {
                    request.mutate().header("userInfoJson", JSON.toJSONString(userInfo, SerializerFeature.BrowserCompatible));
                    exchange.mutate().request(request);
                }
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
