package gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 跨域处理的配置
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
@Configuration
public class CorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();

        // 配置跨域
        corsConfiguration.addAllowedHeader("*"); // 允许所有请求头跨域
        corsConfiguration.addAllowedMethod("*"); // 允许所有请求方法跨域
        corsConfiguration.addAllowedOrigin("*"); // 允许所有请求来源跨域
        corsConfiguration.setAllowCredentials(true); //允许携带cookie跨域，否则跨域请求会丢失cookie信息

        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}
