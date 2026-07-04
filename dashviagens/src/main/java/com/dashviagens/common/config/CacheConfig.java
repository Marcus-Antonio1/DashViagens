package com.dashviagens.common.config;

import java.time.Duration;
import java.util.Map;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**import org.springframework.data.redis.cache.RedisCacheConfiguration;
 import org.springframework.data.redis.cache.RedisCacheManager;
 import org.springframework.data.redis.connection.RedisConnectionFactory;
 */

/**
 * Cache em memória simples para desenvolvimento sem Redis.
 * Para produção: reativar spring-boot-starter-data-redis no pom.xml,
 * trocar cache.type para redis e restaurar o RedisCacheManagerBuilderCustomizer.
 */
@Configuration
@EnableCaching
public class CacheConfig {

/**    @Bean
RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

RedisCacheConfiguration defaultCache =
RedisCacheConfiguration.defaultCacheConfig()
.entryTtl(Duration.ofHours(1));

Map<String, RedisCacheConfiguration> caches = Map.of(
"exchangeRates",
defaultCache.entryTtl(Duration.ofMinutes(15)),

"weather",
defaultCache.entryTtl(Duration.ofMinutes(30)),

"countries",
defaultCache.entryTtl(Duration.ofHours(24))
);

return RedisCacheManager.builder(connectionFactory)
.cacheDefaults(defaultCache)
.withInitialCacheConfigurations(caches)
.build();
}*/

}
