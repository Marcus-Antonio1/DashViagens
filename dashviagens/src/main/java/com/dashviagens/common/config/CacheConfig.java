package com.dashviagens.common.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

/**
 * TTL curto para cotacoes de cambio (mudam pouco ao longo do dia) e para
 * respostas de clima. Ajuste os nomes dos caches conforme forem criados
 * nos services com @Cacheable.
 */
@Configuration
public class CacheConfig {

    @Bean
    public <RedisCacheManagerBuilderCustomizer> RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return builder -> builder
                .withCacheConfiguration("exchangeRates",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(15)))
                .withCacheConfiguration("weather",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30)));
    }
}
