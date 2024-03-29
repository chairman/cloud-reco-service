package gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 一些限流的配置
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
@Configuration
public class KeyResolverConfig {

    @Bean
    public KeyResolver apiKeyResolver () {
        // 按URL限流,即以每秒内请求数按URL分组统计，超出限流的url请求都将返回429状态
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }

     @Bean
    public KeyResolver userKeyResolver () {
        // 按用户限流
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }

    // @Bean
    public KeyResolver ipKeyResolver () {
        // 按IP来限流
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
