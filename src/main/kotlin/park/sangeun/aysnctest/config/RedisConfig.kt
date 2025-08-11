package park.sangeun.aysnctest.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import park.sangeun.aysnctest.common.component.RedisService

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val defaultHost: String,

    @Value("\${spring.data.redis.port}")
    private val defaultPort: Int,
) {
    @Bean
    @Primary
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = defaultHost
        redisStandaloneConfiguration.port = defaultPort

        return LettuceConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean
    @Primary
    fun stringRedisTemplate(): StringRedisTemplate {
        val stringRedisTemplate = StringRedisTemplate()
        stringRedisTemplate.connectionFactory = redisConnectionFactory()

        return stringRedisTemplate
    }

    @Bean
    @Primary
    fun redisService(): RedisService {
        return RedisService(stringRedisTemplate())
    }

}