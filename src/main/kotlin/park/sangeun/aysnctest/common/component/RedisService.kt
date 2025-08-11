package park.sangeun.aysnctest.common.component

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisService(
    private val redisTemplate: StringRedisTemplate
) {
    val opsForHash = redisTemplate.opsForHash<String, String>()
    val opsForValue = redisTemplate.opsForValue()

    fun getHashEntries(key: String): Map<String, String>{
        return opsForHash.entries(key)
    }

    fun getHashValue(key: String, hashKey: String): String {
        return opsForHash.get(key, hashKey) ?: ""
    }

    fun getString(key: String): String {
        return opsForValue.get(key) ?: ""
    }
}